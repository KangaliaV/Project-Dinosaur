package com.kangalia.projectdinosaur.common.tileentity;

import com.kangalia.projectdinosaur.core.init.TileEntitiesInit;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class PetrifiedSignTileEntity extends SignTileEntity {
    public PetrifiedSignTileEntity() {
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return TileEntitiesInit.PETRIFIED_SIGN_ENTITY.get();
    }
}
