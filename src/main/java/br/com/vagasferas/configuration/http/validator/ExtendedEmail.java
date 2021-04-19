package br.com.vagasferas.configuration.http.validator;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Email(regexp = ".+@.+\\..+|")
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ExtendedEmail {

    @OverridesAttribute(constraint = Email.class, name = "message")
    String message() default "Formato de email inválido";

    @OverridesAttribute(constraint = Email.class, name = "groups")
    Class<?>[] groups() default {};

    @OverridesAttribute(constraint = Email.class, name = "payload")
    Class<? extends Payload>[] payload() default {};
}