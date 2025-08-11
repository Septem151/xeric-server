package io.septem150.xeric.server.player;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Schema(enumAsRef = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountType {

  DEFAULT("Default"),
  IRONMAN("Ironman"),
  ULTIMATE("Ultimate Ironman"),
  HARDCORE("Hardcore Ironman"),
  RANKED_GIM("Ranked Group Ironman"),
  HARDCORE_GIM("Hardcore Group Ironman"),
  UNRANKED_GIM("Unranked Group Ironman");

  private final String name;


  @Override
  public String toString() {
    return name;
  }
}
