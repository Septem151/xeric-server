package io.septem150.xeric.server.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PlayerDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Size(max = 12)
    @PlayerUsernameUnique
    private String username;

    @NotNull
    private AccountType accountType;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<AccountException> accountExceptions;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> tasks;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int points;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean slayerException;

}
