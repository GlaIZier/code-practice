package ru.glaizier.jpoint2018.epam;

public class JustLooping {
    private int j;

    public void getJ() {
        while (j < 5) {
            for (int j = 1; j <=5;) {
                System.out.println(j + " ");
                j++;
            }
            j++;
        }
    }

    public static void main(String[] args) {
        new JustLooping().getJ();
    }
}
