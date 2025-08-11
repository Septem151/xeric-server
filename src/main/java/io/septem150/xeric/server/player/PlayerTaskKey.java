package io.septem150.xeric.server.player;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class PlayerTaskKey implements Serializable {
  private Long playerId;
  private Long taskId;
}
