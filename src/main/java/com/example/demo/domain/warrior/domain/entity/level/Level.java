package com.example.demo.domain.warrior.domain.entity.level;

public enum Level {
    BRONZE, SILVER, GOLD, PLATINUM, TITANIUM;

    private static final Level[] vals = values();

    public Boolean hasNext(){
        return this.ordinal() != TITANIUM.ordinal();
    }

    public Level next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }
}
