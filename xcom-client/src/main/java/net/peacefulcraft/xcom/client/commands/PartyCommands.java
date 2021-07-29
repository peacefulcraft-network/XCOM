package net.peacefulcraft.xcom.client.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.peacefulcraft.xcom.client.XCOMClient;

public class PartyCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("xcom.party")) {
			sender.sendMessage(XCOMClient.messagingPrefix + "Sorry, you do not have access to the party system. Contact a staff member if you believe this to be an error.");
			return true;
		}

		if (args.length < 1) {
			sender.sendMessage(XCOMClient.messagingPrefix + "Sorry, I am not sure what you want me to do. Try one of [ accept, create, disban, invite, leave, or update ]");
			return true;
		}

		switch(args[0].toLowerCase()) {
			case "accept":

			break; case "create":

			break; case "disban":

			break; case "invite":

			break; case "leave":

			break; case "update":
			
			break; default:
				sender.sendMessage(XCOMClient.messagingPrefix + "Sorry, I am not sure what you want me to do. Try one of [ accept, create, disban, invite, leave, or update ]");
				return true;
		}
		return false;
	}
}
