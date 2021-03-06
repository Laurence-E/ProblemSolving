package com.laurence.chatmod.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public abstract class CommandPermission extends CommandBase {
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return this.getRequiredPermissionLevel() == 0 || sender.canCommandSenderUseCommand(this.getRequiredPermissionLevel(), this.getCommandName());
    }
	
}
