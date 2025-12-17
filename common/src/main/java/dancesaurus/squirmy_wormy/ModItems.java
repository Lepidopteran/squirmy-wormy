package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.platform.Services;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class ModItems {

    public static final Item EARTHWORM = register(
            new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .fast()
                            .nutrition(1)
                            .effect(new MobEffectInstance(MobEffects.CONFUSION, 6 * 20, 1), 1.0f)
                            .build())),
            "earthworm"
    );

    public static final Item GLOW_WORM_SILK = register(
            new Item(new Item.Properties()),

            "glow_worm_silk"
    );

    public static final Item FRIED_WORM = register(
            new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .fast()
                            .nutrition(1)
                            .effect(new MobEffectInstance(MobEffects.CONFUSION, 6 * 20, 1), 1.0f)
                            .build()
                    )
            ),
            "fried_worm"
    );

    public static final Item EARTHWORM_SPAWN_EGG = register(
            new SpawnEggItem(
                    SquirmyWormy.EARTHWORM.get(),
                    0xaf437c,
                    0xaf437c,
                    new Item.Properties()
            ), "earthworm_spawn_egg"
    );

    public static final Item GLOW_WORM_SPAWN_EGG = register(
            new SpawnEggItem(
                    SquirmyWormy.GLOW_WORM.get(),
                    0x9df4af,
                    0x1fcdc5,
                    new Item.Properties()
            ), "glow_worm_spawn_egg"
    );

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

    public static Item register(Item item, String id) {
        Services.PLATFORM.registerItem(id, item);

        return item;
    }
}
