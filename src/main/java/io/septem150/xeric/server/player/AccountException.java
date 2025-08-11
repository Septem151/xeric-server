package io.septem150.xeric.server.player;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Schema(enumAsRef = true)
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
