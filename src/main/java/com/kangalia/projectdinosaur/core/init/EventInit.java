package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID)
public class EventInit {

    private record mystuff (Item sellItem, int sellCost, Item buyItem, int buyCost, int maxUses, int xp, float multiplier) {
        static mystuff create(Item sellitem, int sellCost, Item buyItem, int buyCost, int maxUses, int xp, float multiplier) {
            return new mystuff(sellitem, sellCost, buyItem, buyCost, maxUses, xp, multiplier);
        }

        static mystuff createFragment(Item buyItem) {
            return mystuff.create(Items.EMERALD, 4, buyItem, 1, 10, 3, 0.2f);
        }
    }

    @SubscribeEvent
    public static void addTrades(VillagerTradesEvent event) {

    }

    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        // GENERIC TRADES

        genericTrades.add((pTrader, pRandom) ->  new MerchantOffer(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(ItemInit.PLASTER.get(), 1),
                24, 3, 0.2f));

        // RARE TRADES

        var fragments = List.of(
                mystuff.createFragment(ItemInit.QUATERNARY_FOSSIL_FRAGMENT.get()),
                mystuff.createFragment(ItemInit.NEOGENE_FOSSIL_FRAGMENT.get()),
                mystuff.createFragment(ItemInit.PALEOGENE_FOSSIL_FRAGMENT.get()),
                mystuff.createFragment(ItemInit.CRETACEOUS_FOSSIL_FRAGMENT.get()),
                mystuff.createFragment(ItemInit.JURASSIC_FOSSIL_FRAGMENT.get()),
                mystuff.createFragment(ItemInit.TRIASSIC_FOSSIL_FRAGMENT.get()),
                mystuff.createFragment(ItemInit.PERMIAN_FOSSIL_SPECIMEN.get()),
                mystuff.createFragment(ItemInit.CARBONIFEROUS_FOSSIL_FRAGMENT.get()),
                mystuff.createFragment(ItemInit.DEVONIAN_FOSSIL_FRAGMENT.get()),
                mystuff.createFragment(ItemInit.SILURIAN_FOSSIL_FRAGMENT.get()),
                mystuff.createFragment(ItemInit.ORDOVICIAN_FOSSIL_FRAGMENT.get()),
                mystuff.createFragment(ItemInit.CAMBRIAN_FOSSIL_FRAGMENT.get())
        );

        fragments.forEach(stuff -> rareTrades.add((pTrader, pRandom) ->  new MerchantOffer(
                new ItemStack(Items.EMERALD, 4),
                new ItemStack(stuff.buyItem(), 1),
                10, 3, 0.2f)));

    }
}
