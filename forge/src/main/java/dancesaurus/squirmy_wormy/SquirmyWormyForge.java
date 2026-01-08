package dancesaurus.squirmy_wormy;

import com.mojang.serialization.Codec;
import dancesaurus.squirmy_wormy.registries.EntityAttributes;
import dancesaurus.squirmy_wormy.registries.EntitySpawnPlacements;
import dancesaurus.squirmy_wormy.registries.FlammableBlocks;
import dancesaurus.squirmy_wormy.registries.VanillaTabModifications;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import static dancesaurus.squirmy_wormy.SquirmyWormy.MOD_ID;

@Mod(MOD_ID)
public class SquirmyWormyForge {
	// region Deferred Registers

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,
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

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS,
			MOD_ID
	);

	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
			Registries.CREATIVE_MODE_TAB,
			MOD_ID
	);


	// endregion

	public SquirmyWormyForge() {
		SquirmyWormy.initialize();

		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		registerBusToDeferredRegistries(modBus);

		modBus.addListener(this::onSetup);
		modBus.addListener(this::onRegisterAttributes);
		modBus.addListener(this::onRegisterSpawnPlacements);
		modBus.addListener(this::onCreativeTab);
		modBus.addListener(this::onFinished);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void onSetup(@NotNull FMLCommonSetupEvent event) {
		event.enqueueWork(SquirmyWormy::registerCompostingChances);
	}

	private void onFinished(@NotNull FMLCommonSetupEvent event) {
		SquirmyWormy.LOGGER.info("The worms have been released from the forges of the earth...");
	}

	public void onRegisterAttributes(@NotNull EntityAttributeCreationEvent event) {
		EntityAttributes.getAll().forEach((type, attributes) -> {
			EntityType<? extends LivingEntity> entityType = type.get();
			SquirmyWormy.LOGGER.info("Registering attributes for {}", entityType);

			event.put(entityType, attributes.get().build());
		});
	}

	private void onCreativeTab(@NotNull BuildCreativeModeTabContentsEvent event) {
		VanillaTabModifications.get(event.getTabKey()).forEach((item) -> {
			event.accept(item.supplier());
		});
	}

	@SuppressWarnings("unchecked")
	private void onRegisterSpawnPlacements(@NotNull SpawnPlacementRegisterEvent event) {
		EntitySpawnPlacements.getAll().forEach(((lazyEntityType, props) -> {
			EntityType<Mob> entityType = (EntityType<Mob>) lazyEntityType.get();
			SpawnPlacements.SpawnPredicate<Mob> predicate = (SpawnPlacements.SpawnPredicate<Mob>) props.decoratorPredicate();

			SquirmyWormy.LOGGER.info("Registering spawn placement for {}", entityType);

			event.register(
					entityType,
					props.decoratorType(),
					props.heightMapType(),
					predicate,
					SpawnPlacementRegisterEvent.Operation.REPLACE
			);
		}));
	}

	public void registerBusToDeferredRegistries(IEventBus bus) {
		BLOCKS.register(bus);
		ITEMS.register(bus);
		ENTITY_TYPES.register(bus);
		BLOCK_ENTITY_TYPES.register(bus);
		SOUND_EVENTS.register(bus);
		MENU_TYPES.register(bus);
		RECIPE_TYPES.register(bus);
		RECIPE_SERIALIZERS.register(bus);
		POTIONS.register(bus);
		CREATIVE_MODE_TABS.register(bus);
	}
}

