package net.rodepanda.holoexample;

import net.rodepanda.holograms.Components.GuiComponents.GuiButtonItemComponent;
import net.rodepanda.holograms.Components.GuiComponents.GuiTextComponent;
import net.rodepanda.holograms.Holo;
import net.rodepanda.holograms.Projector.Page;
import net.rodepanda.holograms.Projector.Screen;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class HoloCommand implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))return true;
        Player p = (Player)commandSender;
        Vector direction = p.getLocation().getDirection().normalize();
        Vector right =  new Vector(-direction.getZ(), 0.0, direction.getX()).normalize().multiply(3).add(p.getLocation().toVector()).add(p.getLocation().getDirection().normalize().multiply(4));
        Vector left =  new Vector(direction.getZ(), 0.0, -direction.getX()).normalize().multiply(3).add(p.getLocation().toVector()).add(p.getLocation().getDirection().normalize().multiply(4));
        left.setY(p.getLocation().getY());
        right.setY(p.getLocation().getY() + 3);
        Screen screen = Holo.getPlayerScreen(p);
        if(screen != null)
            Holo.unRegisterScreen(p);
        new Screen(p, getPage(), left, right, 2);
        return true;
    }

    private Page getPage(){
        Page p = new Page();
        p.addComponent(new GuiTextComponent(0.5,1 , ChatColor.GOLD + "Welcome to my server!", 0));
        p.addComponent(new GuiButtonItemComponent(0.2, 0.55, true, "Play sound", 0.5, Material.NOTE_BLOCK, pl -> pl.playSound(pl.getLocation(), Sound.UI_BUTTON_CLICK, 0.5f, 1f), 0, true));
        p.addComponent(new GuiButtonItemComponent(0.5, 0.55, true, "Server Rules", 0.5, Material.BOOK, pl -> pl.sendMessage("Example rules"), 0, true));
        p.addComponent(new GuiButtonItemComponent(0.8, 0.55, true, ChatColor.RED + "Close menu", 0.5, Material.TNT,pl ->Holo.unRegisterScreen(pl), 0, true));
        return p;
    }

}
