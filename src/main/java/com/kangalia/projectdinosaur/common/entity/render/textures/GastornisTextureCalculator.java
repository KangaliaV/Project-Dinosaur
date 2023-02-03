package com.kangalia.projectdinosaur.common.entity.render.textures;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GastornisTextureCalculator {

    protected static final Logger LOGGER = LogManager.getLogger();

    private static final String DEFAULT_PATH = "textures/entity/mob/dino/gastornis/gastornis";

    private ResourceLocation CM;
    private ResourceLocation FC;
    private ResourceLocation UC;
    private ResourceLocation PC;
    private ResourceLocation HC;
    private ResourceLocation SC;
    private ResourceLocation BC;

    public void setTexturePath(int gene, GastornisEntity entity) {
        if (gene == 0) {
            CM = new ResourceLocation(ProjectDinosaur.MODID, DEFAULT_PATH + switch (entity.getColourMorph()) { //Feather Colour
                case 1 -> "_albino";
                case 2 -> "_melanistic";
                default -> "";
            });
        } else if (gene == 1) {
            FC = new ResourceLocation(ProjectDinosaur.MODID, DEFAULT_PATH + switch (entity.getGeneDominance(1)) { //Feather Colour
                case 1 -> "_black";
                case 2 -> "_cream";
                case 3 -> "_red";
                case 4 -> "_grey";
                case 5 -> "_white";
                default -> "_brown";
            });
        } else if (gene == 2) {
            UC = new ResourceLocation(ProjectDinosaur.MODID, DEFAULT_PATH + "_underside" + switch (entity.getGeneDominance(2)) { //Underside Colour
                case 1 -> "_grey";
                case 2 -> "_white";
                default -> "_cream";
            });

        } else if (gene == 3) {
            PC = new ResourceLocation(ProjectDinosaur.MODID, DEFAULT_PATH + "_pattern" + switch (entity.getGeneDominance(3)) { //Pattern Colour
                case 1 -> "_brown";
                case 2 -> "_cream";
                case 3 -> "_red";
                case 4 -> "_grey";
                case 5 -> "_white";
                default -> "_black";
            });
        } else if (gene == 4) {
            if (entity.getGender() == 0) {
                HC = new ResourceLocation(ProjectDinosaur.MODID, DEFAULT_PATH + "_highlight" + switch (entity.getGeneDominance(4)) { //Highlight Colour *only on males*
                    case 1 -> "_red";
                    case 2 -> "_green";
                    default -> "_blue";
                });
            }
        } else if (gene == 5) {
            SC = new ResourceLocation(ProjectDinosaur.MODID, DEFAULT_PATH + "_skin" + switch (entity.getGeneDominance(5)) { //Skin Colour
                case 1 -> "_brown";
                case 2 -> "_grey";
                case 3 -> "_black";
                default -> "_cream";
            });
        } else {
            BC = new ResourceLocation(ProjectDinosaur.MODID, DEFAULT_PATH + "_beak" + switch (entity.getGeneDominance(1)) { //Beak Colour
                case 1 -> "_orange";
                case 2 -> "_cream";
                default -> "_yellow";
            });
        }
    }
}
