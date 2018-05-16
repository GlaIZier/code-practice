package ru.glaizier.jpoint2018.epam.normal;

/**
 * @author GlaIZier
 */
public class FirstGcCandidate {

    private Object o;
    void set(Object s) {
        o = s;
    }

    // Which is the earliest line after which garbage collection of obj created in the first line is possible,
    // assuming no compiler optimizations are done
    public static void main(String[] args) {
        Object obj = new Object();
        FirstGcCandidate firstGcCandidate = new FirstGcCandidate();
        firstGcCandidate.set(obj);
        obj = new Object();
        obj = null;
        firstGcCandidate.set(obj);
    }

}
