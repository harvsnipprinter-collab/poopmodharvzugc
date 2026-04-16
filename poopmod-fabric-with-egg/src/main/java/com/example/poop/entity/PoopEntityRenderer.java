
package com.example.poop.entity;

import com.example.poop.PoopClient;
import com.example.poop.PoopMod;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class PoopEntityRenderer extends MobEntityRenderer<PoopEntity, PoopModel> {
    private static final Identifier TEXTURE = Identifier.of(PoopMod.MODID, "textures/entity/poop.png");

    public PoopEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new PoopModel(ctx.getPart(PoopClient.POOP_LAYER)), 0.3f);
    }

    @Override public Identifier getTexture(PoopEntity entity) { return TEXTURE; }
}
