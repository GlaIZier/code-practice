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

    private int seed;

    private int x;

    public LinearCongruentRandom(int seed) {
        this.seed = seed;
        this.x = seed;
    }

    @Override
    public int getNext() {
        // TODO  move to global random vars
        x = (7 * x + 7) % 10;
        return x;
    }

    @Override
    public void reset() {
        x = seed;
    }
}
