package Soufian.buildffa.listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;

public class PlayerInteract
implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getItem().getCustomName().equals("\u00a7cSpiel verlassen")) {
            Server.getInstance().dispatchCommand((CommandSender)player, "hub");
        }
    }
}

