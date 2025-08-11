package io.septem150.xeric.server.task;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Schema(enumAsRef = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Diary {

    KOUREND_EASY("Easy"),
    KOUREND_MEDIUM("Medium"),
    KOUREND_HARD("Hard"),
    KOUREND_ELITE("Elite");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
