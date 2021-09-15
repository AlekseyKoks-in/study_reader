package com.alex.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;

public class MyReader {

    public void reader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String pathFile = null;
        try {
            pathFile = bufferedReader.readLine();
            List<String> list = conversionFileToList(pathFile);
            System.out.println(list);
            System.out.println();
            Map<String, Integer> map = wordsStatistics(list);
            for (Map.Entry<String, Integer> pair : map.entrySet()) {
                System.out.println(pair.getKey() + " - "  + pair.getValue());
            }
            System.out.println();
            searchMaxValue(map);
        } catch (Exception e) {
            System.out.println("Something exception");
        }
    }

    public List<String> conversionFileToList(String pathFile) throws IOException {
        String string = String.valueOf(Files.readAllLines(Path.of(pathFile)));
        String[] words = string.toLowerCase(Locale.ROOT).split("\\W+");
        Arrays.sort(words);
        List<String> listWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                listWords.add(words[i]);
            }
        }
        return listWords;
    }

    public Map<String, Integer> wordsStatistics(List<String> listWords) {
        Map<String, Integer> map = new TreeMap<>();
        int count = 1;
        for (String s : listWords) {
            if (map.containsKey(s)) {
                count = count + 1;
            } else {
                count = 1;
            }
            map.put(s, count);
        }
        return map;
    }

    public void searchMaxValue(Map<String, Integer> map) {
        int maxValue = 0;
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            if (maxValue < pair.getValue()) {
                maxValue = pair.getValue();
            }
        }
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            if (pair.getValue().equals(maxValue)) {
                System.out.println(pair.getKey() + " - " + pair.getValue());
            }
        }
    }
}