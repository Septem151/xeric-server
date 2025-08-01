package io.septem150.xeric.server.task;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TaskType {

    CA("ca"),
    COLLECT("collect"),
    DIARY("diary"),
    KC("kc"),
    LEVEL("level"),
    QUEST("quest");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
