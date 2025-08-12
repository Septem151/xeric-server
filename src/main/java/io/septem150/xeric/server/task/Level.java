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
