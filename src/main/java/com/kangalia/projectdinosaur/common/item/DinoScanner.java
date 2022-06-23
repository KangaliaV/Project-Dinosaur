package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.common.container.DinoScannerContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class DinoScanner extends Item {

    public static final String TAG_DINO_NAME = "dino";
    public static final String TAG_DINO_DIET = "diet";
    public static final String TAG_DINO_SCHEDULE = "schedule";
    public static final String TAG_DINO_SEX = "sex";

    public DinoScanner(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack pStack = pPlayer.getItemInHand(pHand);
        if (!pLevel.isClientSide) {
            MenuProvider containerProvider = createContainerProvider(pLevel);
            NetworkHooks.openGui((ServerPlayer) pPlayer, containerProvider);
            System.out.println("Open GUI");
        }
        return InteractionResultHolder.sidedSuccess(pStack, pLevel.isClientSide());
    }

    private MenuProvider createContainerProvider(Level world) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return new TranslatableComponent("screen.projectdinosaur.dino_name");
            }

            @javax.annotation.Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                return new DinoScannerContainer(i, playerInventory, playerEntity);
            }
        };
    }
}
