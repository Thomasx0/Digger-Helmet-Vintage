package dev.thomas.diggerhelmetvintage.client.model;

import net.minecraft.util.ResourceLocation;

import dev.thomas.diggerhelmetvintage.DiggerHelmetVintage;
import dev.thomas.diggerhelmetvintage.item.DiggerHelmetItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DiggerHelmetModel extends AnimatedGeoModel<DiggerHelmetItem> {

    @Override
    public ResourceLocation getModelLocation(DiggerHelmetItem object) {
        return new ResourceLocation(DiggerHelmetVintage.MODID, "geo/digger_helmet.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DiggerHelmetItem object) {
        return new ResourceLocation(DiggerHelmetVintage.MODID, "textures/item/digger_helmet.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DiggerHelmetItem animatable) {
        return new ResourceLocation(DiggerHelmetVintage.MODID, "animations/digger_helmet.animation.json");
    }
}
