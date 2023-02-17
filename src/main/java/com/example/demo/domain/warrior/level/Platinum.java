package com.example.demo.domain.warrior.level;

public class Platinum extends Level implements LevelInterface {

    public Platinum() {
        super(LevelName.PLATINUM);
    }

    @Override
    public Level getNextLevel() {
        return new Titanium();
    }
}
