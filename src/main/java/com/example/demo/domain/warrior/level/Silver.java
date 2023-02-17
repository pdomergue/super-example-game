package com.example.demo.domain.warrior.level;

public class Silver extends Level implements LevelInterface {

    public Silver() {
        super(LevelName.SILVER);
    }

    @Override
    public Level getNextLevel() {
        return new Gold();
    }
}
