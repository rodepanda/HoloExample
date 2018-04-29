package net.rodepanda.holoexample;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        getCommand("holoexample").setExecutor(new HoloCommand());
    }

}
