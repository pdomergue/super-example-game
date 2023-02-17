package com.example.demo.domain.warrior.level;

public class Gold extends Level implements LevelInterface {

    public Gold() {
        super(LevelName.GOLD);
    }

    @Override
    public Level getNextLevel() {
        return new Platinum();
    }
}
