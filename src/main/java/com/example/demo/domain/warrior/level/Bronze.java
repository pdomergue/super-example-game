package com.example.demo.domain.warrior.level;

public class Bronze extends Level implements LevelInterface {

    public Bronze() {
        super(LevelName.BRONZE);
    }

    @Override
    public Level getNextLevel() {
        return new Silver();
    }
}
