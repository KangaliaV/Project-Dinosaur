package com.kangalia.projectdinosaur.common.container;

import com.kangalia.projectdinosaur.common.item.DinoScanner;
import com.kangalia.projectdinosaur.core.init.ContainerInit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class DinoScannerContainer extends AbstractContainerMenu {

    Player player;

    public DinoScannerContainer(int containerID, ItemStack stack, Inventory playerInventory, Player playerEntity) {
        super(ContainerInit.DINO_SCANNER_CONTAINER.get(), containerID);
        player = playerEntity;
    }

    public ItemStack getItem() {
        DinoScanner scanner = (DinoScanner) player.getItemInHand(player.getUsedItemHand()).getItem();
        ItemStack scannerStack = player.getItemInHand(player.getUsedItemHand());
        scanner.saveNBT(scannerStack, scanner.getClickedEntity());
        System.out.println("Stack Tag: "+scannerStack.getTag());
        return scannerStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
