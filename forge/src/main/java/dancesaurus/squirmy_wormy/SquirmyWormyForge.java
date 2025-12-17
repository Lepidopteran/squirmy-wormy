package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.registries.EntityAttributes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import static dancesaurus.squirmy_wormy.SquirmyWormy.MOD_ID;


@Mod(SquirmyWormy.MOD_ID)
public class SquirmyWormyForge {
    // region Deferred Registers

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES,
            MOD_ID
    );

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
            ForgeRegistries.ENTITY_TYPES,
            MOD_ID
    );

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(
            ForgeRegistries.SOUND_EVENTS,
            MOD_ID
    );

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(
            ForgeRegistries.MENU_TYPES,
            MOD_ID
    );

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(
            ForgeRegistries.RECIPE_TYPES,
            MOD_ID
    );

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
            ForgeRegistries.RECIPE_SERIALIZERS,
            MOD_ID
    );

    // endregion

    public SquirmyWormyForge() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        registerBus(bus);
        bus.addListener(this::onRegisterAttributes);


        SquirmyWormy.init();
        SquirmyWormy.LOGGER.info("The worms have been released from the forges of the earth...");
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onRegisterAttributes(@NotNull EntityAttributeCreationEvent event) {
        EntityAttributes.getAll().forEach((type, attributes) -> {
            EntityType<? extends LivingEntity> entityType = type.get();
            SquirmyWormy.LOGGER.info("Registering attributes for {}", entityType);

            event.put(entityType, attributes.get().build());
        });
    }

    public void registerBus(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        ENTITY_TYPES.register(bus);
        BLOCK_ENTITY_TYPES.register(bus);
        SOUND_EVENTS.register(bus);
        MENU_TYPES.register(bus);
        RECIPE_TYPES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
        POTIONS.register(bus);
    }
}

