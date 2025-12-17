package dancesaurus.squirmy_wormy;

import net.minecraftforge.fml.common.Mod;

@Mod(SquirmyWormy.MOD_ID)
public class SquirmyWormyForge {
    
    public SquirmyWormyForge() {
    
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        SquirmyWormy.LOGGER.info("Hello Forge world!");
        SquirmyWormy.init();
        
    }
}