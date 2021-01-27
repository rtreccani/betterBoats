package timecop.better.boats;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityType.EntityFactory;
import net.minecraft.util.registry.Registry;
import timecop.better.boats.entity.dinghyEntity;
import net.minecraft.util.Identifier;


public class betterboats implements ModInitializer {

    //public static final Item DINGHY_ITEM = new Item(new Item.Settings().group(ItemGroup.TRANSPORTATION));
    //public static final 

    public static final EntityType<dinghyEntity> DINGHY = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("betterboats","dinghy_entity"),
        FabricEntityTypeBuilder.create(SpawnGroup.MISC, dinghyEntity()).dimensions(EntityDimensions.fixed(10.0f, 10.0f)).build()
    );

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        
        Registry.register(Registry.ITEM, new Identifier("betterboats", "dinghy_item"), dinghyEntity.DINGHY_ITEM);
		System.out.println("Hello Fabric world!");
	}

	private static EntityFactory dinghyEntity() {
		return null;
	}
}

