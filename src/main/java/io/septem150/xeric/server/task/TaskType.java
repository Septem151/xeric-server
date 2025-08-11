package io.septem150.xeric.server.task;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Schema(enumAsRef = true)
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
