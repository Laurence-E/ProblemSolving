package com.laurence.chatmod.gui.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class GuiReceive extends GuiTutorial {
	
	private EntityPlayer thisPlayer;
	private EntityPlayer fromPlayer;
    
    public GuiReceive(EntityPlayer thisPlayer, EntityPlayer fromPlayer) {
    	super(thisPlayer, fromPlayer);
    	this.thisPlayer = thisPlayer;
		this.fromPlayer = fromPlayer;
	}
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	thisPlayer.addChatComponentMessage(new ChatComponentText("Received!\n"));
		title = "Message > " + fromPlayer.getName() + ">>Received chat!";
		
        drawDefaultBackground();
        //Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        int centerX = (width / 2) - guiWidth / 2;
        int centerY = (height / 2) - guiHeight / 2;
        //drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth, guiHeight);
        //drawString(fontRendererObj, "Tutorial", centerX, centerY, 0x6028ff);
        GlStateManager.pushMatrix();
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.color(1, 1, 1, 1);
            //Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth, guiHeight);
        }
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate((width / 2) - fontRendererObj.getStringWidth(title), centerY + 10, 0);
            GlStateManager.scale(2, 2, 2);
            fontRendererObj.drawString(title, 0, 0, 0x6028ff);
        }
        GlStateManager.popMatrix();
        //super.drawScreen(mouseX, mouseY, partialTicks);
        button1.drawButton(mc, mouseX, mouseY);
        tick.drawButton(mc, mouseX, mouseY);
        ItemStack icon = new ItemStack(Blocks.obsidian);
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(centerX, centerY, 0);
            GlStateManager.scale(2, 2, 2);
            mc.getRenderItem().renderItemAndEffectIntoGUI(icon, 0, 0);
        }
        GlStateManager.popMatrix();
        
        textBox.drawTextBox();
        chatBox.drawCenteredString(fontRendererObj, chat + solution, (width / 2) - 40, (height / 2) - 70, 11184810);
        
        List<String> text = new ArrayList<String>();
        text.add(I18n.format("gui.tooltip"));
        text.add(I18n.format("gui.tooltip2", mc.theWorld.provider.getDimensionName()));
        text.add(icon.getDisplayName());
        drawTooltip(text, mouseX, mouseY, centerX, centerY, 16 * 2, 16 * 2);
    }
	
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
        case BUTTON1:
        	//GuiTutorial.setNames(getNames().remove(fromPlayer.));
        	//mc.displayGuiScreen((GuiScreen)null);
            break;
        case TICK: //When player pressing confirm button
        	screen+=1;
        	textBox.setText(""); //Clear the text box once the user presses the confirm button
            break;
        case BUTTONSMILE:
        	emotion = "happy!";
        	screen=3;
        	//mc.currentScreen.updateScreen();
        	break;
        }
    }
    
}
