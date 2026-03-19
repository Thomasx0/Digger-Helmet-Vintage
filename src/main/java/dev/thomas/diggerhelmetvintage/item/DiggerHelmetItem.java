package dev.thomas.diggerhelmetvintage.item;

import net.minecraft.item.ItemArmor;

import com.gtnewhorizons.angelica.api.IDynamicLightProducer;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class DiggerHelmetItem extends GeoArmorItem implements IAnimatable, IDynamicLightProducer {

    private final AnimationFactory factory = new AnimationFactory(this);

    public DiggerHelmetItem() {
        super(ItemArmor.ArmorMaterial.CLOTH, 0, 0);
        this.setUnlocalizedName("digger_helmet");
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController()
            .setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int getLuminance() {
        return 14;
    }
}
