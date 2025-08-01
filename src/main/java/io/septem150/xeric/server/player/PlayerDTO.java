package io.septem150.xeric.server.player;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PlayerDTO {

    private Long id;

    @NotNull
    @Size(max = 12)
    @PlayerUsernameUnique
    private String username;

    @NotNull
    private AccountType accountType;

    @NotNull
    private List<AccountException> accountExceptions;

    private List<Long> tasks;

}
