package com.kangalia.projectdinosaur.common.container;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.item.DinoScanner;
import com.kangalia.projectdinosaur.common.item.GenomeScanner;
import com.kangalia.projectdinosaur.core.init.ContainerInit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class GenomeScannerContainer extends AbstractContainerMenu {

    Player player;
    GenomeScanner scanner;
    ItemStack scannerStack;

    public GenomeScannerContainer(int containerID, ItemStack stack, Inventory playerInventory, Player playerEntity) {
        super(ContainerInit.GENOME_SCANNER_CONTAINER.get(), containerID);
        player = playerEntity;
    }

    public ItemStack getItem() {
        scannerStack = player.getItemInHand(player.getUsedItemHand());
        if (scannerStack.getItem() instanceof GenomeScanner) {
            scanner = (GenomeScanner) scannerStack.getItem();
            scanner.saveNBT(scannerStack, scanner.getClickedEntity());
        }
        return scannerStack;
    }

    public PrehistoricEntity getPrehistoricEntity() {
        return scanner.getClickedEntity();
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
