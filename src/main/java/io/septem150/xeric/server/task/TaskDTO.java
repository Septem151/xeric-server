package io.septem150.xeric.server.task;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TaskDTO {

  private Long id;

  @NotNull
  @Size(max = 255)
  private String name;

  @NotNull
  private Integer tier;

  @NotNull
  private TaskType type;

  @Size(max = 255)
  private String icon;

  private Integer slayerPoints;

  private Integer total;

  private List<Integer> itemIds;

  private Integer amount;

  private Diary diary;

  private Boss boss;

  private Level level;

  private Integer goal;

  private Quest quest;
}
