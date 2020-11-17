package com.ing.challenge.model.player;

import java.util.Arrays;
import java.util.List;

public record TTTPlayer(String name, TTTPlayerType type, String... attributes) {
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Name: " +
                name +
                ", " +
                "type: " +
                type +
                ", " +
                "attributes: " +
                Arrays.toString(attributes);
    }

    public Iterable<String> toCsv(String msg) {
        return List.of(msg, name, type.name(), Arrays.toString(attributes));
    }
}
