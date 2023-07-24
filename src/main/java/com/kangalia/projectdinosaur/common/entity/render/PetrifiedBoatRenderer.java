package com.kangalia.projectdinosaur.common.entity.render;

import com.google.common.collect.ImmutableMap;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.PetrifiedBoatEntity;
import com.kangalia.projectdinosaur.common.entity.PetrifiedChestBoatEntity;
import com.kangalia.projectdinosaur.core.init.BlockSetTypesInit;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;

// Boat code credited to Deeper And Darker Devs
@SuppressWarnings("NullableProblems")
public class PetrifiedBoatRenderer extends BoatRenderer {
    //private final Map<String, Pair<ResourceLocation, ListModel<Boat>>> BOAT_RESOURCES;
    private final boolean HAS_CHEST;
    private final EntityRendererProvider.Context context;

    public PetrifiedBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context, hasChest);
        //this.BOAT_RESOURCES = ImmutableMap.of(BlockSetTypesInit.PETRIFIED_WOOD_TYPE.name(), Pair.of(new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/" + (hasChest ? "chest_boat" : "boat") + "/" + "petrified.png"), this.createBoatModel(context, hasChest)));
        this.HAS_CHEST = hasChest;
        this.context = context;
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context context, boolean chestBoat) {
        ModelLayerLocation modellayerlocation = chestBoat ?
                new ModelLayerLocation(new ResourceLocation("minecraft", "chest_boat/oak"), "main") :
                new ModelLayerLocation(new ResourceLocation("minecraft", "boat/oak"), "main");
        ModelPart modelpart = context.bakeLayer(modellayerlocation);
        return chestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if (HAS_CHEST) return Pair.of(new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/chest_boat/petrified.png"), this.createBoatModel(context, true));
        return Pair.of(new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/boat/petrified.png"), this.createBoatModel(context, false));
    }
}

