package ru.glaizier.util;

public class Tuple<X, Y> {

    private final X x;
    private final Y y;

    public Tuple(X x, Y y) {
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
        if (that instanceof Tuple) {
            Tuple thatTuple = (Tuple) that;
            boolean firstEquals = (thatTuple.getX() == null) ? this.getX() == null : thatTuple.getX().equals(this.getX());
            boolean secondEquals = (thatTuple.getY() == null) ? this.getY() == null : thatTuple.getY().equals(this.getY());
            return firstEquals && secondEquals;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
