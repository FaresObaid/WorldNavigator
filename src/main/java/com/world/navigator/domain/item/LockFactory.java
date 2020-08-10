package com.world.navigator.domain.item;

import java.util.HashMap;
import java.util.Map;

public class LockFactory {
    private static Map<String, Lock> locks = new HashMap<String, Lock>();;

    public static Lock getLock(String lockName){
        if(locks.containsKey(lockName) == false){
            if(lockName.substring(0, 3).equalsIgnoreCase("Key")){
                locks.put(lockName, new Key(lockName));
            }
            else if(lockName.equalsIgnoreCase("NoKey")){
                locks.put(lockName, NoKey.getInstance());
            }
        }
        return locks.get(lockName);
    }
}
