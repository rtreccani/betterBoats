
package timecop.Better.Boats.entity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import net.minecraft.util.math.Vec3d;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import timecop.Better.Boats.BetterBoats;


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
    
}

// public class dinghyItemClass extends Item{
//     public dinghyItemClass(Settings settings){
//         super(settings);
//     }
// }