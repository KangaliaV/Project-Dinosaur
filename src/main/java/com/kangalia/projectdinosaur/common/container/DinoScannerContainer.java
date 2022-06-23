package com.kangalia.projectdinosaur.common.container;

import com.kangalia.projectdinosaur.core.init.ContainerInit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

public class DinoScannerContainer extends AbstractContainerMenu {

    public DinoScannerContainer(@Nullable MenuType<?> pMenuType, int pContainerId) {
        super(pMenuType, pContainerId);
    }

    public DinoScannerContainer(int containerID, Inventory playerInventory, Player playerEntity) {
        super(ContainerInit.DINO_SCANNER_CONTAINER.get(), containerID);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
