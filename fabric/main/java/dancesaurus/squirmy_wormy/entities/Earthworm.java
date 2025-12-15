package dancesaurus.squirmy_wormy.entities;

import dancesaurus.squirmy_wormy.SquirmyWormy;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.JumpControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FoxEntity;
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

public class Earthworm extends AnimalEntity implements GeoEntity {
    protected static final RawAnimation WIGGLE_ANIMATION = RawAnimation.begin().thenLoop("earthworm_wiggle");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public Earthworm (EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);

        this.jumpControl = new JumpControl(this) {

            @Override
            public void tick() {
            }

            @Override
            public void setActive() {
                super.setActive();
            }
        };

        this.setPathfindingPenalty(PathNodeType.WALKABLE, 0.0F);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new WanderAroundGoal(this, 0.5D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.DIRT), false));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(1, new AttackGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, ZombieEntity.class, true));
        this.goalSelector.add(5, new LookAroundGoal(this));

    }

    public static boolean earthWormSpawnRules(EntityType<Earthworm> entityType, @NotNull ServerWorldAccess world, SpawnReason reason, @NotNull BlockPos pos, Random random) {
        // TODO: Make worms only spawn when raining
        return true;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return SquirmyWormy.EARTHWORM.create(world);
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
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.DIRT);
    }

    @Override
    public boolean isClimbing() {
        return super.horizontalCollision;
    }

    @Override
    public void tickMovement() {
        super.tickMovement();

        if (this.isClimbing()) {
            this.setVelocity(this.getVelocity().add(0.0D, 0.005D, 0.0D));
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, .1);
    }
}
