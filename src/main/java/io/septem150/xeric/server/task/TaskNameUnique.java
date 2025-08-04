package io.septem150.xeric.server.task;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import io.septem150.xeric.server.util.NotFoundException;
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
 * Validate that the name value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = TaskNameUnique.TaskNameUniqueValidator.class
)
public @interface TaskNameUnique {

    String message() default "{Exists.task.name}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class TaskNameUniqueValidator implements ConstraintValidator<TaskNameUnique, String> {

        private final TaskService taskService;
        private final HttpServletRequest request;

        public TaskNameUniqueValidator(final TaskService taskService,
                final HttpServletRequest request) {
            this.taskService = taskService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            try {
                @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                        ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
                final String currentId = pathVariables.get("id");
                if (currentId != null && value.equalsIgnoreCase(taskService.get(Long.parseLong(currentId)).getName())) {
                    // value hasn't changed
                    return true;
                }
            } catch (NotFoundException ignored) {
                // ignored
            }
            return !taskService.nameExists(value);
        }

    }

}
