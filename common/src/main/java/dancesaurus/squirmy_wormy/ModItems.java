package dancesaurus.squirmy_wormy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

import static dancesaurus.squirmy_wormy.platform.Services.PLATFORM;

public class ModItems {

    public static final Supplier<Item> EARTHWORM = registerItem(
            new Properties().food(new FoodProperties.Builder()
                    .fast()
                    .nutrition(1)
                    .effect(new MobEffectInstance(MobEffects.CONFUSION, 6 * 20, 1), 1.0f)
                    .build()), "earthworm"
    );

    public static final Supplier<Item> FRIED_WORM = registerItem(
            new Properties().food(new FoodProperties.Builder()
                    .fast()
                    .nutrition(1)
                    .effect(new MobEffectInstance(MobEffects.CONFUSION, 6 * 20, 1), 1.0f)
                    .build()), "fried_worm"
    );

    public static final Supplier<Item> GLOW_WORM_SILK = registerItem(new Properties(), "glow_worm_silk");

    public static final Supplier<SpawnEggItem> EARTHWORM_SPAWN_EGG = PLATFORM.registerSpawnEgg(
            ModEntities.EARTHWORM,
            "#9df4af",
            "#1fcdc5",
            "earthworm_spawn_egg"
    );

    public static final Supplier<SpawnEggItem> GLOW_WORM_SPAWN_EGG = PLATFORM.registerSpawnEgg(
            ModEntities.GLOW_WORM,
            "#9df4af",
            "#1fcdc5",
            "glow_worm_spawn_egg"
    );


    /**
     * Registers an item with the platform, associating it with the provided name.
     *
     * @param properties The properties used to configure the item's behavior and appearance.
     * @param name       The identifier or name used for the item registration.
     * @return A supplier for the registered item, which can be used to retrieve or reference it later.
     */
    private static Supplier<Item> registerItem(Properties properties, String name) {
        return PLATFORM.registerItem(() -> new Item(properties), name);
    }

    public static void initialize() {
//        CompostingChanceRegistry.INSTANCE.add(EARTHWORM, 1.0f);
//
//        ItemGroupEvents
//                .modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
//                .register((FabricItemGroupEntries entries) -> {
//                            entries.add(EARTHWORM);
//                        }
//                );
//
//        ItemGroupEvents
//                .modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
//                .register((FabricItemGroupEntries entries) -> {
//                            entries.add(FRIED_WORM);
//                        }
//                );
//
//        ItemGroupEvents
//                .modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
//                .register((FabricItemGroupEntries entries) -> {
//                            entries.add(EARTHWORM_SPAWN_EGG);
//                            entries.add(GLOW_WORM_SPAWN_EGG);
//                        }
//                );
//
//        ItemGroupEvents
//                .modifyEntriesEvent(ItemGroups.INGREDIENTS)
//                .register((FabricItemGroupEntries entries) -> {
//                    entries.add(GLOW_WORM_SILK);
//                });
    }
}
