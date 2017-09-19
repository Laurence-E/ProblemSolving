package com.laurence.chatmod.commands;

import java.util.List;

import com.laurence.chatmod.ChatMod;
import com.laurence.chatmod.gui.ChatModGuiHandler;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class HeyCommand extends CommandPermission {

	/*private ChatMod instance;
	
	public HeyCommand(ChatMod instance) {
		this.instance = instance;
	}*/
	
	@Override
	public String getCommandName() {
		
		return "hey";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		
		return "/hey [player]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		
		if(!(sender instanceof EntityPlayer))
			return;
		
		if (args.length < 1)
        {
            throw new WrongUsageException(getCommandUsage(sender), new Object[0]);
        }
        else
        {
            EntityPlayer toPlayer = getPlayer(sender, args[0]);
            EntityPlayer fromPlayer = (EntityPlayer) sender;
            
            new ChatModGuiHandler().setToPlayer(toPlayer);
            
            fromPlayer.openGui(ChatMod.instance, ChatMod.PromptGuiID, fromPlayer.worldObj, fromPlayer.serverPosX, fromPlayer.serverPosY, fromPlayer.serverPosZ);
            
            //Minecraft.getMinecraft().displayGuiScreen(new GuiTutorial(entityplayer, (EntityPlayer) sender));
            
            //GuiTutorial.nameCheck(entityplayer, (EntityPlayer) sender);
            //Minecraft.getMinecraft().displayGuiScreen(new GuiTutorial(entityplayer));
        }
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		
		return args.length == 1 ? CommandBase.getListOfStringsMatchingLastWord(args, getListOfPlayerUsernames()): null;
	}
	
	protected String[] getListOfPlayerUsernames()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }
	
	public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 1;
    }
	
}
