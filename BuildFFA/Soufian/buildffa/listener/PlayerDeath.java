package Soufian.buildffa.listener;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import Soufian.buildffa.BuildFFA;

public class PlayerDeath
implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            Entity damager = ((EntityDamageByEntityEvent)event.getEntity().getLastDamageCause()).getDamager();
            if (damager instanceof Player) {
                event.setDeathMessage(String.valueOf(BuildFFA.prefix) + "\u00a76" + player.getName() + " \u00a77wurde von \u00a76" + damager.getName() + " \u00a77get\u00f6tet.");
                BuildFFA.addKill(damager.getName());
                BuildFFA.addDeath(player.getName());
            }
        } else {
            event.setDeathMessage(String.valueOf(BuildFFA.prefix) + "\u00a76" + player.getName() + " \u00a77ist gestorben.");
            BuildFFA.addDeath(player.getName());
        }
    }
}

