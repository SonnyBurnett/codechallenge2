package directions;

import java.util.HashMap;
import java.util.Map;

public class Directions2D implements Directions {

    private final int dimensions = 2;

    private final Map<Character, Character> directions;

    public Directions2D() {
        this.directions = new HashMap<>();
        this.directions.put('L', 'R');
        this.directions.put('R', 'L');
        this.directions.put('D', 'U');
        this.directions.put('U', 'D');
    }

    public String getOppositeDirection(String direction) {
        StringBuilder oppositeDir = new StringBuilder();

        if (direction.length() <= dimensions) {
            for (int i = 0; i < direction.length(); i++) {
                Character character = direction.charAt(i);
                if (directions.containsKey(character)) {
                    oppositeDir.append(directions.get(direction.charAt(i)));
                } else {
                    throw new IllegalArgumentException("Incorrect direction: " + character + ". Needs to be one of " + directions.keySet());
                }
            }
        } else {
            throw new IllegalArgumentException("Direction string longer then 2: " + direction);
        }

        return oppositeDir.toString();
    }

    public Map<Character, Character> getDirections(){
        return this.directions;
    }
}
