package nl.codechallenge;

import java.util.List;

public interface ArgumentValidator {
    void validate(List<String> args) throws IllegalArgumentException;
}
