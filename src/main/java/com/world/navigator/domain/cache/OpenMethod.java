package com.world.navigator.domain.cache;

import com.world.navigator.domain.item.Lock;

public interface OpenMethod {
    public boolean isMatch(Lock lock1, Lock lock2);
}
