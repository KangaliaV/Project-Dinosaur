package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import com.kangalia.projectdinosaur.core.util.ModEventBusEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTabInit {

    public static CreativeModeTab DINO_BLOCKS;
    public static CreativeModeTab DINO_ITEMS;
    public static CreativeModeTab DINO_CREATURES;
    public static CreativeModeTab DINO_SPAWN_EGGS;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        DINO_BLOCKS = event.registerCreativeModeTab(new ResourceLocation(ProjectDinosaur.MODID, "dino_blocks_tab"),
                builder -> builder.icon(() -> new ItemStack(BlockInit.PETRIFIED_PLANKS.get()))
                        .title(Component.translatable("creativeTab.dino_blocks")).build());
        DINO_ITEMS = event.registerCreativeModeTab(new ResourceLocation(ProjectDinosaur.MODID, "dino_items_tab"),
                builder -> builder.icon(() -> new ItemStack(ItemInit.IRON_CHISEL.get()))
                        .title(Component.translatable("creativeTab.dino_items")).build());
        DINO_CREATURES = event.registerCreativeModeTab(new ResourceLocation(ProjectDinosaur.MODID, "dino_creatures_tab"),
                builder -> builder.icon(() -> new ItemStack(ItemInit.AUSTRALOVENATOR_EGG_FERTILISED.get()))
                        .title(Component.translatable("creativeTab.dino_creatures")).build());
        DINO_SPAWN_EGGS = event.registerCreativeModeTab(new ResourceLocation(ProjectDinosaur.MODID, "dino_spawn_eggs_tab"),
                builder -> builder.icon(() -> new ItemStack(ItemInit.APHANERAMMA_SPAWN_EGG.get()))
                        .title(Component.translatable("creativeTab.dino_spawn_eggs")).build());
    }


}
