package com.example.alticci.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(AlticciSequenceController.PATH)
public class AlticciSequenceController {

    /**
     * we could use this simple memoization technique for simplicity here.
     * In case if there will be a more complex requirement (e.g. concurrency)
     * another alternatives should be used (e.g. Guava's Suppliers#memoize)
     */
    private static final Map<Integer, Long> memoization = new HashMap<>();
    static {
        memoization.put(0, 0L);
        memoization.put(1, 1L);
        memoization.put(2, 1L);
    }

    /**
     * Implement a REST service, using a JAVA framework, returning a value from the Alticci sequence.
     * Optionally implement a simple JavaScript2 web GUI to invoke the service.
     * The Alticci – a(n) - sequence is defined as follows:
     * n=0 => a(0) = 0
     * n=1 => a(1) = 1
     * n=2 => a(2) = 1
     * n>2 => a(n) = a(n-3) + a(n-2)
     * Example of the first sequence values:
     * 0
     * 1
     * 1
     * 1
     * 2
     * 2
     * 3
     * 4
     * 5
     * 7
     * 9
     * […]
     * The endpoint created should be in the form <baseurl>/alticci/{n} where {n}
     * represents the index of the sequence’s value to return.
     * The implemented service should use a caching mechanism to take advantage of previous
     * calculations to speed up future calculations.
     */
    public static final String PATH = "/api/";
    public static final String NEGATIVE_NUMBER = "n must be equal or greater than 0, got %d instead";
    public static final String NOT_SUPPORTED_NUMBER = "n must be less than 25,000, got %d instead";

    @Operation(summary = "Returns Alticci sequence for index n", description = "Returns a value from the" +
            " Alticci sequence. Alticci sequence index 'n' must be integer and cannot be less than 0")
    @Parameter(name = "n", description = "Index of the Alticci sequence." +
            " Cannot be less than 0 or more than 25,000")
    @GetMapping("/alticci/{n}")
    public long getAlticciNumber(@PathVariable int n) {
        if (n < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER.formatted(n));
        }
        if (n > 25_000) {
            throw new IllegalArgumentException(NOT_SUPPORTED_NUMBER.formatted(n));
        }

        return alticciNumber(n);
    }

    /**
     * It is not possible to use
     * <pre>{@code memoization.computeIfAbsent(n, (n1) -> alticciNumber(n1 - 3) + alticciNumber(n1 - 2));}</pre>
     * here, because according to the description of the function
     * 'The mapping function should not modify this map during computation.'
     */
    private static long alticciNumber(int n) {
        if (memoization.containsKey(n)) {
            return memoization.get(n);
        }
        long result = alticciNumber(n - 3) + alticciNumber(n - 2);
        memoization.put(n, result);
        return result;
    }
}
