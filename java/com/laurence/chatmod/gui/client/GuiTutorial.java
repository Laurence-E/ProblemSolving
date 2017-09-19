package com.laurence.chatmod.gui.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.laurence.chatmod.ChatMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiTutorial extends GuiScreen {
    
	//private static List<String> names = new ArrayList();
    
	private EntityPlayer fromPlayer; //Variable for the player who wants to problem solve.
	private EntityPlayer toPlayer; //Variable for the player who they want to problem solve with.
    
	/*
	 * Main method for this Gui
	 * Gets the player who sent the command and the player who they want to problem solve with
	 */
    public GuiTutorial(EntityPlayer entityplayer, EntityPlayer sender) {
		this.toPlayer = entityplayer; //Who are they sending a request to
		this.fromPlayer = sender; //Who typed the (/hey) command
	}
    
    //final ResourceLocation texture = new ResourceLocation(ChatMod.MODID, "textures/gui/bg.png");
    int guiWidth = 256; //Set the size of the gui width [default is 256x256]
    int guiHeight = 256; //Set the size of the gui width

    int screen = 1; //Variable which stores the screen of the gui which the player is viewing
    GuiButton button1; //Create a new button in the gui - used to close the gui
    GuiButtonTutorial tick; //Create a new button in the gui - used for entering/confirming.
    GuiButtonTutorial buttonSmile; //Create a new Happy emoji button for the 'emotions' prompt.
    GuiTextField textBox; //Create a new text field for typing messages
    GuiChat promptBox; //Create a new chat section - where each prompt will appear
    GuiChat chatBox; //Create another chat section, where the messages the user types will appear
    
    final int BUTTON1 = 0, TICK = 1, BUTTONSMILE = 2; //Assign each button an ID.
    
    /*
     * TODO: Make the following prompts be saved in a config file:
     */
    String p1 = "I'm not sure you noticed but you "; //Prompt 1
    String p2 = "Shoot, how does that make you feel?"; //Prompt 2
    String p3 = " I feel "; //Prompt 3
    String p4 = "I would like you to "; //Prompt 4
    String confirm = "Press confirm to send!";
    
    /*
     * Following variables used later in the gui to show the player their messages
     */
    String title;
    String chat = p1;
    String solution = p4;
    String emotion;
    
    /*
     * Test method: Used to check if the player using the command has already been sent an invitation to problem solve.
     */
    public static void nameCheck(EntityPlayer ep, EntityPlayer sender) {
    	
//		if(getNames().contains(sender.getName())) {
//	    	sender.addChatComponentMessage(new ChatComponentText("Name in list"));
//			Minecraft.getMinecraft().displayGuiScreen(new GuiReceive(ep, sender));
//		}
//		else {
			Minecraft.getMinecraft().displayGuiScreen(new GuiTutorial(ep, sender)); //Basic code to display the gui to the command user.
//		}
    }
    
    /*
     * (non-Javadoc)
     * @see net.minecraft.client.gui.GuiScreen#drawScreen(int, int, float)
     * Draws the screen and all the components in it.
     */
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		title = "Message > " + toPlayer.getName(); //Set the title of the Gui to show who the message is being sent to.
		
        drawDefaultBackground(); //Draw the default background for any minecraft gui
        
        //Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        
        int centerX = (width / 2) - guiWidth / 2; //Create a center X variable - for placing components in the center of the gui
        int centerY = (height / 2) - guiHeight / 2; //Create a center X variable - for placing components in the center of the gui
        
        //drawTexturedModalRect(centerX, centerY, 0, 0, guiWidth, guiHeight);
        //drawString(fontRendererObj, "Tutorial", centerX, centerY, 0x6028ff);
        
        /*
         * GlStateManager >> Uses OpenGL graphics to render the colours etc.. in our gui.
         */
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
        
        /*
         * Draw all the buttons for the gui
         */
        button1.drawButton(mc, mouseX, mouseY);
        tick.drawButton(mc, mouseX, mouseY);
        
        /*
         * Render a book icon into the gui - TODO: Probably remove as it has no actual use.
         */
        ItemStack icon = new ItemStack(Items.book);
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(centerX, centerY, 0);
            GlStateManager.scale(2, 2, 2);
            mc.getRenderItem().renderItemAndEffectIntoGUI(icon, 0, 0);
        }
        GlStateManager.popMatrix();
        
        textBox.drawTextBox(); //Run the method to draw the text box where the players messages will appear.
        chatBox.drawCenteredString(fontRendererObj, chat, (width / 2) - 40, (height / 2) - 70, 11184810);
        if(screen==2)
        	prompt2(mouseX, mouseY);
        else if(screen==3)
        	prompt3();
        else if(screen==4)
        	prompt4();
        else if(screen==5)
        	prompt5();
        else if(screen==6)
        	sendRequest();
        
        List<String> text = new ArrayList<String>();
        text.add(I18n.format("gui.tooltip"));
        text.add(I18n.format("gui.tooltip2", mc.theWorld.provider.getDimensionName()));
        text.add(icon.getDisplayName());
        drawTooltip(text, mouseX, mouseY, centerX, centerY, 16 * 2, 16 * 2);
    }

	private void sendRequest() {
		//TODO CHECK IF PLAYER TO SEND TO IS STILL ONLINE
		
		//if(names.contains(fromPlayer.getName()))
			//return;
		
		//getNames().add(fromPlayer.getName()); //Check if the player who sent it has their name in the list
		
		//Chat message: toPlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "LittleHelper: Hey " + toPlayer.getName() + ", " + fromPlayer.getName() + " wants to problem solve with you.\nType /hey " + mc.thePlayer.getName()));//chat + "\n" + solution));
		mc.thePlayer.closeScreen();
		
		//TODO: Make sure only toPlayer can see this:
		Minecraft.getMinecraft().displayGuiScreen(new GuiReceive(toPlayer, fromPlayer));
		
	}

	private void prompt2(int mouseX, int mouseY) {
    	promptBox.drawCenteredString(fontRendererObj, p2, (width / 2) - 100, (height / 2) - 40, 11184810);
    	buttonSmile.drawButton(mc, mouseX, mouseY);
    	
	}
    
    private void prompt3() {
    	chat += p3 + emotion;
    	screen = 4;
	}
    
    private void prompt4() {
    	promptBox.drawCenteredString(fontRendererObj, "Tell " + toPlayer.getName() + " the action(s) that would resolve this:", (width / 2), (height / 2) - 40, 11184810);
    	promptBox.drawCenteredString(fontRendererObj, solution, (width / 2) - 50, (height / 2) - 30, 11184810);
		
	}
    
    private void prompt5() {
    	promptBox.drawCenteredString(fontRendererObj, solution, (width / 2) - 25, (height / 2) - 56, 11184810);
    	promptBox.drawCenteredString(fontRendererObj, confirm, (width / 2) - 50, (height / 2) - 30, 373765); //373765 green colour
	}

	public void drawTooltip(List<String> lines, int mouseX, int mouseY, int posX, int posY, int width, int height) {
        if (mouseX >= posX && mouseX <= posX + width && mouseY >= posY && mouseY <= posY + height) {
            drawHoveringText(lines, mouseX, mouseY);
        }
    }

    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(button1 = new GuiButton(BUTTON1, (width / 2) - 100 / 2, height - 70, 100, 20, "Close"));
        buttonList.add(tick = new GuiButtonTutorial(TICK, 100, 100));
        buttonList.add(buttonSmile = new GuiButtonTutorial(BUTTONSMILE, (width / 2) - 70, (height / 2) - 30));
        textBox = new GuiTextField(0, fontRendererObj, (width / 2) - 100 / 2, height - 40, 100, 20);
        chatBox = new GuiChat();
        promptBox = new GuiChat();
        //updateButtons();
        super.initGui();
    }

    public void updateButtons() {
    	if(screen!=6) {
    		
	        if (!textBox.getText().isEmpty()) {
	        	tick.enabled = true;
	        } else {
	        	tick.enabled = false;
	        }
    	} tick.enabled = true;
    }

    public void updateTextBoxes() {
        if (!textBox.getText().isEmpty()) {
        	
        	if(screen==1)
        		//SET TEXT FROM BOX AS TITLE
        		chat = p1 + textBox.getText();
        	else if(screen==4)
        		solution = p4 + textBox.getText();
        }
        updateButtons();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case BUTTON1:
            	mc.displayGuiScreen((GuiScreen)null);
                break;
            case TICK: //When player pressing confirm button
            	screen+=1;
            	textBox.setText(""); //Clear the text box once the user presses the confirm button
            	
//            	mc.thePlayer.sendChatMessage(chat);
            	//mc.currentScreen.updateScreen();
                //mc.displayGuiScreen(new GuiInventory(mc.thePlayer));
                break;
            case BUTTONSMILE:
            	emotion = "happy!";
            	screen=3;
            	//mc.currentScreen.updateScreen();
            	break;
                
        }
        updateButtons();
        super.actionPerformed(button);
    }

	@Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        textBox.textboxKeyTyped(typedChar, keyCode);
        updateTextBoxes();
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        textBox.mouseClicked(mouseX, mouseY, mouseButton);
        updateTextBoxes();
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

//	public static List<String> getNames() {
//		return names;
//	}

//	public static void setNames(List<String> names) {
//		GuiTutorial.names = names;
//	}

}
