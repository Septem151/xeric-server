package io.septem150.xeric.server.task;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum DiaryTier {

    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    ELITE("Elite");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
