package Soufian.buildffa;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.Config;
import de.theamychan.scoreboard.network.Scoreboard;
import java.io.File;
import java.util.HashMap;
import Soufian.buildffa.listener.BlockBreak;
import Soufian.buildffa.listener.BlockPlace;
import Soufian.buildffa.listener.DataPacketReceive;
import Soufian.buildffa.listener.EntityDamage;
import Soufian.buildffa.listener.EntityDamageByEntity;
import Soufian.buildffa.listener.FoodLevelChange;
import Soufian.buildffa.listener.PlayerDeath;
import Soufian.buildffa.listener.PlayerInteract;
import Soufian.buildffa.listener.PlayerItemDrop;
import Soufian.buildffa.listener.PlayerJoin;
import Soufian.buildffa.listener.PlayerLogin;
import Soufian.buildffa.listener.PlayerQuit;
import Soufian.buildffa.listener.PlayerRespawn;
import Soufian.buildffa.tasks.ScoreboardTask;
import Soufian.buildffa.utils.Utils;

public class BuildFFA
extends PluginBase {
    public static String prefix = "\u00a7e\u00a7l\u00bb \u00a7r\u00a77";
    public static HashMap<Player, Scoreboard> scoreboards = new HashMap();
    private static BuildFFA instance;

    public void onEnable() {
        this.init();
    }

    public static BuildFFA getInstance() {
        return instance;
    }

    public static Config getPConfig(String name) {
        return new Config(BuildFFA.getInstance().getDataFolder() + "/players/" + name.toLowerCase() + ".yml", 2);
    }

    public static void checkAndCreate(String name) {
        if (!new File(BuildFFA.getInstance().getDataFolder() + "/players/" + name.toLowerCase() + ".yml").exists()) {
            Config config = BuildFFA.getPConfig(name);
            config.set("kills", (Object)0);
            config.set("deaths", (Object)0);
            config.save();
        }
    }

    public static int getKills(String name) {
        return BuildFFA.getPConfig(name).getInt("kills");
    }

    public static int getDeaths(String name) {
        return BuildFFA.getPConfig(name).getInt("deaths");
    }

    public static void addKill(String name) {
        Config config = BuildFFA.getPConfig(name);
        config.set("kills", (Object)(BuildFFA.getKills(name) + 1));
        config.save();
    }

    public static void addDeath(String name) {
        Config config = BuildFFA.getPConfig(name);
        config.set("deaths", (Object)(BuildFFA.getDeaths(name) + 1));
        config.save();
    }

    private void init() {
        instance = this;
        new File(this.getDataFolder() + "/players/").mkdirs();
        this.saveResource("config.yml");
        Utils.items = this.getConfig().getStringList("items");
        Utils.ip = this.getConfig().getString("fallback-server");
        Utils.helmet = this.getConfig().getInt("armor.helmet");
        Utils.chestplate = this.getConfig().getInt("armor.chestplate");
        Utils.leggings = this.getConfig().getInt("armor.leggings");
        Utils.boots = this.getConfig().getInt("armor.boots");
        this.getServer().getPluginManager().registerEvents((Listener)new BlockBreak(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new BlockPlace(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerDeath(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerLogin(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new DataPacketReceive(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerInteract(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new EntityDamageByEntity(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerRespawn(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerJoin(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerQuit(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new EntityDamage(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerItemDrop(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new FoodLevelChange(), (Plugin)this);
        this.getServer().getScheduler().scheduleRepeatingTask((Task)new ScoreboardTask(), 20);
    }

    public static void giveItems(Player player) {
        player.getInventory().clearAll();
        for (String item : Utils.items) {
            String[] ex = item.split(":");
            try {
                String[] ex2;
                Item newItem = Item.get((int)Integer.parseInt(ex[1]), (Integer)Integer.parseInt(ex[2]), (int)Integer.parseInt(ex[3]));
                String[] arrstring = ex2 = ex[4].split(",");
                int n = ex2.length;
                for (int i = 0; i < n; ++i) {
                    String s2 = arrstring[i];
                    String[] ex3 = s2.split("#");
                    newItem.addEnchantment(new Enchantment[]{Enchantment.get((int)Integer.parseInt(ex3[0])).setLevel(Integer.parseInt(ex3[1]))});
                }
                player.getInventory().setItem(Integer.parseInt(ex[0]), newItem);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.getInventory().setItem(Integer.parseInt(ex[0]), Item.get((int)Integer.parseInt(ex[1]), (Integer)Integer.parseInt(ex[2]), (int)Integer.parseInt(ex[3])));
            }
        }
        player.getInventory().setHelmet(Item.get((int)Utils.helmet));
        player.getInventory().setChestplate(Item.get((int)Utils.chestplate));
        player.getInventory().setLeggings(Item.get((int)Utils.leggings));
        player.getInventory().setBoots(Item.get((int)Utils.boots));
    }
}

