package dancesaurus.squirmy_wormy.mixin;

import dancesaurus.squirmy_wormy.entities.Earthworm;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public abstract class ZombieMixin extends Monster {

	protected ZombieMixin() {
		super(null, null);
	}

	@Inject(at = @At("TAIL"), method = "registerGoals")
	private void init(CallbackInfo info) {
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Earthworm.class, 6.0F, (double) 1.0F, 1.2));
	}
}

