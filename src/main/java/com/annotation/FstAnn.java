package com.annotation;

import com.annotation.valiadtors.FstAnnConstValid;
import org.springframework.context.annotation.PropertySource;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by bohdan on 01.11.16.
 */
@Documented
@Constraint(validatedBy = FstAnnConstValid.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@PropertySource("classpath:massage.properties")
public @interface FstAnn {
    String message() default "FstAnn";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
