package nl.codechallenge.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArgumentValidatorImpl implements ArgumentValidator {
    @Override
    public void validate(List<String> args) throws IllegalArgumentException {
        // expecting a single argument, but assuming it will be a proper file path
        if (args == null || args.size() != 1) {
            throw new IllegalArgumentException("Please provide a single argument, being the input file path");
        }
    }
}
