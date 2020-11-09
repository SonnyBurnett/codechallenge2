package nl.codechallenge;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
@EqualsAndHashCode
public class Product {

    private final double price;
    private final @NonNull String productId;
    private final @NonNull String name;
    private final @NonNull String description;
    private final @NonNull String category;

    public boolean isTenPlusDollars() {
        return price >= 10;
    }

    public double priceInEuro() {
        return price * 0.85;
    }

}
