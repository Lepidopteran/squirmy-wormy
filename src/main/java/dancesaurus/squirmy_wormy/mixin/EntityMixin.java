package dancesaurus.squirmy_wormy.mixin;

import dancesaurus.squirmy_wormy.SquirmyWormy;
import dancesaurus.squirmy_wormy.entities.Earthworm;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(Entity.class)
public abstract class EntityMixin {

  @Shadow
  @Final
  protected Random random;

  @Shadow
  public abstract World getWorld();

  @Shadow
  public abstract double getX();

  @Shadow
  public abstract double getY();

  @Shadow
  public abstract double getZ();

  // NOTE: Not sure if we need this and, we can instead just put an override method in ZombieMixin.
  @Inject(at = @At("HEAD"), method = "remove")
  private void spawn_worm_worm_on_death(Entity.RemovalReason reason, CallbackInfo ci) {
    if ((Entity) (Object) this instanceof ZombieEntity) {
      if (random.nextFloat() <= 0.15) {
        Earthworm entity = SquirmyWormy.EARTHWORM.create(this.getWorld());
        if (entity != null) {
          entity.refreshPositionAndAngles(getX(), getY(), getZ(), this.random.nextFloat() * 360.0F, 0.0F);
          getWorld().spawnEntity(entity);
        }
      }
    }
  }
}
