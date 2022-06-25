package com.kangalia.projectdinosaur.common.container;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.item.DinoScanner;
import com.kangalia.projectdinosaur.core.init.ContainerInit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class DinoScannerContainer extends AbstractContainerMenu {

    Player player;
    DinoScanner scanner;
    ItemStack scannerStack;

    public DinoScannerContainer(int containerID, ItemStack stack, Inventory playerInventory, Player playerEntity) {
        super(ContainerInit.DINO_SCANNER_CONTAINER.get(), containerID);
        player = playerEntity;
    }

    public ItemStack getItem() {
        scanner = (DinoScanner) player.getItemInHand(player.getUsedItemHand()).getItem();
        scannerStack = player.getItemInHand(player.getUsedItemHand());
        scanner.saveNBT(scannerStack, scanner.getClickedEntity());
        return scannerStack;
    }

    public PrehistoricEntity getPrehistoricEntity() {
        return scanner.getClickedEntity();
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
