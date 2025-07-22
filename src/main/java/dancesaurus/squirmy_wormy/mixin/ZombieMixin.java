package dancesaurus.squirmy_wormy.mixin;

import dancesaurus.squirmy_wormy.entities.Earthworm;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public abstract class ZombieMixin extends PathAwareEntity {

	protected ZombieMixin() {
		super(null, null);
	}

	@Inject(at = @At("TAIL"), method = "initGoals")
	private void init(CallbackInfo info) {
		this.goalSelector.add(1, new FleeEntityGoal<>(this, Earthworm.class, 6.0F, (double)1.0F, 1.2));
	}
}