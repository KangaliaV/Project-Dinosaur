package com.kangalia.projectdinosaur.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

public class ChiselItem extends Item {

    public ChiselItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        if (EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.ALL_DAMAGE_PROTECTION)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FIRE_PROTECTION)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FALL_PROTECTION)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.BLAST_PROTECTION)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.PROJECTILE_PROTECTION)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.RESPIRATION)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.AQUA_AFFINITY)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.THORNS)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.DEPTH_STRIDER)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FROST_WALKER)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.BINDING_CURSE)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.SOUL_SPEED)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.SHARPNESS)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.SMITE)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.BANE_OF_ARTHROPODS)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.KNOCKBACK)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FIRE_ASPECT)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.MOB_LOOTING)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.SWEEPING_EDGE)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.BLOCK_EFFICIENCY)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.SILK_TOUCH)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.BLOCK_FORTUNE)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.POWER_ARROWS)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.PUNCH_ARROWS)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.INFINITY_ARROWS)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FLAMING_ARROWS)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FISHING_LUCK)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.FISHING_SPEED)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.LOYALTY)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.IMPALING)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.RIPTIDE)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.CHANNELING)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.MULTISHOT)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.QUICK_CHARGE)
                || EnchantmentHelper.getEnchantments(book).containsKey(Enchantments.PIERCING))
        {
            return false;
        }
        return true;
    }
}
