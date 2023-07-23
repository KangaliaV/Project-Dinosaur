package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabInit {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ProjectDinosaur.MODID);

    public static RegistryObject<CreativeModeTab> DINO_BLOCKS = CREATIVE_MODE_TABS.register("dino_blocks_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(BlockInit.PETRIFIED_PLANKS.get()))
            .title(Component.translatable("creativeTab.dino_blocks")).build());
    public static RegistryObject<CreativeModeTab> DINO_ITEMS = CREATIVE_MODE_TABS.register("dino_items_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemInit.IRON_CHISEL.get()))
            .title(Component.translatable("creativeTab.dino_items")).build());
    public static RegistryObject<CreativeModeTab> DINO_CREATURES = CREATIVE_MODE_TABS.register("dino_creatures_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(BlockInit.AUSTRALOVENATOR_EGG_INCUBATED.get()))
            .title(Component.translatable("creativeTab.dino_creatures")).build());
    public static RegistryObject<CreativeModeTab> DINO_SPAWN_EGGS = CREATIVE_MODE_TABS.register("dino_spawn_eggs_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemInit.APHANERAMMA_SPAWN_EGG.get()))
            .title(Component.translatable("creativeTab.dino_spawn_eggs")).build());

    public static void register(IEventBus event) {
        CREATIVE_MODE_TABS.register(event);
    }


}
