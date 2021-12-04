package Soufian.buildffa.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLoginEvent;
import Soufian.buildffa.BuildFFA;

public class PlayerLogin
implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        BuildFFA.checkAndCreate(player.getName());
    }
}

