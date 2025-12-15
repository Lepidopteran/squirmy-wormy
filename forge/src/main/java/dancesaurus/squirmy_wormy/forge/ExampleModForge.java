package dancesaurus.squirmy_wormy.forge;

import net.minecraftforge.fml.common.Mod;

import dancesaurus.squirmy_wormy.SquirmyWormy;

@Mod(SquirmyWormy.MOD_ID)
public final class ExampleModForge {
    public ExampleModForge() {
        // Run our common setup.
        SquirmyWormy.init();
    }
}
