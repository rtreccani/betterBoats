package timecop.Better.Boats.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import timecop.Better.Boats.entity.DinghyEntity;

	public class DinghyEntityBBTWO extends EntityModel<DinghyEntity> {
    private final ModelPart Boat;
	private final ModelPart backsail;
	private final ModelPart Frontsail;
    public DinghyEntityBBTWO() {
		textureWidth = 16;
		textureHeight = 16;
		Boat = new ModelPart(this);
		Boat.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(Boat, 0.0F, -1.5708F, 0.0F);
		Boat.setTextureOffset(0, 0).addCuboid(-8.0F, -1.0F, -25.0F, 16.0F, 1.0F, 50.0F, 0.0F, false);
		Boat.setTextureOffset(0, 3).addCuboid(-8.0F, -7.0F, -25.0F, 15.0F, 7.0F, 1.0F, 0.0F, false);
		Boat.setTextureOffset(0, 0).addCuboid(-9.0F, -7.0F, -25.0F, 1.0F, 7.0F, 50.0F, 0.0F, false);
		Boat.setTextureOffset(0, 3).addCuboid(-8.0F, -7.0F, 24.0F, 15.0F, 7.0F, 1.0F, 0.0F, false);
		Boat.setTextureOffset(0, 0).addCuboid(7.0F, -7.0F, -25.0F, 1.0F, 7.0F, 50.0F, 0.0F, false);

		backsail = new ModelPart(this);
		backsail.setPivot(0.0F, 0.0F, 0.0F);
		Boat.addChild(backsail);
		backsail.setTextureOffset(7, 0).addCuboid(-1.0F, -42.0F, -1.0F, 1.0F, 42.0F, 1.0F, 0.0F, false);
		backsail.setTextureOffset(0, 0).addCuboid(-1.0F, -10.0F, -18.0F, 1.0F, 1.0F, 17.0F, 0.0F, false);

		Frontsail = new ModelPart(this);
		Frontsail.setPivot(0.0F, 0.0F, 3.0F);
		Boat.addChild(Frontsail);
		Frontsail.setTextureOffset(32, 15).addCuboid(-1.0F, -35.0F, 13.0F, 1.0F, 35.0F, 1.0F, 0.0F, false);
		Frontsail.setTextureOffset(0, 0).addCuboid(-1.0F, -10.0F, 0.0F, 1.0F, 1.0F, 13.0F, 0.0F, false);
}
public ModelPart getBottom(){
    ModelPart Bottom = new ModelPart(this);
    Bottom.setTextureOffset(0, 0).addCuboid(-8.0F, -1.0F, -25.0F, 16.0F, 1.0F, 50.0F, 0.0F, false);
    return(Bottom);
}

@Override
public void setAngles(DinghyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //System.out.println("dunno");
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

	}