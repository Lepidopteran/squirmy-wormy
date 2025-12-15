package dancesaurus.squirmy_wormy.forge;

import net.minecraftforge.fml.common.Mod;

import dancesaurus.squirmy_wormy.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModForge {
    public ExampleModForge() {
        // Run our common setup.
        ExampleMod.init();
    }
}
