package timecop.Better.Boats;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityType.EntityFactory;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import timecop.Better.Boats.entity.DinghyEntity;


public class BetterBoats implements ModInitializer {

    //public static final Item DINGHY_ITEM = new Item(new Item.Settings().group(ItemGroup.TRANSPORTATION));
    //public static final 

    public static final EntityType<DinghyEntity> DINGHY = Registry.register(
        Registry.ENTITY_TYPE,
        new Identifier("betterboats","dinghy_entity"),
        FabricEntityTypeBuilder.create(SpawnGroup.MISC, dinghyEntityFactory()).dimensions(EntityDimensions.fixed(1.3f, 0.4f)).build()
    );

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        
        Registry.register(Registry.ITEM, new Identifier("betterboats", "dinghy_item"), DinghyEntity.DINGHY_ITEM);
		System.out.println("Hello Fabric world!");
	}

	private static EntityFactory<DinghyEntity> dinghyEntityFactory() {
		return DinghyEntity::new;
	}
}

