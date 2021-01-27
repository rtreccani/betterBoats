package net.zandra.playerlove.models.bedrock;

import com.google.gson.*;
import net.minecraft.util.math.MathHelper;
import net.zandra.playerlove.models.CustomModel;
import net.zandra.playerlove.models.CustomModelPart;

import java.lang.reflect.Type;
import java.util.HashMap;

public class BedrockModelDeserializer implements JsonDeserializer<CustomModel> {

    @Override
    public CustomModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        CustomModel model = new CustomModel();

        JsonObject obj = json.getAsJsonObject();
        JsonArray geo = obj.get("minecraft:geometry").getAsJsonArray();

        for (int i = 0; i < geo.size(); i++) {
            JsonObject part = geo.get(i).getAsJsonObject();
            JsonObject description = part.get("description").getAsJsonObject();
            JsonArray groups = part.get("bones").getAsJsonArray();

            HashMap<String, CustomModelPart> group_map = new HashMap<String, CustomModelPart>();
            
            for (int j = 0; j < groups.size(); j++) {
                JsonElement groupEl = groups.get(j);
                JsonObject group = groupEl.getAsJsonObject();
                String name = group.get("name").getAsString();
                JsonArray pivot = group.get("pivot").getAsJsonArray();
                JsonArray cubes = group.getAsJsonArray("cubes");
                JsonArray rotation = null;
                if (group.has("rotation"))
                    rotation = group.get("rotation").getAsJsonArray();

                CustomModelPart group_part = new CustomModelPart();
                group_part.setPivot(
                        pivot.get(0).getAsFloat(),
                        -pivot.get(1).getAsFloat(),
                        pivot.get(2).getAsFloat()
                );
                if (rotation != null) {
                    group_part.pitch = (float) Math.toRadians(MathHelper.wrapDegrees(rotation.get(0).getAsFloat()));
                    group_part.yaw = (float) Math.toRadians(MathHelper.wrapDegrees(rotation.get(1).getAsFloat()));
                    group_part.roll = (float) Math.toRadians(MathHelper.wrapDegrees(rotation.get(2).getAsFloat()));
                }
                
                //Cubes
                for (int k = 0; k < cubes.size(); k++) {
                    JsonObject cube = cubes.get(k).getAsJsonObject();
                    JsonArray origin = cube.get("origin").getAsJsonArray();
                    JsonArray size = cube.get("size").getAsJsonArray();
                    JsonArray uv = cube.get("uv").getAsJsonArray();

                    group_part.addCuboid(
                            origin.get(0).getAsFloat() - group_part.pivotX,
                            -origin.get(1).getAsFloat() - size.get(1).getAsFloat() - group_part.pivotY,
                            origin.get(2).getAsFloat() - group_part.pivotZ,
                            size.get(0).getAsFloat(),
                            size.get(1).getAsFloat(),
                            size.get(2).getAsFloat(),
                            0,
                            uv.get(0).getAsFloat(),
                            uv.get(0).getAsFloat()
                    );
                }

                group_map.put(name, group_part);
                if(group.has("parent")){
                    CustomModelPart parent_part = group_map.get(group.get("parent").getAsString());
                    parent_part.addChild(group_part);
                } else {
                    model.all_parts.add(group_part);
                }
            }
        }
        
        return model;
    }
}
