package ru.pda.dnddungeonmasterkit.util;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class FileManager {
    public static final HashMap<String, ImageIcon> backgrounds = new HashMap<>();
    public static List<File> readedFiles = new ArrayList<>();

    public static void getImages(String dir) {
        List<File> files = getFiles(dir);
        readedFiles.addAll(files);
        for (File f : files) {
            backgrounds.put(f.getName().substring(0, f.getName().length()-4), new ImageIcon(f.getAbsolutePath()));
        }
    }

    private static List<File> getFiles(String dir) {
        List<File> fileList = new ArrayList<>();
        try (Stream<Path> files = Files.list(Paths.get(dir))) {
            fileList = files
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .toList();
            fileList.forEach(System.out::println);
        } catch (IOException e) {
            log.info("Error occured due to file reading. " + e.getMessage());
        }
        return fileList;
    }

    public static List<String> getNames(String dir) {
        List<String> names = new ArrayList<>();
        for (File f : readedFiles) {
            names.add(f.getName().substring(0, f.getName().length()-4));
        }
        return names;
    }
}
