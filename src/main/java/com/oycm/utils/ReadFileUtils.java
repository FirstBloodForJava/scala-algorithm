package com.oycm.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileUtils {

    public static String readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }

        // 读取文件内容

        try {
            return Files.readString(Paths.get(filePath)).trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
