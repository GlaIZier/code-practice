package ru.glaizier.jpoint2018.alfabank;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author GlaIZier
 */
public class EncodedCoordinates {

    String s1 = "Ave, ётхе. Пвёжаую, фэ ёруфвфрщпр хозп, щфргэ пвлфк мрртёкпвфэ.";
    String s2 = "Дрф фдрк дчрёпэж ёвппж, фрнюмр срожпбл кч ожуфвок:";
    String s3 = "4,5,3,1,2,0 18,72,16,54,27,23";

    public static void decodeAndPrintCoordinates(String[] args) {
        int[] arr1 = Arrays.stream(args[0].split(","))
            .mapToInt(Integer::parseInt).toArray();
        int[] arr2 = Arrays.stream(args[0].split(","))
            .mapToInt(Integer::parseInt).toArray();

        Optional<String> coordinates = Stream.iterate(0, i -> arr2[i])
            .map(res -> arr1[res])
            .skip(1)
            .limit(arr2.length)
            .map(Object::toString)
            .reduce((a, b) -> a + ", " + b);

        if (coordinates.isPresent()) {
            coordinates.map(s -> new StringBuilder(s).reverse().toString());
        }

        System.out.println(String.format("%s %s' %s" + System.getProperty("line.separator") + "%s %s' %s",
            coordinates.get().split(", ")));
    }



}
