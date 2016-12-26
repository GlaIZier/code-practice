package ru.glaizier.random;

/**
 * Реализуйте генератор рандомных чисел представленный классом Random. Класс должен удовлетворять интерфейсу RandomInterface.
 * <p>
 * Пример использования:
 * <p>
 * $seq = new Random(100);
 * $result1 = $seq->getNext();
 * $result2 = $seq->getNext();
 * <p>
 * $this->assertNotEquals($result1, $result2);
 * <p>
 * $seq->reset();
 * $result21 = $seq->getNext();
 * $result22 = $seq->getNext();
 * $this->assertEquals($result1, $result21);
 * $this->assertEquals($result2, $result22);
 * <p>
 * Простейший способ реализовать случайные числа это линейный конгруэнтный метод.
 * <p>
 * xn+1 = (axn + c) mod m
 */
public class LinearCongruentRandom implements RandomInterface {

    // 0 <= seed < m
    private final int seed;

    // 0 <= a < m
    private final int a;

    // 0 <= c < m
    private final int c;

    // m >= 2
    private final int m;

    private int x;

    public LinearCongruentRandom(int seed) {
//        1 <= seed <= Integer.MAX_VALUE - 1
        if (seed < 0)
            seed = Math.abs(seed);
        if (seed == Integer.MIN_VALUE || seed == 0 || seed == Integer.MAX_VALUE)
            seed = 31;
        this.seed = seed;
        this.x = seed;
        this.m = seed + 1;
        this.a = x;
        this.c = x;
    }

    @Override
    public int getNext() {
        x = (a * x + c) % m;
        return x;
    }

    @Override
    public void reset() {
        x = seed;
    }

    public int getSeed() {
        return seed;
    }
}
