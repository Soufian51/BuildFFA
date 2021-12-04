package Soufian.buildffa.listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.SetLocalPlayerAsInitializedPacket;
import Soufian.buildffa.BuildFFA;

public class DataPacketReceive
implements Listener {
    @EventHandler
    public void onReceive(DataPacketReceiveEvent event) {
        if (event.getPacket() instanceof SetLocalPlayerAsInitializedPacket) {
            Player player = event.getPlayer();
            Server.getInstance().getScheduler().scheduleDelayedTask(() -> player.teleport(Server.getInstance().getDefaultLevel().getSafeSpawn()), 1);
            BuildFFA.giveItems(player);
        }
    }
}

