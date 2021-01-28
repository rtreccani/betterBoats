package timecop.Better.Boats.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import timecop.Better.Boats.entity.DinghyEntity;

	public class DinghyEntityModel extends EntityModel<DinghyEntity> {
        private final ModelPart Boat;
        private final ModelPart backsail;
        private final ModelPart Frontsail;
    
public DinghyEntityModel() {
    textureWidth = 32;
    textureHeight = 16;
    Boat = new ModelPart(this);
    Boat.setPivot(0.0F, 24.0F, 0.0F);
    Boat.setTextureOffset(0, 0).addCuboid(-58.0F, 10.0F, -8.0F, 106.0F, 4.0F, 16.0F, 0.0F, false);
    Boat.setTextureOffset(0, 0).addCuboid(-50.0F, 8.0F, -15.0F, 100.0F, 2.0F, 30.0F, 0.0F, false);
    Boat.setTextureOffset(0, 3).addCuboid(50.0F, 1.0F, -15.0F, 2.0F, 7.0F, 30.0F, 0.0F, false);
    Boat.setTextureOffset(0, 7).addCuboid(-50.0F, 1.0F, -17.0F, 100.0F, 7.0F, 2.0F, 0.0F, false);
    Boat.setTextureOffset(0, 0).addCuboid(-50.0F, 1.0F, 15.0F, 100.0F, 7.0F, 2.0F, 0.0F, false);
    Boat.setTextureOffset(0, 0).addCuboid(-73.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, 0.0F, false);
    Boat.setTextureOffset(0, 0).addCuboid(-56.0F, 0.0F, -15.0F, 6.0F, 10.0F, 30.0F, 0.0F, false);
    Boat.setTextureOffset(0, 0).addCuboid(-62.0F, -1.0F, -8.0F, 6.0F, 11.0F, 16.0F, 0.0F, false);
    Boat.setTextureOffset(0, 0).addCuboid(-61.0F, 10.0F, -4.0F, 3.0F, 3.0F, 8.0F, 0.0F, false);
    Boat.setTextureOffset(0, 0).addCuboid(-67.0F, -1.0F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);

    backsail = new ModelPart(this);
    backsail.setPivot(0.0F, 0.0F, 0.0F);
    Boat.addChild(backsail);
    backsail.setTextureOffset(7, 0).addCuboid(-3.0F, -65.0F, -2.0F, 3.0F, 73.0F, 3.0F, 0.0F, false);
    backsail.setTextureOffset(0, 0).addCuboid(0.0F, -14.0F, -2.0F, 30.0F, 3.0F, 3.0F, 0.0F, false);

    Frontsail = new ModelPart(this);
    Frontsail.setPivot(-19.0F, 0.0F, 3.0F);
    Boat.addChild(Frontsail);
    Frontsail.setTextureOffset(0, -20).addCuboid(-19.0F, -47.0F, -5.0F, 3.0F, 55.0F, 3.0F, 0.0F, false);
    Frontsail.setTextureOffset(0, 3).addCuboid(-16.0F, -14.0F, -5.0F, 21.0F, 3.0F, 3.0F, 0.0F, false);
}

    @Override
    public void setAngles(DinghyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
            //previously the render function, render code was moved to a method below
    }
    @Override
    public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
            Boat.render(matrixStack, buffer, packedLight, packedOverlay);
    }
    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
            bone.pitch = x;
            bone.yaw = y;
            bone.roll = z;
    }

    public ModelPart getBottom(){
        ModelPart bottom = new ModelPart(this);
        bottom.addCuboid(-25.0F, 7.3333F, -8.0F, 50.0F, 1.0F, 16.0F, 0.0F, false);
        return(bottom);
    }

	}