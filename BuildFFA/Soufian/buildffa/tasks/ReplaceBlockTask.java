package Soufian.buildffa.tasks;

import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.level.Position;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.Task;

public class ReplaceBlockTask
extends Task {
    private Position position;
    private int seconds;

    public ReplaceBlockTask(Position position, int seconds) {
        this.position = position;
        this.seconds = seconds;
    }

    public void onRun(int i) {
        if (this.seconds == 2) {
            Server.getInstance().getLevelByName(this.position.getLevel().getName()).setBlock((Vector3)this.position, Block.get((int)179));
        }
        if (this.seconds == 0) {
            Server.getInstance().getLevelByName(this.position.getLevel().getName()).setBlock((Vector3)this.position, Block.get((int)0));
            this.getHandler().cancel();
        }
        --this.seconds;
    }
}

