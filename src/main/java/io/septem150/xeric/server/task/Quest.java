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
public enum Quest {
  DRUIDIC_RITUAL("Druidic Ritual"),
  EAGLES_PEAK("Eagles' Peak"),
  RUNE_MYSTERIES("Rune Mysteries"),
  A_KINGDOM_DIVIDED("A Kingdom Divided"),
  GETTING_AHEAD("Getting Ahead"),
  THE_GARDEN_OF_DEATH("The Garden of Death"),
  CHILDREN_OF_THE_SUN("Children of the Sun"),
  TWILIGHTS_PROMISE("Twilight's Promise"),
  THE_HEART_OF_DARKNESS("The Heart of Darkness"),
  X_MARKS_THE_SPOT("X Marks the Spot"),
  CLIENT_OF_KOUREND("Client of Kourend"),
  THE_QUEEN_OF_THIEVES("The Queen of Thieves"),
  THE_DEPTHS_OF_DESPAIR("The Depths of Despair"),
  THE_ASCENT_OF_ARCEUUS("The Ascent of Arceuus"),
  THE_FORSAKEN_TOWER("The Forsaken Tower"),
  TALE_OF_THE_RIGHTEOUS("Tale of the Righteous"),
  PERILOUS_MOONS("Perilous Moons"),
  THE_RIBBITING_TALE_OF_A_LILY_PAD_LABOUR_DISPUTE(
      "The Ribbiting Tale of a Lily Pad Labour Dispute"),
  AT_FIRST_LIGHT("At First Light"),
  DEATH_ON_THE_ISLE("Death on the Isle"),
  MEAT_AND_GREET("Meat and Greet"),
  ETHICALLY_ACQUIRED_ANTIQUITIES("Ethically Acquired Antiquities"),
  THE_FINAL_DAWN("The Final Dawn"),
  SHADOWS_OF_CUSTODIA("Shadows of Custodia"),
  SCRAMBLED("Scrambled!");

  private final String name;

  @Override
  public String toString() {
    return name;
  }
}
