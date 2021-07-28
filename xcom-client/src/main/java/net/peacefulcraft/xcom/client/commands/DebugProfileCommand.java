package net.peacefulcraft.xcom.client.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.peacefulcraft.xcom.client.XCOMClient;
import net.peacefulcraft.xcom.client.profile.UserProfile;

public class DebugProfileCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!label.equalsIgnoreCase("xcm-profile")) { return false; }

		if (!sender.hasPermission("xcom.*")) { return false; }

		Player p;
		if (args.length < 1) {
			if (sender instanceof Player) {
				p = (Player) sender;
			} else {
				sender.sendMessage(XCOMClient.messagingPrefix + "Expected the name of an online user.");
				return true;
			}
		} else {
			p = XCOMClient._this().getServer().getPlayer(args[0]);
			if (p == null) {
				sender.sendMessage(XCOMClient.messagingPrefix + "Unable to find user " + args[0] + ". Are they online?");
				return true;
			}
		}

		UserProfile up = (UserProfile) XCOMClient._this().getUserProfileService().getProfileByMojangID(p.getUniqueId());

		sender.sendMessage("---------------------------------");
		sender.sendMessage("ID: " + up.getPeacefulCraftID());
		sender.sendMessage("Discord Links:");
		up.getDiscordIDs().forEach((snowflake) -> {
			sender.sendMessage("  - " + snowflake);
		});

		sender.sendMessage("Flarum Links:");
		up.getFlarumIDs().forEach((snowflake) -> {
			sender.sendMessage("  - " + snowflake);
		});

		sender.sendMessage("Mojang Links");
		up.getMojangIDs().forEach((snowflake) -> {
			sender.sendMessage("  - " + snowflake);
		});

		return true;
	}
	
}
