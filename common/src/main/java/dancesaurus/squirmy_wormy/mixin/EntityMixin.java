package dancesaurus.squirmy_wormy.mixin;

import dancesaurus.squirmy_wormy.ModEntities;
import dancesaurus.squirmy_wormy.SquirmyWormy;
import dancesaurus.squirmy_wormy.entities.Earthworm;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(net.minecraft.world.entity.Entity.class)
public abstract class EntityMixin {

    @Shadow
    @Final
    protected RandomSource random;

    @Shadow
    public abstract Level level();

    @Shadow
    public abstract double getX();

    @Shadow
    public abstract double getY();

    @Shadow
    public abstract double getZ();

    // NOTE: Not sure if we need this and, we can instead just put an override method in ZombieMixin.
    @Inject(at = @At("HEAD"), method = "remove")
    private void spawn_worm_worm_on_death(Entity.RemovalReason reason, CallbackInfo ci) {
        if ((Entity) (Object) this instanceof Zombie) {
            if (random.nextFloat() <= 0.15) {
                Level level = this.level();
                Earthworm entity = ModEntities.EARTHWORM.get().create(level);
                if (entity != null) {
                    entity.moveTo(getX(), getY(), getZ(), this.random.nextFloat() * 360.0F, 0.0F);
                    level.addFreshEntity(entity);
                }
            }
        }
    }
}
