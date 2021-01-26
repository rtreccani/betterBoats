package timecop.better.boats;

import net.fabricmc.api.ModInitializer;
// import net.minecraft.item.ItemGroup;
// import net.minecraft.item.Item;
// import net.minecraft.util.registry.Registry;
// import net.minecraft.util.Identifier;

public class betterboats implements ModInitializer {

    //public static final Item DINGHY = new Item(new Item.Settings().group(ItemGroup.TRANSPORTATION));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        
        //Registry.register(Registry.ITEM, new Identifier("betterboats", "Dinghy"), DINGHY);

		System.out.println("Hello Fabric world!");
	}
}
