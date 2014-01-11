package net.naturalmc.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Main
        extends JavaPlugin
        implements Listener
{
    //Messages on plugin's enable
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("Enabling NSGUI.");
        getLogger().info(ChatColor.RED + "Connecting to http://naturalmc.net");
        getLogger().info(ChatColor.RED + "Found 5 Api servers, Pinging.");
        getLogger().info(ChatColor.BLUE + "Connected to ns1.api.natural.survival.network.chestercraft.com");
        getLogger().info(ChatColor.BLUE + "Logging in as Natural Minecraft Survival Server 01");
        getLogger().severe("Connected to Auth Server!");
        getLogger().info(ChatColor.GREEN + "Authenticated as Natural Minecraft Survival Server 01, on ns1.api.natural.survival.network.chestercraft.com");
    }

    //Messages on plugin's disable
    public void onDisable()
    {
        getLogger().info("Sending Disconnect request to http://naturalmc.net ");
        getLogger().severe("NSGUI now shutting down.");



    }


    String displayname = ChatColor.DARK_GRAY + "Natural" + ChatColor.DARK_AQUA + "MC";

    @EventHandler
    public void onPJE(PlayerJoinEvent event)
    {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        board.registerNewObjective("showhealth", "health");
        board.registerNewObjective("info", "dummy");
        board.registerNewObjective("test", "totalKillCount");
        Objective objective = board.getObjective("showhealth");
        Objective objective2 = board.getObjective("info");
        Objective objective3 = board.getObjective("test");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplayName(ChatColor.RED + "/ 20");
        objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective2.setDisplayName(this.displayname);
        objective3.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        Score score4 = objective2.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_AQUA + "Server I.D. NS"));
        score4.setScore(Integer.parseInt(Bukkit.getServerName()));
        Score staff = objective2.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Staff"));
        int onlineops = 0;
        try
        {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("nmc.staff")) {
                    onlineops++;
                }
            }
        }
        finally
        {
            staff.setScore(onlineops);
        }
        Score players = objective2.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_AQUA + "Players"));
        int onlineplayers = 0;
        try
        {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("nmc.player")) {
                    onlineplayers++;
                }
            }
        }
        finally
        {
            players.setScore(onlineplayers);
        }
        Score score5 = objective2.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_AQUA + "NMC$"));
        score5.setScore(0);

        //Teams

        Team team = board.registerNewTeam("Team");






        team.setPrefix(ChatColor.DARK_GRAY + "[N" + ChatColor.DARK_AQUA + "S1" + ChatColor.DARK_GRAY + "]");


        team.setSuffix("");

        team.setDisplayName("display name");


        team.setCanSeeFriendlyInvisibles(true);


        team.setAllowFriendlyFire(true);
        team.addPlayer(event.getPlayer());
        for (Player online : Bukkit.getOnlinePlayers())
        {
            online.setScoreboard(board);

            team.addPlayer(online);
        }
    }

    @EventHandler
    public void onPQE(PlayerQuitEvent event) //When a player leaves the server, this handler will update it. Make sure to have the exact same thing as above
            //or the scoreboard will not be the same as wanted.
    {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        board.registerNewObjective("showhealth", "health");
        board.registerNewObjective("info", "dummy");
        board.registerNewObjective("test", "totalKillCount");
        Objective objective = board.getObjective("showhealth");
        Objective objective2 = board.getObjective("info");
        Objective objective3 = board.getObjective("test");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective2.setDisplayName(this.displayname);
        objective3.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        //Score score4 = objective2.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_AQUA + "Server I.D. NS"));
        //score4.setScore(0);
        Score staff = objective2.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Staff"));
        int onlineops = 0;
        try
        {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("nmc.staff")) {
                    onlineops++;
                }
            }
        }
        finally
        {
            staff.setScore(onlineops);
        }
        Score players = objective2.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_AQUA + "Players"));
        int onlineplayers = 0;
        try
        {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("nmc.player")) {
                    onlineplayers++;
                }
            }
        }
        finally
        {
            players.setScore(onlineplayers);
        }
        Score score5 = objective2.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "NMC$"));
        score5.setScore(0);


        Team team = board.registerNewTeam("Team");





        team.setPrefix(ChatColor.DARK_GRAY + "[N" + ChatColor.DARK_AQUA + "S1" + ChatColor.DARK_GRAY + "]");


        team.setSuffix("");

        team.setDisplayName("display name");


        team.setCanSeeFriendlyInvisibles(true);


        team.setAllowFriendlyFire(true);
        team.addPlayer(event.getPlayer());
        for (Player online : Bukkit.getOnlinePlayers())
        {
            online.setScoreboard(board);

            team.addPlayer(online);
        }
    }
}
