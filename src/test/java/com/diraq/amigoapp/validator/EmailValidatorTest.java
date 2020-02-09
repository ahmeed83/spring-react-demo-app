package com.diraq.amigoapp.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmailValidatorTest {

  private final EmailValidator underTest = new EmailValidator();

  @ParameterizedTest
  @ValueSource(strings = {"hello@gmail.com", "hello@meeee.com"})
  void withValueSourceTrue(String word) {
    assertThat(underTest.test(word)).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {"hello@gmailcom", "hellos.com", "@easew.com", "hdshds@mmm"})
  void withValueSourceFalse(String word) {
    assertThat(underTest.test(word)).isFalse();
  }
}