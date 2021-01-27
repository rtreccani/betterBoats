package net.zandra.playerlove.models;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.nbt.CompoundTag;
import net.zandra.playerlove.models.persistence.TagSavable;

import java.util.ArrayList;

public class CustomModel implements TagSavable {
    public ArrayList<CustomModelPart> all_parts = new ArrayList<CustomModelPart>();


    public int getMaxRenderAmount() {
        return 128;
    }

    public void render(PlayerEntityModel<?> player_model, MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        int left_to_render = getMaxRenderAmount();
        for (CustomModelPart part : all_parts) {
            left_to_render = part.render(left_to_render, matrices, vertices, light, overlay);
        }
    }


    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("version", 1);

        //Put model parts here.
        CompoundTag parts = new CompoundTag();
        parts.putInt("count", all_parts.size());
        for (int i = 0; i < all_parts.size(); i++) {
            CompoundTag nextPart = new CompoundTag();
            nextPart = all_parts.get(i).toTag(nextPart);
            parts.put(Integer.toString(i), nextPart);
        }
        tag.put("parts", parts);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {

        //Put model parts here.
        CompoundTag parts = (CompoundTag)tag.get("parts");
        int count = parts.getInt("count");
        
        for (int i = 0; i < count; i++) {
            CompoundTag nextPartTag = (CompoundTag)parts.get(Integer.toString(i));
            CustomModelPart part = new CustomModelPart();
            part.fromTag(nextPartTag);
            all_parts.add(part);
        }
    }
    
    
}
