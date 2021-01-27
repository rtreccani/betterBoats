package net.zandra.playerlove.models;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.client.util.math.Vector4f;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;
import net.zandra.playerlove.models.persistence.TagSavable;

import java.util.Random;

public class CustomModelPart implements TagSavable {
    private float textureWidth;
    private float textureHeight;
    private int textureOffsetU;
    private int textureOffsetV;
    public float pivotX;
    public float pivotY;
    public float pivotZ;
    public float pitch;
    public float yaw;
    public float roll;
    public boolean mirror;
    public boolean visible;
    private final ObjectList<CustomModelPart.Cuboid> cuboids;
    private final ObjectList<CustomModelPart> children;

    public CustomModelPart(int textureWidth, int textureHeight, int textureOffsetU, int textureOffsetV) {
        this.textureWidth = 64.0F;
        this.textureHeight = 64.0F;
        this.visible = true;
        this.cuboids = new ObjectArrayList();
        this.children = new ObjectArrayList();
        this.setTextureSize(textureWidth, textureHeight);
        this.setTextureOffset(textureOffsetU, textureOffsetV);
    }

    public CustomModelPart() {
        this.textureWidth = 64.0F;
        this.textureHeight = 64.0F;
        this.visible = true;
        this.cuboids = new ObjectArrayList();
        this.children = new ObjectArrayList();
    }

    public CustomModelPart copy() {
        CustomModelPart modelPart = new CustomModelPart();
        modelPart.copyPositionAndRotation(this);
        return modelPart;
    }

    public void copyPositionAndRotation(CustomModelPart modelPart) {
        this.pitch = modelPart.pitch;
        this.yaw = modelPart.yaw;
        this.roll = modelPart.roll;
        this.pivotX = modelPart.pivotX;
        this.pivotY = modelPart.pivotY;
        this.pivotZ = modelPart.pivotZ;
    }

    public void addChild(CustomModelPart part) {
        this.children.add(part);
    }

    public CustomModelPart setTextureOffset(int textureOffsetU, int textureOffsetV) {
        this.textureOffsetU = textureOffsetU;
        this.textureOffsetV = textureOffsetV;
        return this;
    }

    public CustomModelPart addCuboid(String name, float x, float y, float z, int sizeX, int sizeY, int sizeZ, float extra, int textureOffsetU, int textureOffsetV) {
        this.setTextureOffset(textureOffsetU, textureOffsetV);
        this.addCuboid(this.textureOffsetU, this.textureOffsetV, x, y, z, (float) sizeX, (float) sizeY, (float) sizeZ, extra, extra, extra, this.mirror, false);
        return this;
    }

    public CustomModelPart addCuboid(float x, float y, float z, float sizeX, float sizeY, float sizeZ) {
        this.addCuboid(this.textureOffsetU, this.textureOffsetV, x, y, z, sizeX, sizeY, sizeZ, 0.0F, 0.0F, 0.0F, this.mirror, false);
        return this;
    }

    public CustomModelPart addCuboid(float x, float y, float z, float sizeX, float sizeY, float sizeZ, boolean mirror) {
        this.addCuboid(this.textureOffsetU, this.textureOffsetV, x, y, z, sizeX, sizeY, sizeZ, 0.0F, 0.0F, 0.0F, mirror, false);
        return this;
    }

    public void addCuboid(float x, float y, float z, float sizeX, float sizeY, float sizeZ, float extra) {
        this.addCuboid(this.textureOffsetU, this.textureOffsetV, x, y, z, sizeX, sizeY, sizeZ, extra, extra, extra, this.mirror, false);
    }

    public void addCuboid(float x, float y, float z, float sizeX, float sizeY, float sizeZ, float extraX, float extraY, float extraZ) {
        this.addCuboid(this.textureOffsetU, this.textureOffsetV, x, y, z, sizeX, sizeY, sizeZ, extraX, extraY, extraZ, this.mirror, false);
    }

    public void addCuboid(float x, float y, float z, float sizeX, float sizeY, float sizeZ, float extra, boolean mirror) {
        this.addCuboid(this.textureOffsetU, this.textureOffsetV, x, y, z, sizeX, sizeY, sizeZ, extra, extra, extra, mirror, false);
    }

    private void addCuboid(int u, int v, float x, float y, float z, float sizeX, float sizeY, float sizeZ, float extraX, float extraY, float extraZ, boolean mirror, boolean bl) {
        this.cuboids.add(new CustomModelPart.Cuboid(u, v, x, y, z, sizeX, sizeY, sizeZ, extraX, extraY, extraZ, mirror, this.textureWidth, this.textureHeight));
    }

    public void setPivot(float x, float y, float z) {
        this.pivotX = x;
        this.pivotY = y;
        this.pivotZ = z;
    }

    public int render(int left, MatrixStack matrices, VertexConsumer vertices, int light, int overlay) {
        return this.render(left, matrices, vertices, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public int render(int left, MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

        if (left == 0)
            return left;

        if (this.visible) {
            if (!this.cuboids.isEmpty() || !this.children.isEmpty()) {
                matrices.push();
                this.rotate(matrices);
                left = this.renderCuboids(left, matrices.peek(), vertices, light, overlay, red, green, blue, alpha);
                ObjectListIterator var9 = this.children.iterator();
                while (var9.hasNext()) {
                    CustomModelPart modelPart = (CustomModelPart) var9.next();

                    matrices.push();
                    matrices.translate((double) (-this.pivotX / 16.0F), (double) (-this.pivotY / 16.0F), (double) (-this.pivotZ / 16.0F));
                    left = modelPart.render(left, matrices, vertices, light, overlay, red, green, blue, alpha);
                    matrices.pop();
                }

                matrices.pop();
            }
        }
        return left;
    }

    public void rotate(MatrixStack matrix) {
        matrix.translate((double) (this.pivotX / 16.0F), (double) (this.pivotY / 16.0F), (double) (this.pivotZ / 16.0F));
        if (this.roll != 0.0F) {
            matrix.multiply(Vector3f.POSITIVE_Z.getRadialQuaternion(this.roll));
        }

        if (this.yaw != 0.0F) {
            matrix.multiply(Vector3f.POSITIVE_Y.getRadialQuaternion(this.yaw));
        }

        if (this.pitch != 0.0F) {
            matrix.multiply(Vector3f.POSITIVE_X.getRadialQuaternion(this.pitch));
        }

    }

    private int renderCuboids(int left, MatrixStack.Entry matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        Matrix4f matrix4f = matrices.getModel();
        Matrix3f matrix3f = matrices.getNormal();
        ObjectListIterator var11 = this.cuboids.iterator();

        while (var11.hasNext()) {

            //Decrease count.
            if (left == 0)
                return left;
            left--;

            CustomModelPart.Cuboid cuboid = (CustomModelPart.Cuboid) var11.next();
            CustomModelPart.Quad[] var13 = cuboid.sides;
            int var14 = var13.length;

            for (int var15 = 0; var15 < var14; ++var15) {
                CustomModelPart.Quad quad = var13[var15];
                Vector3f vector3f = quad.direction.copy();
                vector3f.transform(matrix3f);
                float f = vector3f.getX();
                float g = vector3f.getY();
                float h = vector3f.getZ();

                for (int i = 0; i < 4; ++i) {
                    CustomModelPart.Vertex vertex = quad.vertices[i];
                    float j = vertex.pos.getX() / 16.0F;
                    float k = vertex.pos.getY() / 16.0F;
                    float l = vertex.pos.getZ() / 16.0F;
                    Vector4f vector4f = new Vector4f(j, k, l, 1.0F);
                    vector4f.transform(matrix4f);
                    vertexConsumer.vertex(vector4f.getX(), vector4f.getY(), vector4f.getZ(), red, green, blue, alpha, vertex.u, vertex.v, overlay, light, f, g, h);
                }
            }
        }

        return left;
    }

    public CustomModelPart setTextureSize(int width, int height) {
        this.textureWidth = (float) width;
        this.textureHeight = (float) height;
        return this;
    }

    public CustomModelPart.Cuboid getRandomCuboid(Random random) {
        return (CustomModelPart.Cuboid) this.cuboids.get(random.nextInt(this.cuboids.size()));
    }

    @Override
    public CompoundTag toTag(CompoundTag part_tag) {
        part_tag.putFloat("textureWidth", textureWidth);
        part_tag.putFloat("textureHeight", textureHeight);
        part_tag.putFloat("textureOffsetU", textureOffsetU);
        part_tag.putFloat("textureOffsetV", textureOffsetV);
        part_tag.putFloat("pivotX", pivotX);
        part_tag.putFloat("pivotY", pivotY);
        part_tag.putFloat("pivotZ", pivotZ);
        part_tag.putFloat("pitch", pitch);
        part_tag.putFloat("yaw", yaw);
        part_tag.putFloat("roll", roll);
        part_tag.putBoolean("visible", visible);
        part_tag.putBoolean("mirror", mirror);


        CompoundTag children_tag = new CompoundTag();
        children_tag.putInt("count", children.size());
        for (int i = 0; i < children.size(); i++) {
            children_tag.put(Integer.toString(i), children.get(i).toTag(children_tag));
        }
        part_tag.put("child_parts", children_tag);


        CompoundTag cuboids_tag = new CompoundTag();
        cuboids_tag.putInt("count", cuboids.size());
        for (int i = 0; i < cuboids.size(); i++) {
            CompoundTag nextTag = new CompoundTag();
            cuboids_tag.put(Integer.toString(i), cuboids.get(i).toTag(nextTag));
        }
        part_tag.put("cuboids", cuboids_tag);

        return part_tag;
    }

    @Override
    public void fromTag(CompoundTag part_tag) {
        
    }

    @Environment(EnvType.CLIENT)
    static class Vertex {
        public final Vector3f pos;
        public final float u;
        public final float v;

        public Vertex(float x, float y, float z, float u, float v) {
            this(new Vector3f(x, y, z), u, v);
        }

        public CustomModelPart.Vertex remap(float u, float v) {
            return new CustomModelPart.Vertex(this.pos, u, v);
        }

        public Vertex(Vector3f pos, float u, float v) {
            this.pos = pos;
            this.u = u;
            this.v = v;
        }
    }

    @Environment(EnvType.CLIENT)
    static class Quad {
        public final CustomModelPart.Vertex[] vertices;
        public final Vector3f direction;

        public Quad(CustomModelPart.Vertex[] vertices, float u1, float v1, float u2, float v2, float squishU, float squishV, boolean flip, Direction direction) {
            this.vertices = vertices;
            float f = 0.0F / squishU;
            float g = 0.0F / squishV;
            vertices[0] = vertices[0].remap(u2 / squishU - f, v1 / squishV + g);
            vertices[1] = vertices[1].remap(u1 / squishU + f, v1 / squishV + g);
            vertices[2] = vertices[2].remap(u1 / squishU + f, v2 / squishV - g);
            vertices[3] = vertices[3].remap(u2 / squishU - f, v2 / squishV - g);
            if (flip) {
                int i = vertices.length;

                for (int j = 0; j < i / 2; ++j) {
                    CustomModelPart.Vertex vertex = vertices[j];
                    vertices[j] = vertices[i - 1 - j];
                    vertices[i - 1 - j] = vertex;
                }
            }

            this.direction = direction.getUnitVector();
            if (flip) {
                this.direction.multiplyComponentwise(-1.0F, 1.0F, 1.0F);
            }

        }
    }

    @Environment(EnvType.CLIENT)
    public static class Cuboid implements TagSavable {
        private CustomModelPart.Quad[] sides;
        public float minX;
        public float minY;
        public float minZ;
        public float maxX;
        public float maxY;
        public float maxZ;

        public int u;
        public int v;
        public float x;
        public float y;
        public float z;
        public float sizeX;
        public float sizeY;
        public float sizeZ;
        public float extraX;
        public float extraY;
        public float extraZ;
        boolean mirror;
        public float textureWidth;
        public float textureHeight;

        public Cuboid(int u, int v, float x, float y, float z, float sizeX, float sizeY, float sizeZ, float extraX, float extraY, float extraZ, boolean mirror, float textureWidth, float textureHeight) {
            build(u, v, x, y, z, sizeX, sizeY, sizeZ, extraX, extraY, extraZ, mirror, textureWidth, textureHeight);
        }
        
        private void build(int u, int v, float x, float y, float z, float sizeX, float sizeY, float sizeZ, float extraX, float extraY, float extraZ, boolean mirror, float textureWidth, float textureHeight) {
            this.u = u;
            this.v = v;
            this.x = x;
            this.y = y;
            this.z = z;
            this.sizeX = sizeX; 
            this.sizeY = sizeY;
            this.sizeZ = sizeZ;
            this.extraX = extraX;
            this.extraY = extraY;
            this.extraZ = extraZ;
            this.mirror = mirror;
            this.textureWidth = textureWidth;
            this.textureHeight = textureHeight;
            this.minX = x;
            this.minY = y;
            this.minZ = z;
            this.maxX = x + sizeX;
            this.maxY = y + sizeY;
            this.maxZ = z + sizeZ;
            this.sides = new CustomModelPart.Quad[6];
            float f = x + sizeX;
            float g = y + sizeY;
            float h = z + sizeZ;
            x -= extraX;
            y -= extraY;
            z -= extraZ;
            f += extraX;
            g += extraY;
            h += extraZ;
            if (mirror) {
                float i = f;
                f = x;
                x = i;
            }

            CustomModelPart.Vertex vertex = new CustomModelPart.Vertex(x, y, z, 0.0F, 0.0F);
            CustomModelPart.Vertex vertex2 = new CustomModelPart.Vertex(f, y, z, 0.0F, 8.0F);
            CustomModelPart.Vertex vertex3 = new CustomModelPart.Vertex(f, g, z, 8.0F, 8.0F);
            CustomModelPart.Vertex vertex4 = new CustomModelPart.Vertex(x, g, z, 8.0F, 0.0F);
            CustomModelPart.Vertex vertex5 = new CustomModelPart.Vertex(x, y, h, 0.0F, 0.0F);
            CustomModelPart.Vertex vertex6 = new CustomModelPart.Vertex(f, y, h, 0.0F, 8.0F);
            CustomModelPart.Vertex vertex7 = new CustomModelPart.Vertex(f, g, h, 8.0F, 8.0F);
            CustomModelPart.Vertex vertex8 = new CustomModelPart.Vertex(x, g, h, 8.0F, 0.0F);
            float j = (float) u;
            float k = (float) u + sizeZ;
            float l = (float) u + sizeZ + sizeX;
            float m = (float) u + sizeZ + sizeX + sizeX;
            float n = (float) u + sizeZ + sizeX + sizeZ;
            float o = (float) u + sizeZ + sizeX + sizeZ + sizeX;
            float p = (float) v;
            float q = (float) v + sizeZ;
            float r = (float) v + sizeZ + sizeY;
            this.sides[2] = new CustomModelPart.Quad(new CustomModelPart.Vertex[]{vertex6, vertex5, vertex, vertex2}, k, p, l, q, textureWidth, textureHeight, mirror, Direction.DOWN);
            this.sides[3] = new CustomModelPart.Quad(new CustomModelPart.Vertex[]{vertex3, vertex4, vertex8, vertex7}, l, q, m, p, textureWidth, textureHeight, mirror, Direction.UP);
            this.sides[1] = new CustomModelPart.Quad(new CustomModelPart.Vertex[]{vertex, vertex5, vertex8, vertex4}, j, q, k, r, textureWidth, textureHeight, mirror, Direction.WEST);
            this.sides[4] = new CustomModelPart.Quad(new CustomModelPart.Vertex[]{vertex2, vertex, vertex4, vertex3}, k, q, l, r, textureWidth, textureHeight, mirror, Direction.NORTH);
            this.sides[0] = new CustomModelPart.Quad(new CustomModelPart.Vertex[]{vertex6, vertex2, vertex3, vertex7}, l, q, n, r, textureWidth, textureHeight, mirror, Direction.EAST);
            this.sides[5] = new CustomModelPart.Quad(new CustomModelPart.Vertex[]{vertex5, vertex6, vertex7, vertex8}, n, q, o, r, textureWidth, textureHeight, mirror, Direction.SOUTH);
        }

        @Override
        public CompoundTag toTag(CompoundTag tag) {

            return null;
        }

        @Override
        public void fromTag(CompoundTag tag) {
            
        }
    }
}
