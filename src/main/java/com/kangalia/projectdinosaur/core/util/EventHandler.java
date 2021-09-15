package com.kangalia.projectdinosaur.core.util;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class EventHandler {

    public static void addStripping() throws NoSuchFieldException, IllegalAccessException {
        Field map = ObfuscationReflectionHelper.findField(AxeItem.class, "STRIPABLES");

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(map, map.getModifiers() & ~Modifier.FINAL);

        map.setAccessible(true);
        Map<Block, Block> strip_map = (Map<Block, Block>) map.get(null);
        HashMap<Block, Block> new_map = new HashMap<>(strip_map);
        new_map.put(BlockInit.PETRIFIED_LOG.get(), BlockInit.STRIPPED_PETRIFIED_LOG.get());
        map.set(null, new_map);
    }
}
