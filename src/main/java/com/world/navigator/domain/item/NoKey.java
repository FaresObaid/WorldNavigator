package com.world.navigator.domain.item;

public class NoKey extends Lock {
    private static NoKey singeltonNoKey= new NoKey();

    private NoKey(){

    }

    public static NoKey getInstance(){
        return singeltonNoKey;
    }

    @Override
    public String toString() {
        return "NoKey";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof NoKey)) {return false;}

        return true;
    }
}
