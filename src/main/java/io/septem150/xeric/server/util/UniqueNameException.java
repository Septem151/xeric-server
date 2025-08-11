package io.septem150.xeric.server.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UniqueNameException extends RuntimeException {
  @Getter
  private final Long id;

  public UniqueNameException(final Long id) {
    this("name must be unique", id);
  }

  public UniqueNameException(final String message, final Long id) {
    super(message);
    this.id = id;
  }
}
