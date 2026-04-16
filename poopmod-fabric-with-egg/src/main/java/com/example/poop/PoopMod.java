package com.example.poop;

import com.example.poop.entity.PoopEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PoopMod implements ModInitializer {
    public static final String MODID = "poopmod";
    public static final EntityType<PoopEntity> POOP = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier.of(MODID, "poop"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PoopEntity::new)
            .dimensions(EntityDimensions.fixed(0.6f, 0.5f)).build()
    );

    public static final Item POOP_SPAWN_EGG = Registry.register(
        Registries.ITEM,
        Identifier.of(MODID, "poop_spawn_egg"),
        new SpawnEggItem(POOP, 0x5C3A21, 0x3A2415, new Item.Settings())
    );

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(POOP, PoopEntity.createAttributes());
    }
}