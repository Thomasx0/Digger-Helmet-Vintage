package dev.thomas.diggerhelmetvintage;

import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dev.thomas.diggerhelmetvintage.client.renderer.DiggerHelmetArmorRenderer;
import dev.thomas.diggerhelmetvintage.client.renderer.DiggerHelmetItemRenderer;
import dev.thomas.diggerhelmetvintage.item.DiggerHelmetItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        GeoArmorRenderer.registerArmorRenderer(DiggerHelmetItem.class, new DiggerHelmetArmorRenderer());
        MinecraftForgeClient.registerItemRenderer(diggerHelmet, new DiggerHelmetItemRenderer());
    }
}
