/**
BSD 2-Clause License

Copyright (c) 2025, Carly Mullins

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package io.septem150.xeric.server.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Schema(enumAsRef = true)
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
