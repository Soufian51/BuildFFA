package Soufian.buildffa.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerRespawnEvent;
import Soufian.buildffa.BuildFFA;

public class PlayerRespawn
implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        BuildFFA.giveItems(player);
    }
}

