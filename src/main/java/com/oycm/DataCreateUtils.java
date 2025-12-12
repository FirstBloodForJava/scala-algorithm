package com.oycm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author ouyangcm
 * create 2025/12/10 13:24
 */
public class DataCreateUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * @param str
     * @return 字符串转二维 int 数组
     */
    public static int[][] twoDimensionInts(String str) {
        try {
            return mapper.readValue(str, int[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<List<String>> twoDimensionListStings(String str) {
        try {
            return mapper.readValue(str, new TypeReference<List<List<String>>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
    }
}
