package timecop.better.boats;


import timecop.better.boats.client.renderer.dinghyEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class betterboatsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(){
    System.out.println("hey woah dispatching a renderer ova here");

    EntityRendererRegistry.INSTANCE.register(betterboats.DINGHY, (dispatcher, context) ->{
        return new dinghyEntityRenderer(dispatcher);
    });
    }

    
}
