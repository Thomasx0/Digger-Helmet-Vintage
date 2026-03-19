package dev.thomas.diggerhelmetvintage;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dev.thomas.diggerhelmetvintage.item.DiggerHelmetItem;

public class CommonProxy {

    public static Item diggerHelmet;

    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        DiggerHelmetVintage.LOG.info(Config.greeting);
        DiggerHelmetVintage.LOG.info("I am MyMod at version " + Tags.VERSION);

        diggerHelmet = new DiggerHelmetItem();
        GameRegistry.registerItem(diggerHelmet, "digger_helmet");
    }

    public void init(FMLInitializationEvent event) {
        registerRecipes();
    }

    private void registerRecipes() {
        GameRegistry.addRecipe(
            new ItemStack(diggerHelmet),
            " T ",
            " H ",
            'T',
            Item.getItemFromBlock(Blocks.torch),
            'H',
            Items.leather_helmet);
    }

    public void postInit(FMLPostInitializationEvent event) {}

    public void serverStarting(FMLServerStartingEvent event) {}
}
