package com.diraq.amigoapp.validator;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements Predicate {

  public static final Predicate<String> IS_EMAIL_VALID =
      Pattern.compile(
          "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
          Pattern.CASE_INSENSITIVE).asPredicate();

  @Override
  public boolean test(Object email) {
    return IS_EMAIL_VALID.test((String) email);
  }
}
