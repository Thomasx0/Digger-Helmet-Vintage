package dev.thomas.diggerhelmetvintage.client.renderer;

import java.util.Arrays;
import java.util.Objects;

import net.geckominecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import dev.thomas.diggerhelmetvintage.client.model.DiggerHelmetModel;
import dev.thomas.diggerhelmetvintage.item.DiggerHelmetItem;
import software.bernie.example.config.ConfigHandler;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.util.GeoUtils;

public class DiggerHelmetArmorRenderer extends GeoArmorRenderer<DiggerHelmetItem> {

    private DiggerHelmetItem currentArmorItem;
    private EntityLivingBase entityLiving;
    private ItemStack itemStack;
    private int armorSlot;

    public DiggerHelmetArmorRenderer() {
        super(new DiggerHelmetModel());

        this.headBone = "armorHead";
        this.bodyBone = "chestplate";
        this.rightArmBone = "rightArm";
        this.leftArmBone = "leftArm";
        this.rightLegBone = "rightLeg";
        this.leftLegBone = "leftLeg";
        this.rightBootBone = "rightBoot";
        this.leftBootBone = "leftBoot";
    }

    @Override
    public void setCurrentItem(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        super.setCurrentItem(entityLiving, itemStack, armorSlot);
        this.entityLiving = entityLiving;
        this.itemStack = itemStack;
        this.armorSlot = armorSlot;
        this.currentArmorItem = (DiggerHelmetItem) itemStack.getItem();
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
        float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.render(ageInTicks);
    }

    @Override
    public void render(float partialTicks) {
        GlStateManager.translate(0.0D, 1.501F, 0.0D);
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.color(1, 1, 1, 1);
        GeoModel model = getGeoModelProvider().getModel(getGeoModelProvider().getModelLocation(currentArmorItem));

        AnimationEvent itemEvent = new AnimationEvent(
            this.currentArmorItem,
            0,
            0,
            0,
            false,
            Arrays.asList(this.itemStack, this.entityLiving, this.armorSlot));
        getGeoModelProvider().setLivingAnimations(currentArmorItem, this.getUniqueID(this.currentArmorItem), itemEvent);
        this.fitToBiped();
        GlStateManager.pushMatrix();
        try {

            if (isSneak) {
                IBone headBone = this.getGeoModelProvider()
                    .getBone(this.headBone);
                headBone.setPositionY(headBone.getPositionY() - 1F);
            }

            Minecraft.getMinecraft().renderEngine.bindTexture(getTextureLocation(currentArmorItem));
            Color renderColor = getRenderColor(currentArmorItem, partialTicks);
            render(
                model,
                currentArmorItem,
                partialTicks,
                (float) renderColor.getRed() / 255f,
                (float) renderColor.getGreen() / 255f,
                (float) renderColor.getBlue() / 255f,
                (float) renderColor.getAlpha() / 255);
        } catch (Exception e) {
            if (ConfigHandler.debugPrintStacktraces) {
                e.printStackTrace();
            }
        } finally {
            GlStateManager.popMatrix();
        }
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
        GlStateManager.translate(0.0D, -1.501F, 0.0D);
    }

    private void fitToBiped() {
        IBone headBone = this.getGeoModelProvider()
            .getBone(this.headBone);
        try {
            GeoUtils.copyRotations(this.bipedHead, headBone);
            headBone.setPositionX(this.bipedHead.rotationPointX);
            headBone.setPositionY(this.bipedHead.rotationPointY);
            headBone.setPositionZ(this.bipedHead.rotationPointZ);
        } catch (Exception e) {
            throw new RuntimeException("Could not find an armor bone.", e);
        }
    }

    @Override
    public Integer getUniqueID(DiggerHelmetItem animatable) {
        return Objects.hash(
            this.armorSlot,
            itemStack.getItem(),
            itemStack.stackSize,
            itemStack.hasTagCompound() ? itemStack.getTagCompound()
                .toString() : 1,
            this.entityLiving.getUniqueID()
                .toString());
    }
}
