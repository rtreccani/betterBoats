package timecop.better.boats.entity;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;


public class dinghyEntity extends BoatEntity{
    public static final Item DINGHY_ITEM = new Item(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1));


    public dinghyEntity(EntityType<? extends BoatEntity> entityType, World world){
        super(entityType, world);
        System.out.println("you summoned a dinghy");
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