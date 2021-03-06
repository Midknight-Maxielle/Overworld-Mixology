package com.midknight.owmix.screen;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.menu.CrucibleMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CrucibleScreen extends AbstractContainerScreen<CrucibleMenu> {

    // Fields
    private static final ResourceLocation GUI = new ResourceLocation(OverworldMixology.MOD_ID, "textures/gui/crucible_gui.png");
    private static final Rectangle PROGRESS_ARROW = new Rectangle(76,44,27,13);
    private static final Rectangle HEAT_ICON = new Rectangle(79,62,16,17);

    //Constructor Method
    public CrucibleScreen(CrucibleMenu crucibleMenu, Inventory playerInventory, Component title) {
        super(crucibleMenu, playerInventory, title);

    }

    @Override
    public void render(PoseStack matrixStack, final int mouseX, final int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        if(this.minecraft == null) {
            return;
        }
        RenderSystem.setShaderTexture(0, GUI);
        int posX = this.leftPos;
        int posY = this.topPos;
        this.blit(matrixStack, posX, posY, 0, 0, this.getXSize(), this.getYSize());
        int smeltWidth = this.menu.smeltProgress();

        if(this.menu.isHeated()) {
        // renders the Heat Icon //
            this.blit(matrixStack,
                    posX + HEAT_ICON.x,
                    posY + HEAT_ICON.y,
                    176,
                    13,
                    HEAT_ICON.width,
                    HEAT_ICON.height
            );
        }
        // renders the Progress Arrow //
        this.blit(matrixStack,
                posX + PROGRESS_ARROW.x,
                posY + PROGRESS_ARROW.y,
                176,
                0,
                smeltWidth,
                PROGRESS_ARROW.height
        );

    }
}
