package ru.glaizier.jpoint2018.ok;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryCleanUp {

    // Code to clean up a directory
    // what's wrong with this code?
    void cleanup(Path dir) throws IOException {
        // didn't check that dir is a directory
        // Didn't use try-with-resources
        for (Path file: Files.newDirectoryStream(dir)) {
            // endsWith will return false for "/home/ggg.tmp" as it checks the string from the last file separator to
            // the provided string
            if (file.endsWith(".tmp")) {
                // didn't check that this is a file and not a directory
                Files.delete(file);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Paths.get("/home/glaizier.tmp").endsWith("glaizier.tmp/"));
    }

}
