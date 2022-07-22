package com.example.alticci.controller;

import com.example.alticci.service.AlticciSequenceServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AlticciSequenceController.PATH)
public class AlticciSequenceController {

    @Autowired
    private AlticciSequenceServer alticciSequenceServer;

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

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }

    @Operation(summary = "Returns Alticci sequence for index n", description = "Returns a value from the" +
            " Alticci sequence. Alticci sequence index 'n' must be integer and cannot be less than 0")
    @Parameter(name = "n", description = "Index of the Alticci sequence." +
            " Cannot be less than 0 or more than 25,000")
    @ApiResponse()
    @GetMapping("/alticci/{n}")
    public long getAlticciSequenceForIndex(@PathVariable int n) {
        return alticciSequenceServer.getAlticciSequence(n);
    }

}
