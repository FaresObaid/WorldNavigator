package com.world.navigator.domain.cache;

import com.world.navigator.domain.item.Lock;
import com.world.navigator.domain.maze.Wall;

public abstract class Chest extends Wall{
    private Lock lockType;
    private OpenMethod openMethod;
    private Content contents;
    private boolean open;
    private boolean areContentLooted = false;

    public Chest(Lock lockType, OpenMethod openMethod, Content contents, boolean open) {
        this.lockType = lockType;
        this.openMethod = openMethod;
        this.contents = contents;
        this.open = open;
    }

    public boolean isOpen(){
        return open;
    }

    public Lock getLockType() {
        return lockType;
    }

    public boolean openCashe(Lock lock){
        open = openMethod.isMatch(lock, lockType);
        return open;
    }

    public Content lootContent(){
        if(open && !areContentLooted) {
            areContentLooted = true;
            return contents;
        }
        return null;
    }

    public boolean isContainContent(){
        return !areContentLooted;
    }
}
