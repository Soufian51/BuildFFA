package Soufian.buildffa.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFoodLevelChangeEvent;

public class FoodLevelChange
implements Listener {
    @EventHandler
    public void onFood(PlayerFoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}

