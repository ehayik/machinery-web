package com.github.eljaiek.machinery.web.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.collections.impl.factory.primitive.IntLists.immutable;

import com.carrotsearch.hppc.IntArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.val;
import org.joda.money.Money;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class JacksonAddonsConfigurationTests {

    @Autowired ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void jsonOrgModuleShouldBeEnabled() {
        // When
        val jsonObject =
                objectMapper.readValue(
                        "{\"a\":{\"b\":3}, \"c\":[9, -4], \"d\":null, \"e\":true}",
                        JSONObject.class);

        // Then
        assertThat(jsonObject.getJSONObject("a").getInt("b")).isEqualTo(3);
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("java:S5845")
    void hppcModuleShouldBeRegistered() {
        // Given
        val numbers = IntArrayList.from(1, 2, 3, 4, 5);

        // When
        val jsonString = objectMapper.writeValueAsString(numbers);

        // Then
        assertThat(jsonString).isEqualTo("[1,2,3,4,5]");
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("java:S5845")
    void eclipseCollectionsModuleShouldBeRegistered() {
        // Given
        val numbers = immutable.of(1, 2, 3, 4, 5);

        // When
        val jsonString = objectMapper.writeValueAsString(numbers);

        // Then
        assertThat(jsonString).isEqualTo("[1,2,3,4,5]");
    }

    @Test
    @SneakyThrows
    @SuppressWarnings("java:S5845")
    void jodaMoneyModuleShouldBeRegistered() {
        // Given
        val money = Money.parse("USD 23.87");

        // When
        val jsonString = objectMapper.writeValueAsString(money);

        // Then
        assertThat(jsonString).isEqualTo("{\"amount\":23.87,\"currency\":\"USD\"}");
    }
}
