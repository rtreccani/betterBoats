
package timecop.Better.Boats.entity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import timecop.Better.Boats.BetterBoats;
import timecop.Better.Boats.mixin.BoatEntityAccessor;


public class DinghyEntity extends BoatEntity{
    public static final Item DINGHY_ITEM = new Item(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1));


    public DinghyEntity(EntityType<? extends BoatEntity> entityType, World world){
        super(entityType, world);
        System.out.println("you summoned a dinghy");
    }

    public DinghyEntity(World world, double x, double y, double z) {
        this(BetterBoats.DINGHY, world);
        this.updatePosition(x, y, z);
        this.setVelocity(Vec3d.ZERO);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        System.out.println("making a dinghyentity using 2nd constructor");
     }


   @Override
   public Item asItem(){
       return(DINGHY_ITEM);
   }
   
    @Override
    public void updatePassengerPosition(Entity passenger) {
        int passengerIndex = this.getPassengerList().indexOf(passenger);
        Vec3d passengerInBoatPosition;
        switch(passengerIndex){
            case 0:
                //AARRR I be the captain of this here vessel
                passengerInBoatPosition = new Vec3d(-3.0, 0, 0.4);
                break;
            case 1:
                passengerInBoatPosition = new Vec3d(-0.6, 0, -0.4);
                break;
            
            case 2:
                passengerInBoatPosition = new Vec3d(-0.6, 0, 0.4);
                break;
            
            case 3:
                passengerInBoatPosition = new Vec3d(0.1, 0, -0.4);
                break;
            default:
                passengerInBoatPosition = new Vec3d(0, 10, 0);
        }
        Vec3d rotatedPassengerInBoatPosition = passengerInBoatPosition.rotateY(-this.yaw * 0.017453292F - 1.5707964F);
        passenger.updatePosition(this.getX() + rotatedPassengerInBoatPosition.x, this.getY() + rotatedPassengerInBoatPosition.y, this.getZ() + rotatedPassengerInBoatPosition.z);
        

        passenger.yaw +=  ((BoatEntityAccessor) this).getYawVelocity();
        passenger.setHeadYaw(passenger.getHeadYaw() +  ((BoatEntityAccessor) this).getYawVelocity());
         this.copyEntityData(passenger);
        if (passenger instanceof AnimalEntity && this.getPassengerList().size() > 1) {
            int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
            passenger.setYaw(((AnimalEntity)passenger).bodyYaw + (float)j);
            passenger.setHeadYaw(passenger.getHeadYaw() + (float)j);
            }
    }

    // private float getYawVelocity(){
    //     return(yawVelocity);
    // }


}
