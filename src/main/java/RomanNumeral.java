package main.java;

/**
 * Created by gaukharmassabayeva on 10/11/15.
 */

public enum RomanNumeral {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);
    private Integer value;

    RomanNumeral(Integer value) { this.value = value; }

    public Integer getValue() {
        return value;
    }

}
