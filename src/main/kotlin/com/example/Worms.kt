package com.example

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

const val MOD_ID = "squirmy_wormy"

object ModItems {

    val EARTHWORM: Item? =
        register(
            Item(FabricItemSettings()
                .food(FoodComponent.Builder()
                .snack()
                .statusEffect(StatusEffectInstance(
                StatusEffects.NAUSEA, 6*20, 1), 1.0f)
                .build())),
            "earthworm"
        )

    fun initialize() {

        CompostingChanceRegistry.INSTANCE.add(EARTHWORM, 1.0f)


        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
            .register(ModifyEntries { itemGroup: FabricItemGroupEntries? -> itemGroup!!.add(EARTHWORM) })
    }

    fun register(item: Item, id: String): Item {
        // Create the identifier for the item.
        val itemID = Identifier(MOD_ID, id)

        // Register the item.
        val registeredItem = Registry.register(Registries.ITEM, itemID, item)

        // Return the registered item!
        return registeredItem
    }
}

object Worms : ModInitializer {
    private val logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        ModItems.initialize()
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world!")
    }
}