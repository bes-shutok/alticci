package com.example.alticci;

import com.example.alticci.controller.AlticciSequenceController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.alticci.controller.AlticciSequenceController.ILLIGAL_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AlticciApplicationTests {

	@Autowired
	private AlticciSequenceController alticciSequenceController;

	@ParameterizedTest
	@ValueSource(ints = {-1, -100, Integer.MIN_VALUE })
	void shouldFailForNegative(int n) {
		assertThrows(
				IllegalArgumentException.class,
				() -> alticciSequenceController.getAlticciNumber(n),
				ILLIGAL_NUMBER.formatted(n)
		);
	}

	@ParameterizedTest
	@CsvSource(textBlock = """
			0, 0
			1, 1
			2, 1
			3, 1
			4, 2
			5, 2
			6, 3
			7, 4
			8, 5
			9, 7
			10, 9
			""")
	void shouldReturnCorrectValue(int n, long alticciNumber) {
		assertEquals(alticciNumber, alticciSequenceController.getAlticciNumber(n));
	}

}
