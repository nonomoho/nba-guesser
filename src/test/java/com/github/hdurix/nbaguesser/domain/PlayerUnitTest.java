package com.github.hdurix.nbaguesser.domain;

import com.github.hdurix.nbaguesser.error.domain.MissingMandatoryValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.github.hdurix.nbaguesser.domain.PlayerFixture.makePlayer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerUnitTest {
  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "\n"})
  void shouldNotBuildWithoutFirstName(String firstName) {
    assertThatThrownBy(() -> new Player(2, firstName, "Claxton")).isInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("firstName");
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "\n"})
  void shouldNotBuildWithoutLastName(String lastName) {
    assertThatThrownBy(() -> new Player(2, "Nicolas", lastName)).isInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("lastName");
  }

  @Test
  void shouldBuild() {
    Player player = makePlayer();

    assertThat(player.id()).isEqualTo(2);
    assertThat(player.firstName()).isEqualTo("Nicolas");
    assertThat(player.lastName()).isEqualTo("Claxton");
  }
}
