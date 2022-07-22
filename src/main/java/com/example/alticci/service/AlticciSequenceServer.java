package com.example.alticci.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlticciSequenceServer {

    public static final String NEGATIVE_NUMBER = "n must be equal or greater than 0, got %d instead";
    public static final String NOT_SUPPORTED_NUMBER = "n must be less than 25,000, got %d instead";

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

    public long getAlticciSequence(@PathVariable int n) {
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
