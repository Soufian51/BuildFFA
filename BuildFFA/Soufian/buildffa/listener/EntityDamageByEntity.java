package Soufian.buildffa.listener;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import Soufian.buildffa.utils.Utils;

public class EntityDamageByEntity
implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            int x = entity.getFloorX();
            int y = entity.getFloorY();
            int z = entity.getFloorZ();
            int sx = entity.getLevel().getSafeSpawn().getFloorX();
            int sy = entity.getLevel().getSafeSpawn().getFloorY();
            int sz = entity.getLevel().getSafeSpawn().getFloorZ();
            int cp = Utils.zone;
            if (Math.abs(sx - x) < cp && Math.abs(sy - y) < cp && Math.abs(sz - z) < cp) {
                event.setCancelled(true);
            }
        }
    }
}

