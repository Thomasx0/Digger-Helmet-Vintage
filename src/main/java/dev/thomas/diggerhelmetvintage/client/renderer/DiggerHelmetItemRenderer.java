package dev.thomas.diggerhelmetvintage.client.renderer;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

import dev.thomas.diggerhelmetvintage.client.model.DiggerHelmetItemModel;
import dev.thomas.diggerhelmetvintage.item.DiggerHelmetItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DiggerHelmetItemRenderer extends GeoItemRenderer<DiggerHelmetItem> {

    public DiggerHelmetItemRenderer() {
        super(new DiggerHelmetItemModel());
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack itemStack, Object... var3) {
        GL11.glPushMatrix();
        try {
            if (type == ItemRenderType.INVENTORY) {
                GL11.glTranslated(-1, -2.6, 0);
                GL11.glRotatef(90, 0, 1, 0);
            } else if (type == ItemRenderType.EQUIPPED) {
                // Позиция шлема в руке, вид от третьего лица
                GL11.glTranslated(0, -2.0, 0);
            } else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
                GL11.glTranslated(0, -1.5, 0);
            } else {
                GL11.glTranslated(0, -1.5, 0);
            }

            this.render((DiggerHelmetItem) itemStack.getItem(), itemStack);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            GL11.glPopMatrix();
        }
    }
}
