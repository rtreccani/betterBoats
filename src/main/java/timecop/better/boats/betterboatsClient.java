package timecop.Better.Boats;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import timecop.Better.Boats.client.renderer.DinghyEntityRenderer;

@Environment(EnvType.CLIENT)
public class BetterBoatsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(){
    System.out.println("hey woah dispatching a renderer ova here");

    EntityRendererRegistry.INSTANCE.register(BetterBoats.DINGHY, (dispatcher, context) ->{
        return new DinghyEntityRenderer(dispatcher);
    });
    }
}
