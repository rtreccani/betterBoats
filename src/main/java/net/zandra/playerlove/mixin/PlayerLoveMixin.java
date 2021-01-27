package net.zandra.playerlove.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.zandra.playerlove.PlayerLoveMod;
import net.zandra.playerlove.models.CustomModel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntityModel.class)
public class PlayerLoveMixin<T extends LivingEntity> extends BipedEntityModel<T> {
    
    public PlayerLoveMixin(float scale) {
        super(scale);
    }
    
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        CustomModel customModel = PlayerLoveMod.getCurrCustomModel();
        
        if(customModel != null)
            customModel.render((PlayerEntityModel<?>)(Object)this, matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
