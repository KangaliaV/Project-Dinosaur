package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

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
        if(event.getType() == VillagerInit.ARCHEOLOGIST.get()) {
            Random random = new Random();
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            var fragments = List.of(
                    ItemInit.QUATERNARY_FOSSIL_FRAGMENT.get(),
                    ItemInit.NEOGENE_FOSSIL_FRAGMENT.get(),
                    ItemInit.PALEOGENE_FOSSIL_FRAGMENT.get(),
                    ItemInit.CRETACEOUS_FOSSIL_FRAGMENT.get(),
                    ItemInit.JURASSIC_FOSSIL_FRAGMENT.get(),
                    ItemInit.TRIASSIC_FOSSIL_FRAGMENT.get(),
                    ItemInit.PERMIAN_FOSSIL_SPECIMEN.get(),
                    ItemInit.CARBONIFEROUS_FOSSIL_FRAGMENT.get(),
                    ItemInit.DEVONIAN_FOSSIL_FRAGMENT.get(),
                    ItemInit.SILURIAN_FOSSIL_FRAGMENT.get(),
                    ItemInit.ORDOVICIAN_FOSSIL_FRAGMENT.get(),
                    ItemInit.CAMBRIAN_FOSSIL_FRAGMENT.get()
            );
            var specimens = List.of(
                    ItemInit.QUATERNARY_FOSSIL_SPECIMEN.get(),
                    ItemInit.NEOGENE_FOSSIL_SPECIMEN.get(),
                    ItemInit.PALEOGENE_FOSSIL_SPECIMEN.get(),
                    ItemInit.CRETACEOUS_FOSSIL_SPECIMEN.get(),
                    ItemInit.JURASSIC_FOSSIL_SPECIMEN.get(),
                    ItemInit.TRIASSIC_FOSSIL_SPECIMEN.get(),
                    ItemInit.PERMIAN_FOSSIL_SPECIMEN.get(),
                    ItemInit.CARBONIFEROUS_FOSSIL_SPECIMEN.get(),
                    ItemInit.DEVONIAN_FOSSIL_SPECIMEN.get(),
                    ItemInit.SILURIAN_FOSSIL_SPECIMEN.get(),
                    ItemInit.ORDOVICIAN_FOSSIL_SPECIMEN.get(),
                    ItemInit.CAMBRIAN_FOSSIL_SPECIMEN.get()
            );

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.PAPER, 24),
                    new ItemStack(Items.EMERALD, 1),
                    16, 2, 0.05f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.CLAY_BALL, 16),
                    new ItemStack(Items.EMERALD, 1),
                    16, 3, 0.05f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Blocks.WHITE_WOOL.asItem(), 12),
                    new ItemStack(Items.EMERALD, 1),
                    16, 3, 0.05f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ItemInit.PLASTER.get(), 4),
                    8, 2, 0.05f
            ));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(fragments.get(random.nextInt(fragments.size())), 4),
                    new ItemStack(Items.EMERALD, 1),
                    16, 8, 0.2f
            ));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(ItemInit.IRON_CHISEL.get(), 1),
                    4, 6, 0.25f
            ));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 6),
                    new ItemStack(fragments.get(random.nextInt(fragments.size())), 1),
                    12, 8, 0.2f
            ));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(specimens.get(random.nextInt(specimens.size())), 1),
                    new ItemStack(Items.EMERALD, 1),
                    6, 10, 0.25f
            ));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(specimens.get(random.nextInt(specimens.size())), 1),
                    16, 10, 0.25f
            ));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 20),
                    new ItemStack(ItemInit.DIAMOND_CHISEL.get(), 1),
                    2, 12, 0.3f
            ));
        }
        if(event.getType() == VillagerInit.PALEONTOLOGIST.get()) {
            Random random = new Random();
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            var dna = List.of(
                    ItemInit.APHANERAMMA_DNA.get(),
                    ItemInit.ARTHROPLEURA_DNA.get(),
                    ItemInit.AUSTRALOVENATOR_DNA.get(),
                    ItemInit.DIRE_WOLF_DNA.get(),
                    ItemInit.GUIYU_DNA.get(),
                    ItemInit.GASTORNIS_DNA.get(),
                    ItemInit.GORGONOPS_DNA.get(),
                    ItemInit.MEGALODON_DNA.get(),
                    ItemInit.MEGALOGRAPTUS_DNA.get(),
                    ItemInit.SCELIDOSAURUS_DNA.get(),
                    ItemInit.TIKTAALIK_DNA.get(),
                    ItemInit.TRILOBITE_DNA.get()
            );

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Blocks.GLASS_PANE, 11),
                    new ItemStack(Items.EMERALD, 1),
                    16, 4, 0.05f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ItemInit.ROTTEN_EGG.get(), 4),
                    new ItemStack(Items.EMERALD, 1),
                    16, 3, 0.05f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ItemInit.SYRINGE.get(), 4),
                    8, 2, 0.05f
            ));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(dna.get(random.nextInt(dna.size())), 1),
                    new ItemStack(Items.EMERALD, 1),
                    16, 8, 0.2f
            ));

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ItemInit.NUTRIENT_GOO.get(), 4),
                    4, 6, 0.25f
            ));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ItemInit.DINO_SCANNER.get(), 1),
                    12, 8, 0.2f
            ));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    new ItemStack(ItemInit.GENOME_SCANNER.get(), 1),
                    16, 10, 0.25f
            ));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24),
                    new ItemStack(ItemInit.CRYOPORTER.get(), 1),
                    2, 12, 0.3f
            ));
        }
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
