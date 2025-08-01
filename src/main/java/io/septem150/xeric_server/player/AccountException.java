package io.septem150.xeric_server.player;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountException {

    SLAYER("Slayer"),
    HERBLORE("Herblore"),
    BOXTRAPS("Boxtraps"),
    OTHER("Other");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
