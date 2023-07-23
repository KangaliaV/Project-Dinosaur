package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.resources.ResourceLocation;

public class BlockSetTypesInit {

    public static final BlockSetType PETRIFIED_TYPE = BlockSetType.register(new BlockSetType(ProjectDinosaur.MODID + "petrified"));

    public static final WoodType PETRIFIED_WOOD_TYPE = new WoodType(new ResourceLocation(ProjectDinosaur.MODID, "petrified").toString(), BlockSetTypesInit.PETRIFIED_TYPE);

}
