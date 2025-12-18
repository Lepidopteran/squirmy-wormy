package dancesaurus.squirmy_wormy.entities;

import dancesaurus.squirmy_wormy.SquirmyWormy;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
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

public class GlowWorm extends AnimalEntity implements GeoEntity {
    protected static final RawAnimation WIGGLE_ANIMATION = RawAnimation.begin().thenLoop("glow_worm_wiggle");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public GlowWorm(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new WanderAroundFarGoal(this, 0.5D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.DIRT), false));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    private static final Set<Block> VALID_SPAWN_BLOCKS =
            Set.of(
                    Blocks.MUD,
                    Blocks.BIG_DRIPLEAF,
                    Blocks.ANDESITE,
                    Blocks.DIORITE,
                    Blocks.STONE,
                    Blocks.DEEPSLATE,
                    Blocks.MOSS_BLOCK
            );


    public static boolean GlowWormSpawnRules(
            EntityType<GlowWorm> entityType,
            @NotNull ServerWorldAccess world,
            SpawnReason reason,
            @NotNull BlockPos pos,
            Random random
    ) {

        ServerWorld serverWorld = world.toServerWorld();

        SquirmyWormy.LOGGER.info("Worming it? {}", VALID_SPAWN_BLOCKS.contains(serverWorld
                .getBlockState(pos.down())
                .getBlock()));

        return VALID_SPAWN_BLOCKS.contains(serverWorld.getBlockState(pos.down()).getBlock());
    }


    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return SquirmyWormy.GLOW_WORM.create(world);
    }


    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Walking", 5, this::walkAnimationController));
    }

    protected <E extends GlowWorm> PlayState walkAnimationController(final AnimationState<E> event) {
        return event.setAndContinue(WIGGLE_ANIMATION);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.MUD);
    }

    @Override
    public boolean isClimbing() {
        return super.horizontalCollision;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20);
    }
}