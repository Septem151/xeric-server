package io.septem150.xeric.server.player;

import io.septem150.xeric.server.task.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "player_task")
@IdClass(PlayerTaskKey.class)
@Getter
@Setter
public class PlayerTask {
  @Id
  private Long playerId;

  @Id
  private Long taskId;

  @ManyToOne
  @MapsId("playerId")
  @JoinColumn(name = "player_id")
  private Player player;

  @ManyToOne
  @MapsId("taskId")
  @JoinColumn(name = "task_id")
  private Task task;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private OffsetDateTime dateCompleted;
}
