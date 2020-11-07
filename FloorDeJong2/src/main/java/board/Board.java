package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Board {
    Map<Integer, BoardPosition> positions = new HashMap<>();
    List<String> allowedValues = new ArrayList<>();

    void setAllowedValues();
    boolean isCorrectValue(String value);

    void createPositions();
    void setPositionValue(int positionNr, String value);
    boolean isExistingPosition(int positionNr);

    Map<Integer, BoardPosition> getPositions();
    List<String> getAllowedValues();
}
