package io.septem150.xeric.server.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Schema(enumAsRef = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Level {

    ATTACK("attack"),
    STRENGTH("strength"),
    DEFENCE("defence"),
    RANGED("ranged"),
    PRAYER("prayer"),
    MAGIC("magic"),
    RUNECRAFT("runecraft"),
    CONSTRUCTION("construction"),
    HITPOINTS("hitpoints"),
    AGILITY("agility"),
    HERBLORE("herblore"),
    THIEVING("thieving"),
    CRAFTING("crafting"),
    FLETCHING("fletching"),
    SLAYER("slayer"),
    HUNTER("hunter"),
    MINING("mining"),
    SMITHING("smithing"),
    FISHING("fishing"),
    COOKING("cooking"),
    FIREMAKING("firemaking"),
    WOODCUTTING("woodcutting"),
    FARMING("farming"),
    TOTAL("total"),
    ANY("any"),
    MAXED("maxed");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
