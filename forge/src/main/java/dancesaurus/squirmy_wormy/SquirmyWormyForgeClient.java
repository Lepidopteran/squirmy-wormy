package dancesaurus.squirmy_wormy;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SquirmyWormy.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SquirmyWormyForgeClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        SquirmyWormyClient.initialize();
    }
}
