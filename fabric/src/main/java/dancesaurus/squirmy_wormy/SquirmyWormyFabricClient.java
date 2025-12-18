package dancesaurus.squirmy_wormy;

import net.fabricmc.api.ClientModInitializer;

public class SquirmyWormyFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SquirmyWormyClient.initialize();
    }
}
