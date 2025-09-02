package pl.coderslab.dish.enums;

import lombok.Getter;

@Getter
public enum Spiciness {
    MILD(1),
    MEDIUM(2),
    SPICY(3),
    HOT(4);

    private final int level;

    Spiciness(int level) {
        this.level = level;
    }

}
