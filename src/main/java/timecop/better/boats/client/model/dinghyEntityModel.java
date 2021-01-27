package timecop.Better.Boats.client.model;


import com.google.gson.*;
import net.zandra.playerlove.models.CustomModel;
import net.zandra.playerlove.models.bedrock.BedrockModelDeserializer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import java.util.Arrays;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Environment(EnvType.CLIENT)
public class DinghyEntityModel extends CompositeEntityModel<BoatEntity> {

    public static final Gson builder = new GsonBuilder().registerTypeAdapter(CustomModel.class, new BedrockModelDeserializer()).setPrettyPrinting().create();

    public DinghyEntityModel () throws IOException{
        String modelfile = Files.readString(Path.of("dinghy_model.json"));
        CustomModel model = builder.fromJson(modelfile, CustomModel.class); 
    }

    

}
