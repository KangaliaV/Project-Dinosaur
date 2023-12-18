package com.kangalia.projectdinosaur.core.util.datagen;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PoiTypeTagGen extends PoiTypeTagsProvider {

    public PoiTypeTagGen(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, ProjectDinosaur.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).addOptional(new ResourceLocation(ProjectDinosaur.MODID, "archeologist_poi"));
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).addOptional(new ResourceLocation(ProjectDinosaur.MODID, "paleontologist_poi"));
    }
}
