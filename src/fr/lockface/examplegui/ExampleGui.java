package fr.lockface.examplegui;

import fr.lockface.impostorcraft.gui.sabotagegui.SabotageGuisManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleGui extends JavaPlugin {

    @Override
    public void onEnable() {
        // register our gui to the SabotageGuisManager
        SabotageGuisManager.registerGui("button", ButtonGui.class);
    }

    @Override
    public void onDisable() {

    }
}
