package io.septem150.xeric.server.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "player")
@Getter
@Setter
public class Player {
  @Id
  @SequenceGenerator(name = "primary_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
  private Long id;

  @Column(nullable = false, unique = true, length = 12)
  private String username;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountType accountType;

  @Column(nullable = false, columnDefinition = "jsonb")
  @JdbcTypeCode(SqlTypes.JSON)
  private List<AccountException> accountExceptions;

  @OneToMany(mappedBy = "player")
  private List<PlayerTask> taskCompletions;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private OffsetDateTime dateCreated;

  @LastModifiedDate
  @Column(nullable = false)
  private OffsetDateTime lastUpdated;

  public int getPoints() {
    boolean slayer = isSlayerException();
    int points = 0;
    for (PlayerTask taskCompletion : taskCompletions) {
      if (slayer) {
        points += taskCompletion.getTask().getSlayerPoints();
      } else {
        points += taskCompletion.getTask().getTier();
      }
    }
    return points;
  }

  public boolean isSlayerException() {
    return accountExceptions.contains(AccountException.SLAYER);
  }
}
