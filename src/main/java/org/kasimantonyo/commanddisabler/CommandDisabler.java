package org.kasimantonyo.commanddisabler;

import jdk.tools.jlink.plugin.PluginException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandDisabler extends JavaPlugin implements Listener {

    PluginDescriptionFile pdffile = getDescription();
    public String version = pdffile.getVersion();
    ConsoleCommandSender c = getServer().getConsoleSender();


    public void onEnable() {
        c.sendMessage("");
        c.sendMessage(CC.translate("&cCommandDisabler has been enabled!"));
        c.sendMessage(CC.translate("&fYou are using version: &e" + version));
        c.sendMessage("");
        registrarConfig(); // Metodo registrarConfig
        Bukkit.getPluginManager().registerEvents(this, (Plugin)this); // Registrar eventos

    }
    public void onDisable() {
        c.sendMessage("");
        c.sendMessage(CC.translate("&cCommandDisabler has been disabled!"));
        c.sendMessage(CC.translate("&fYou are using version: &e" + version));
        c.sendMessage("");

    }
    public void registrarConfig() { // registrar config
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Player jugador = e.getPlayer();

        String[] arrayos;
        int j = (arrayos = e.getMessage().split(" ")).length;
        for (int i = 0; i < j; i++) {
            String cmdsBloqueado = arrayos[i];
            if (getConfig().getStringList("Commands").contains(cmdsBloqueado.toLowerCase())) {
                e.setCancelled(true);
                // futuro jugador.sendMessage(CC.translate(Config.Message))) future
                jugador.sendMessage(getConfig().getString("Message").replace("&", "§"));
            }
        }

    }

}
