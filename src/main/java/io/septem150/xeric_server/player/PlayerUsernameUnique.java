package io.septem150.xeric_server.player;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;


/**
 * Validate that the username value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = PlayerUsernameUnique.PlayerUsernameUniqueValidator.class
)
public @interface PlayerUsernameUnique {

    String message() default "{Exists.player.username}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class PlayerUsernameUniqueValidator implements ConstraintValidator<PlayerUsernameUnique, String> {

        private final PlayerService playerService;
        private final HttpServletRequest request;

        public PlayerUsernameUniqueValidator(final PlayerService playerService,
                final HttpServletRequest request) {
            this.playerService = playerService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(playerService.get(Long.parseLong(currentId)).getUsername())) {
                // value hasn't changed
                return true;
            }
            return !playerService.usernameExists(value);
        }

    }

}
