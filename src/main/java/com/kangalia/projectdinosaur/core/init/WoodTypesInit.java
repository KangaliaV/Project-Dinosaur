package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.resources.ResourceLocation;

public class WoodTypesInit {
    public static final WoodType PETRIFIED = WoodType.create(new ResourceLocation(ProjectDinosaur.MODID, "petrified").toString());
}
