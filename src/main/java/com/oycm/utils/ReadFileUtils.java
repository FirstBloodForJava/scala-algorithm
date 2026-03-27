package com.oycm.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFileUtils {

    /**
     * [2025/12/03 11:11:49.938] [XNIO-2 task-42] ERROR  - Filter threw Exception 字符串分割成 三块
     * 2025/12/03 11:11:49.938
     * XNIO-2 task-42
     * ERROR  - Filter threw Exception
     */
    public static final Pattern PATTERN_1 = Pattern.compile("^\\[([\\d/]+\\s+[\\d:.]+)\\]\\s+" +
            "\\[(XNIO-2\\s+task-\\d+)\\]\\s+" +
            "(.*)$");


    public static final Pattern PATTERN_2 = Pattern.compile("^([\\d:.]+)\\s+" +
            "\\[(.+)\\]\\s+" +
            "\\[(\\S+)\\]\\s+" +
            "(.*)$");

    public static final Pattern START_PATTERN_1 = Pattern.compile("^\\d.+$");

    public static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }

        // 读取文件内容

        return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8).trim();
    }


    /**
     *
     * @param filePath
     * @param errorContain 包含该字符才读取
     * @return
     * @throws IOException
     */
    public static List<String> readLogLine(String filePath, String errorContain) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (errorContain.isEmpty()) {
                    if (START_PATTERN_1.matcher(line).matches()) {
                        list.add(line);
                    }
                } else if (line.contains(errorContain)) {
                    list.add(line);
                }
            }
        }
        return list;
    }

    public static void readErrorThreadInfo(String infoPath, String errorPath, String errorContain, Pattern pattern, int threadGroup, String outPath, int errorBeforeSize) throws IOException {
        List<String> errorList = readLogLine(errorPath, errorContain);
        // 错误线程
        Set<String> errorThreads = new HashSet<>();
        // info 线程数
        Set<String> infoThreads = new TreeSet<>();
        for (String s : errorList) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                errorThreads.add(matcher.group(threadGroup));
            }
        }
        int errorIdx = 0;
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(outPath));

        Map<String, Deque<String>> map = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(infoPath))) {
            String line;
            while ((line = reader.readLine()) != null && errorIdx < errorList.size()) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {

                    if (errorThreads.contains(matcher.group(threadGroup))) {
                        map.computeIfAbsent(matcher.group(threadGroup), o -> new ArrayDeque<>()).push(line);
                    }

                    infoThreads.add(matcher.group(threadGroup));

                    if (errorList.get(errorIdx).equals(line)) {
                        Deque<String> stack = map.get(matcher.group(threadGroup));
                        int size = errorBeforeSize;
                        List<String> tempList = new ArrayList<>();
                        while (!stack.isEmpty() && --size > 0) {
                            tempList.add(stack.pop());

                        }
                        errorIdx++;
                        for (int i = tempList.size() - 1; i >= 0; i--) {
                            bufferedWriter.write(tempList.get(i));
                            bufferedWriter.write("\n");
                        }
                    }

                }
            }
        }
        for (String s : infoThreads) {
            bufferedWriter.write(s);
            bufferedWriter.write("\n");
        }

        bufferedWriter.write("error Thread \n");

        errorThreads = new TreeSet<>(errorThreads);
        for (String s : errorThreads) {
            bufferedWriter.write(s);
            bufferedWriter.write("\n");
        }

        bufferedWriter.flush();

    }

    public static void main(String[] args) throws IOException {
//        readErrorThreadInfo("data/36zuul.log.2025-12-03", "data/36error.log.2025-12-03", "[", PATTERN_1, 2,"data/36zuul-251203.log", 20);

        readErrorThreadInfo("data/eg-info.2025-12-03.4.log", "data/eg-error.2025-12-03.0.log", "", PATTERN_2, 3,"data/eg-33.4-251203.log", 20);


    }

}
