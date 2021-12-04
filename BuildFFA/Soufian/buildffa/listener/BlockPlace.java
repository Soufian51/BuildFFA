package Soufian.buildffa.listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.level.Position;
import cn.nukkit.scheduler.Task;
import Soufian.buildffa.tasks.ReplaceBlockTask;
import Soufian.buildffa.utils.Utils;

public class BlockPlace
implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        int x = player.getFloorX();
        int y = player.getFloorY();
        int z = player.getFloorZ();
        int sx = player.getLevel().getSafeSpawn().getFloorX();
        int sy = player.getLevel().getSafeSpawn().getFloorY();
        int sz = player.getLevel().getSafeSpawn().getFloorZ();
        int cp = Utils.zone;
        if (Math.abs(sx - x) < cp && Math.abs(sy - y) < cp && Math.abs(sz - z) < cp) {
            event.setCancelled(true);
        } else {
            Server.getInstance().getScheduler().scheduleRepeatingTask((Task)new ReplaceBlockTask((Position)event.getBlock().getLocation(), 5), 20);
        }
    }
}

