package dancesaurus.squirmy_wormy;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static dancesaurus.squirmy_wormy.ModInfo.MOD_ID;

public class ModItems {

    public static final Item EARTHWORM = register(
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder()
                            .snack()
                            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6 * 20, 1), 1.0f)
                            .build())),
            "earthworm"
    );

    public static void initialize() {
        CompostingChanceRegistry.INSTANCE.add(EARTHWORM, 1.0f);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((FabricItemGroupEntries entries) -> entries.add(EARTHWORM));
    }

    public static Item register(Item item, String id) {
        Identifier itemID = new Identifier(MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }
}
