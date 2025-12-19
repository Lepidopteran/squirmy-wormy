package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.registries.EntityAttributes;
import dancesaurus.squirmy_wormy.registries.VanillaTabModifications;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTabs;

public class SquirmyWormyFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        SquirmyWormy.initialize();
        SquirmyWormy.registerItemCompostingChances();

        EntityAttributes.getAll().forEach((type, attributes) -> {
            EntityType<? extends LivingEntity> entityType = type.get();
            SquirmyWormy.LOGGER.info("Registering attributes for {}", entityType);

            FabricDefaultAttributeRegistry.register(entityType, attributes.get());
        });

        VanillaTabModifications.getAll().forEach((tab, items) -> {
            ItemGroupEvents.modifyEntriesEvent(tab.key()).register(entries -> {
                items.forEach(item -> {
                    entries.accept(item.get());
                });
            });
        });

        SquirmyWormy.LOGGER.info("Y O U ' V E  G O T  W O R M S !");
    }
}
