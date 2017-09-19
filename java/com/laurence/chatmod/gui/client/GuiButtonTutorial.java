package com.laurence.chatmod.gui.client;

import com.laurence.chatmod.ChatMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiButtonTutorial extends GuiButton {

    final ResourceLocation texture = new ResourceLocation(ChatMod.MODID, "textures/gui/book.png");

    int buttonWidth = 16;
    int buttonHeight = 16;
    int u; //= 175;
    int v; //= 1;

    public GuiButtonTutorial(int buttonId, int x, int y) {
        super(buttonId, x, y, 16, 16, "");
        
        switch (buttonId) {
        	case 1:
        		this.u = 175;
        		this.v = 1;
        		break;
        	case 2:
        		this.u = 192;
        		this.v = 1;
        		break;
        }
        
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (visible) {
            mc.renderEngine.bindTexture(texture);
            if (mouseX >= this.xPosition && mouseX <= this.xPosition + width && mouseY >= this.yPosition && mouseY <= this.yPosition + height) {
                hovered = true;
            } else {
                hovered = false;
            }
            if (hovered) {
                v = 18;
            } else {
                v = 1;
            }
            drawTexturedModalRect(this.xPosition, this.yPosition, u, v, width, height);
        }
    }
}
