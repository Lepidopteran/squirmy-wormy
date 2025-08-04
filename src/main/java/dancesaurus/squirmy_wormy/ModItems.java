package dancesaurus.squirmy_wormy;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
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

    public static final Item FRIED_WORM = register(
            new Item(new FabricItemSettings()
                    .food(new FoodComponent.Builder()
                            .snack()
                            .hunger(1)
                            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 6 * 20, 1), 1.0f)
                            .build())),
            "fried_worm"
    );

    public static final Item EARTHWORM_SPAWN_EGG = register(new SpawnEggItem(
            SquirmyWormy.EARTHWORM,
            0xaf437c,
            0xaf437c,
            new FabricItemSettings()
    ), "earthworm_spawn_egg");

    public static final Item GLOW_WORM_SPAWN_EGG = register(new SpawnEggItem(
            SquirmyWormy.GLOW_WORM,
            0x9df4af,
            0x1fcdc5,
            new FabricItemSettings()
    ), "glow_worm_spawn_egg");

    public static void initialize() {
        CompostingChanceRegistry.INSTANCE.add(EARTHWORM, 1.0f);

        ItemGroupEvents
            .modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
            .register((FabricItemGroupEntries entries) -> {
                entries.add(EARTHWORM);
            }
        );

        ItemGroupEvents
                .modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((FabricItemGroupEntries entries) -> {
                            entries.add(FRIED_WORM);
                        }
                );

        ItemGroupEvents
            .modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
            .register((FabricItemGroupEntries entries) -> {
                entries.add(EARTHWORM_SPAWN_EGG);
                entries.add(GLOW_WORM_SPAWN_EGG);
            }
        );
    }

    public static Item register(Item item, String id) {
        Identifier itemID = new Identifier(MOD_ID, id);
        return Registry.register(Registries.ITEM, itemID, item);
    }
}
