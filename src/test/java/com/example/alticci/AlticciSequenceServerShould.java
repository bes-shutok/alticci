package com.example.alticci;

import com.example.alticci.service.AlticciSequenceServer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.alticci.service.AlticciSequenceServer.NEGATIVE_NUMBER;
import static com.example.alticci.service.AlticciSequenceServer.NOT_SUPPORTED_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("NewClassNamingConvention")
@SpringBootTest
class AlticciSequenceServerShould {

	@Autowired
	private AlticciSequenceServer alticciSequenceServer;

	@ParameterizedTest
	@ValueSource(ints = {-1, -100, Integer.MIN_VALUE })
	void failForNegative(int n) {
		assertThrows(
				IllegalArgumentException.class,
				() -> alticciSequenceServer.getAlticciSequence(n),
				NEGATIVE_NUMBER.formatted(n)
		);
	}

	@ParameterizedTest
	@ValueSource(ints = {26_000, Integer.MAX_VALUE })
	void failForNotSupported(int n) {
		assertThrows(
				IllegalArgumentException.class,
				() -> alticciSequenceServer.getAlticciSequence(n),
				NOT_SUPPORTED_NUMBER.formatted(n)
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
			100, 888855064897
			""")
	void returnCorrectValue(int n, long alticciNumber) {
		assertEquals(alticciNumber, alticciSequenceServer.getAlticciSequence(n));
	}

}
