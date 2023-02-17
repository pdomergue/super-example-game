package com.example.demo.domain.warrior.level;

public class Titanium extends Level implements LevelInterface {

    public Titanium() {
        super(LevelName.TITANIUM);
    }

    @Override
    public Level getNextLevel() {
        return null;
    }
}
