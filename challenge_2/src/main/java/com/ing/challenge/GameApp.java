package com.ing.challenge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameApp {
    private static final Logger logger = LogManager.getLogger(GameApp.class);

    public static void main(String[] args) {
        logger.info(() -> "Let the games begin!");
    }
}
