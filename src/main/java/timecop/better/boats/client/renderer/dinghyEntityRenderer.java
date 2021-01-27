package timecop.Better.Boats.client.renderer;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;
import timecop.Better.Boats.entity.DinghyEntity;
import timecop.Better.Boats.client.model.DinghyEntityModel;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;


public class DinghyEntityRenderer extends EntityRenderer<DinghyEntity>{

    public DinghyEntityRenderer(EntityRenderDispatcher entityRenderDispatcher){
        super(entityRenderDispatcher);
    }
    public final DinghyEntityModel model = new DinghyEntityModel();



    public void render(DinghyEntity dinghyEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - f));
        float h = (float)dinghyEntity.getDamageWobbleTicks() - g;
        float j = dinghyEntity.getDamageWobbleStrength() - g;
        if (j < 0.0F) {
           j = 0.0F;
        }
  
        if (h > 0.0F) {
           matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(MathHelper.sin(h) * h * j / 10.0F * (float)dinghyEntity.getDamageWobbleSide()));
        }
  
        float k = dinghyEntity.interpolateBubbleWobble(g);
        if (!MathHelper.approximatelyEquals(k, 0.0F)) {
           matrixStack.multiply(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), dinghyEntity.interpolateBubbleWobble(g), true));
        }
  
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
        this.model.setAngles(dinghyEntity, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(this.getTexture(dinghyEntity)));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!dinghyEntity.isSubmergedInWater()) {
           VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getWaterMask());
           this.model.getBottom().render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV);
        }
  
        matrixStack.pop();
        super.render(dinghyEntity, f, g, matrixStack, vertexConsumerProvider, i);
     }

    @Override
    public Identifier getTexture(DinghyEntity entity){
        return new Identifier("betterboats", "textures/entity/dinghy/dinghy.png");
    }

    
}
