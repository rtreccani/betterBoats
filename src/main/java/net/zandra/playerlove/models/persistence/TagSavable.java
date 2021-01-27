package net.zandra.playerlove.models.persistence;

import net.minecraft.nbt.CompoundTag;

public interface TagSavable {
    CompoundTag toTag(CompoundTag tag);
    void fromTag(CompoundTag tag);
}
