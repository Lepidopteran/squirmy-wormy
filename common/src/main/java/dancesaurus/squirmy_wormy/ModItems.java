package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.platform.VanillaTab;
import dancesaurus.squirmy_wormy.registries.CompostingChances;
import dancesaurus.squirmy_wormy.registries.VanillaTabModifications;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

import static dancesaurus.squirmy_wormy.platform.Services.PLATFORM;

public class ModItems {

    public static final Supplier<Item> EARTHWORM = PLATFORM.registerItem(
            new Properties().food(new FoodProperties.Builder()
                    .fast()
                    .nutrition(1)
                    .effect(new MobEffectInstance(MobEffects.CONFUSION, 6 * 20, 1), 1.0f)
                    .build()), "earthworm"
    );

    public static final Supplier<Item> FRIED_WORM = PLATFORM.registerItem(
            new Properties().food(new FoodProperties.Builder()
                    .fast()
                    .nutrition(1)
                    .effect(new MobEffectInstance(MobEffects.CONFUSION, 6 * 20, 1), 1.0f)
                    .build()), "fried_worm"
    );

    public static final Supplier<Item> GLOW_WORM_SILK = PLATFORM.registerItem("glow_worm_silk");

    public static final Supplier<SpawnEggItem> EARTHWORM_SPAWN_EGG = PLATFORM.registerSpawnEgg(
            ModEntities.EARTHWORM,
            "#af437c",
            "#af437c",
            "earthworm_spawn_egg"
    );

    public static final Supplier<SpawnEggItem> GLOW_WORM_SPAWN_EGG = PLATFORM.registerSpawnEgg(
            ModEntities.GLOW_WORM,
            "#9df4af",
            "#1fcdc5",
            "glow_worm_spawn_egg"
    );

    public static void initialize() {
        CompostingChances.register(EARTHWORM, 1.0f);

        VanillaTabModifications.addItemTo(EARTHWORM, VanillaTab.FOOD_AND_DRINKS);
        VanillaTabModifications.addItemTo(FRIED_WORM, VanillaTab.FOOD_AND_DRINKS);
        VanillaTabModifications.addItemTo(GLOW_WORM_SILK, VanillaTab.INGREDIENTS);
        VanillaTabModifications.addItemTo(EARTHWORM_SPAWN_EGG, VanillaTab.SPAWN_EGGS);
        VanillaTabModifications.addItemTo(GLOW_WORM_SPAWN_EGG, VanillaTab.SPAWN_EGGS);
    }
}
