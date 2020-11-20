package nl.codechallenge.service;

import java.util.List;

public interface ArgumentValidator {
    void validate(List<String> args) throws IllegalArgumentException;
}
