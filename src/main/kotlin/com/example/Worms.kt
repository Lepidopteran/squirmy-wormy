package com.example

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory


object ModItems {

    val EARTH_WORM: Item? =
        register( // Ignore the food component for now, we'll cover it later in the food section.
            Item(FabricItemSettings()),
            "earth_worm"
        )

    fun initialize() {

        // Get the event for modifying entries in the ingredients group.
// And register an event handler that adds our suspicious item to the ingredients group.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
            .register(ModifyEntries { itemGroup: FabricItemGroupEntries? -> itemGroup!!.add(EARTH_WORM) })
    }

    fun register(item: Item, id: String): Item {
        // Create the identifier for the item.
        val itemID = Identifier("worms", id)

        // Register the item.
        val registeredItem = Registry.register(Registries.ITEM, itemID, item)

        // Return the registered item!
        return registeredItem
    }
}

object Worms : ModInitializer {
    private val logger = LoggerFactory.getLogger("worms")

    override fun onInitialize() {
        ModItems.initialize()
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        logger.info("Hello Fabric world!")
    }
}