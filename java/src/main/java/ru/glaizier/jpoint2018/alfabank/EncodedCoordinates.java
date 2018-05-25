package ru.glaizier.jpoint2018.alfabank;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author GlaIZier
 */
// U need to find coordinates by messages and transform()
public class EncodedCoordinates {

    // At first, we realize that these messages are encrypted with Cesar coding
    private static String s1 = "Ave, ётхе. Пвёжаую, фэ ёруфвфрщпр хозп, щфргэ пвлфк мрртёкпвфэ.";
    private static String s2 = "Дрф фдрк дчрёпэж ёвппж, фрнюмр срожпбл кч ожуфвок:";
    private static String s3 = "18,72,16,54,27,23 4,5,3,1,2,0";

    public static void transform(String[] args) {
        int[] arr1 = Arrays.stream(args[0].split(","))
            .mapToInt(Integer::parseInt).toArray(); // 18,72,16,54,27,23 parsed
        int[] arr2 = Arrays.stream(args[1].split(","))
            .mapToInt(Integer::parseInt).toArray(); // 4, 5, 3, 1, 2, 0 parsed

        Optional<String> coordinates = Stream.iterate(0, i -> arr2[i]) // 0, 4, 2, 3, 1, 5, | limit length(6)  0, 4...
            .map(res -> arr1[res]) // 18, 27, 16, 54, 72, 23 | limit length (6) 18, 27
            .skip(1) // 27, 16, 54, 72, 23, 18
            .limit(arr2.length) // 27, 16, 54, 72, 23, 18
            .map(Object::toString)
            .reduce((a, b) -> a + ", " + b); // 27, 16, 54, 72, 23, 18

        if (coordinates.isPresent()) {
            coordinates.map(s -> new StringBuilder(s).reverse().toString()); // doesn't change anything because result is not assigned
        }

        System.out.println(String.format("%so %s' %s''" + System.getProperty("line.separator") + "%so %s' %s''",
            coordinates.get().split(", ")));
    }

    public static String decodeCesarEncoding(String s, int shift) {
        return s.chars()
            .map(c -> c + shift)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }

    public static void main(String[] args) {
        Stream.of(s1, s2)
            .map(s -> decodeCesarEncoding(s, -2))
            .forEach(System.out::println);

        transform(s3.split(" "));

//        Integer[] arr2 = {54, 33, 27};
//        Stream.iterate(0, i -> arr2[i])
//            .limit(arr2.length)
//            .forEach(System.out::println);
    }

}
