
package com.example.poop;

import com.example.poop.entity.PoopEntityRenderer;
import com.example.poop.entity.PoopModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class PoopClient implements ClientModInitializer {
    public static final EntityModelLayer POOP_LAYER = new EntityModelLayer(Identifier.of(PoopMod.MODID, "poop"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(PoopMod.POOP, PoopEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(POOP_LAYER, PoopModel::getTexturedModelData);
    }
}
