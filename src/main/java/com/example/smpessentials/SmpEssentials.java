package com.example.smpessentials;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class SmpEssentials extends JavaPlugin implements Listener {
    private static final String GLYPH = "\\uE001";
    private final Set<UUID> hiddenPlayers = new HashSet<>();
    private BossBar discordBar;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        createBar();
        Bukkit.getPluginManager().registerEvents(this, this);
        for (Player player : Bukkit.getOnlinePlayers()) showTo(player);
        getLogger().info("SMP Essentials enabled.");
    }

    @Override
    public void onDisable() {
        if (discordBar != null) discordBar.removeAll();
    }

    private void createBar() {
        Component title = Component.text(GLYPH).font(Key.key("smp", "default"));
        discordBar = Bukkit.createBossBar(title, BarColor.WHITE, BarStyle.SOLID);
        // An empty bar keeps the custom banner prominent; vanilla still draws its thin bossbar track.
        discordBar.setProgress(0.0);
        discordBar.setVisible(getConfig().getBoolean("bossbar.enabled", true));
    }

    private void showTo(Player player) {
        if (getConfig().getBoolean("resource-pack.auto-send", false)) {
            String url = getConfig().getString("resource-pack.url", "").trim();
            if (!url.isEmpty()) {
                String sha1 = getConfig().getString("resource-pack.sha1", "").trim();
                if (sha1.isEmpty()) player.setResourcePack(url);
                else player.setResourcePack(url, sha1);
            }
        }
        if (!hiddenPlayers.contains(player.getUniqueId()) && getConfig().getBoolean("bossbar.enabled", true)) {
            discordBar.addPlayer(player);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        showTo(event.getPlayer());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("smpe")) return false;
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("§bSMP Essentials §7- /smpe toggle | /smpe reload");
            return true;
        }
        if (args[0].equalsIgnoreCase("toggle")) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage("Only players can toggle their own banner.");
                return true;
            }
            UUID id = player.getUniqueId();
            if (hiddenPlayers.remove(id)) {
                if (getConfig().getBoolean("bossbar.enabled", true)) discordBar.addPlayer(player);
                player.sendMessage("§aDiscord banner enabled.");
            } else {
                hiddenPlayers.add(id);
                discordBar.removePlayer(player);
                player.sendMessage("§eDiscord banner hidden.");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("smpe.admin")) {
                sender.sendMessage("§cYou don't have permission.");
                return true;
            }
            reloadConfig();
            discordBar.removeAll();
            createBar();
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!hiddenPlayers.contains(player.getUniqueId())) discordBar.addPlayer(player);
            }
            sender.sendMessage("§aSMP Essentials reloaded.");
            return true;
        }
        sender.sendMessage("§cUsage: /smpe [toggle|reload|help]");
        return true;
    }
}
