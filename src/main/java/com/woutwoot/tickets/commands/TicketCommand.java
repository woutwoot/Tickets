package com.woutwoot.tickets.commands;

import com.woutwoot.tickets.Main;
import com.woutwoot.tickets.tools.Vars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author woutwoot
 *         Created by on 1/01/2015 - 22:11.
 */
public class TicketCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("ticket")){
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("info")){
                    int id = Integer.parseInt(args[1]);
                    Main.getManager().sendInfo(id, sender);
                }else if(args[0].equalsIgnoreCase("tp") || args[0].equalsIgnoreCase("teleport")){
                    int id = Integer.parseInt(args[1]);
                    if(sender instanceof Player){
                        ((Player) sender).teleport(Main.getManager().getTicket(id).getLocation());
                        sender.sendMessage(Vars.tag + "You've been teleported to the ticket location.");
                    }else{
                        sender.sendMessage(Vars.tag + "You have to be a player to use this command.");
                    }
                }else if(args[0].equalsIgnoreCase("close")){
                    int id = Integer.parseInt(args[1]);
                    Main.getManager().closeTicket(id, sender.getName());
                    sender.sendMessage(Vars.tag + "Ticket #" + id + " has been closed.");
                }else if(args[0].equalsIgnoreCase("delete")){
                    int id = Integer.parseInt(args[1]);
                    Main.getManager().deleteTicket(id);
                    sender.sendMessage(Vars.tag + "Ticket #" + id + " has been deleted.");
                }else if(args[0].equalsIgnoreCase("take")){
                    int id = Integer.parseInt(args[1]);
                    if(sender instanceof Player){
                        Main.getManager().takeTicket(id, (Player) sender);
                        sender.sendMessage(Vars.tag + "You've taken ticket #" + id + ".");
                    }else{
                        sender.sendMessage(Vars.tag + "You have to be a player to use this command.");
                    }
                }
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("list")){
                    Main.getManager().sendFullList(sender);
                }
            }else if(args.length > 2){
                if(args[0].equalsIgnoreCase("comment") || args[0].equalsIgnoreCase("c")) {
                    int id = Integer.parseInt(args[1]);
                    Main.getManager().addComment(id, "TODO"); //TODO: concat args for comment
                }
            }
            return true;
        }
        return false;
    }

}
