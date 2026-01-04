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
            return mapper.readValue(str, new TypeReference<List<List<String>>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ListNode buildListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        // 哨兵节点
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        for (int val : arr) {
            cur.next = new ListNode(val);
            cur = cur.next;
        }

        return dummy.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return null;
        }
        StringBuilder ans = new StringBuilder("[");
        while (node != null) {
            ans.append(node.val).append(", ");
            node = node.next;
        }
        ans.setLength(ans.length() - 2);
        ans.append("]");
        return ans.toString();
    }

    public static void main(String[] args) {
    }
}
