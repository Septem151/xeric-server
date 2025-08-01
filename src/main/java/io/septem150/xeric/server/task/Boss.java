package io.septem150.xeric.server.task;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Boss {

    CHAMBERS_OF_XERIC_CHALLENGE_MODE("Chambers of Xeric: Challenge Mode"),
    CHAMBERS_OF_XERIC("Chambers of Xeric"),
    ALCHEMICAL_HYDRA("Alchemical Hydra"),
    AMOXLIATL("Amoxliatl"),
    THE_HUEYCOATL("Hueycoatl"),
    SARACHNIS("Sarachnis"),
    HESPORI("Hespori"),
    SKOTIZO("Skotizo"),
    LUNAR_CHESTS("Lunar Chests"),
    SOL_HEREDIT("Sol Heredit"),
    COLOSSEUM_GLORY("Colosseum Glory"),
    WINTERTODT("Wintertodt"),
    MIMIC("Mimic"),
    YAMA("Yama"),
    DOOM_OF_MOKHAIOTL("Doom of Mokhaiotl"),
    CLUES_EASY("Clue Scrolls (easy)"),
    CLUES_MEDIUM("Clue Scrolls (medium)"),
    CLUES_HARD("Clue Scrolls (hard)"),
    CLUES_ELITE("Clue Scrolls (elite)"),
    CLUES_MASTER("Clue Scrolls (master)");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
