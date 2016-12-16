package ru.glaizier.util;

@SuppressWarnings("WeakerAccess")
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Pair) {
            Pair thatPair = (Pair) that;
            boolean firstEquals = (thatPair.getX() == null) ? this.getX() == null : thatPair.getX().equals(this.getX());
            boolean secondEquals = (thatPair.getY() == null) ? this.getY() == null : thatPair.getY().equals(this.getY());
            return firstEquals && secondEquals;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
