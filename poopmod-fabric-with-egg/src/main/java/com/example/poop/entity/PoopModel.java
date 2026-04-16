
package com.example.poop.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class PoopModel extends EntityModel<PoopEntity> {
    private final ModelPart poop;

    public PoopModel(ModelPart root) { this.poop = root.getChild("poop"); }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();
        root.addChild("poop", ModelPartBuilder.create()
            .uv(0,0).cuboid(-4f,-3f,-4f,8f,3f,8f)
            .uv(0,11).cuboid(-3f,-6f,-3f,6f,3f,6f)
            .uv(0,20).cuboid(-2f,-8f,-2f,4f,2f,4f)
            .uv(20,0).cuboid(-1f,-10f,-1f,2f,2f,2f),
            ModelTransform.pivot(0f,24f,0f));
        return TexturedModelData.of(data, 32, 32);
    }

    @Override public void setAngles(PoopEntity e, float l, float m, float n, float o, float p) {}

    @Override public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        poop.render(matrices, vertices, light, overlay);
    }
}
