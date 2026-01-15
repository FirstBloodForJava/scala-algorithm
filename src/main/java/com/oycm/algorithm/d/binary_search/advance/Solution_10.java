package com.oycm.algorithm.d.binary_search.advance;

import java.util.*;

public class Solution_10 {

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();

        timeMap.set("foo", "bar", 1);

        System.out.println(timeMap.get("foo", 1));

    }

}

/**
 * 981. 基于时间的键值存储 1575
 * https://leetcode.cn/problems/time-based-key-value-store/description/
 * <p>
 * 设计结构可以在 不同时间戳 存储对应同一个键 的多个值
 */
class TimeMap {

    Map<String, List<Map.Entry<Integer, String>>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    /**
     * 存储给定时间戳 timestamp 时的键 key 和值 value
     *
     * @param key
     * @param value
     * @param timestamp 调用 set 的时间戳都是严格递增的
     */
    public void set(String key, String value, int timestamp) {
        List<Map.Entry<Integer, String>> list = map.get(key);
        if (list == null) {
            list = new ArrayList<>();
            map.put(key, list);
        }
        list.add(new AbstractMap.SimpleEntry<>(timestamp, value));
    }

    /**
     * 返回 <= timestamp 最大时间戳对应的 value, 没有则返回 ""
     *
     * @param key
     * @param timestamp
     * @return
     */
    public String get(String key, int timestamp) {
        List<Map.Entry<Integer, String>> list = map.get(key);
        if (list == null) {
            return "";
        } else if (timestamp < list.get(0).getKey()) {
            return "";
        }
        int l = -1;
        int r = list.size();
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid).getKey() >= timestamp + 1) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return list.get(r - 1).getValue();
    }
}