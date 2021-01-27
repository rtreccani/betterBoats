package timecop.better.boats.entity;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class dinghyEntity extends BoatEntity{
    public dinghyEntity(EntityType<? extends BoatEntity> entityType, World world){
        super(entityType, world);
    }

    public static void onCreateDinghy(){
        System.out.println("fuck");
    }
    
}
