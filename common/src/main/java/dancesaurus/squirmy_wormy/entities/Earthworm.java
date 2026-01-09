package dancesaurus.squirmy_wormy.entities;

import dancesaurus.squirmy_wormy.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Set;

public class Earthworm extends Animal implements GeoEntity {
	protected static final RawAnimation WIGGLE_ANIMATION = RawAnimation.begin().thenLoop("earthworm_wiggle");
	private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

	public Earthworm(EntityType<? extends Animal> entityType, Level world) {
		super(entityType, world);

		this.jumpControl = new JumpControl(this) {

			@Override
			public void tick() {
			}
		};

		this.setPathfindingMalus(BlockPathTypes.WALKABLE, 0.0F);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 0.5D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.15F));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.DIRT), false));
		this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 4.0F));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Zombie.class, true));

	}

	private static final Set<Block> VALID_SPAWN_BLOCKS = Set.of(
			Blocks.GRASS_BLOCK,
			Blocks.PODZOL,
			Blocks.DIRT,
			Blocks.MYCELIUM,
			Blocks.COARSE_DIRT,
			Blocks.FARMLAND
	);

	public static boolean canWormSpawn(
			EntityType<Earthworm> entityType,
			@NotNull ServerLevelAccessor levelAccessor,
			MobSpawnType reason,
			@NotNull BlockPos pos,
			RandomSource random
	) {
		if (!reason.equals(MobSpawnType.NATURAL)) {
			return false;
		}


		return levelAccessor.getLevel().isRaining() &&
				VALID_SPAWN_BLOCKS.contains(levelAccessor.getBlockState(pos.below()).getBlock());
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(@NotNull ServerLevel world, @NotNull AgeableMob entity) {
		return ModEntities.EARTHWORM.get().create(world);
	}


	@Override
	public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "Walking", 5, this::walkAnimationController));
	}

	protected <E extends Earthworm> PlayState walkAnimationController(final AnimationState<E> event) {
		return event.setAndContinue(WIGGLE_ANIMATION);
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.geoCache;
	}

	@Override
	public boolean isFood(net.minecraft.world.item.ItemStack stack) {
		return stack.is(net.minecraft.world.item.Items.MUD);
	}

	@Override
	public boolean onClimbable() {
		return super.horizontalCollision;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob
				.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 1)
				.add(Attributes.MOVEMENT_SPEED, 0.1)
				.add(Attributes.FOLLOW_RANGE, 20)
				.add(Attributes.ATTACK_DAMAGE, 0.1)
				.add(Attributes.ATTACK_SPEED, 10);
	}
}