package com.oycm.algorithm.d.binary_search.advance;

import java.util.*;

public class Solution_11 {

    public static void main(String[] args) {
        Router router = new Router(4);
        System.out.println(router.addPacket(4, 5, 1));
        System.out.println(router.getCount(5, 1, 1));
    }
}

/**
 * 3508. <a href="https://leetcode.cn/problems/implement-router/description/">设计路由器</a> 1851
 */
class Router {

    class Packet {
        int source;
        int destination;
        int timestamp;

        public Packet(int source, int destination, int timestamp) {
            this.source = source;
            this.destination = destination;
            this.timestamp = timestamp;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Packet packet = (Packet) o;
            return source == packet.source && destination == packet.destination && timestamp == packet.timestamp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination, timestamp);
        }
    }

    class Pair {
        int head;
        List<Integer> timestamps = new ArrayList<>();
    }

    private int size;
    // 数据包队列
    private Queue<Packet> queue = new ArrayDeque<>();
    // 判断数据包是否存在
    private Set<Packet> set = new HashSet<>();
    // 相同地址的 时间戳集合
    private Map<Integer, Pair> pairs = new HashMap<>();

    /**
     * 初始化路由器并指定内存限制
     * <p>
     * 表示任意时间点可以存储的最大的数据包 数量
     * <p>
     * 如果添加一个数据包会超过这个限制，则必须移除最旧的数据包以腾出空间
     *
     * @param memoryLimit 路由器内存限制
     */
    public Router(int memoryLimit) {
        /*
         怎么知道哪个要先删除；快速查找数据包是否相等；快速查找指定地址的数据包；
         队列记录 存放的元素， set 用来判断元素是否相等
         map key 为目的地，value 为集合 + 集合的头，用来标记是否删除头元素
         */
        size = memoryLimit;
    }

    /**
     * 添加数据包，数据包包含的属性
     * <p>
     * 如果路由器中已经存在具有相同 source、destination、timestamp，则视为重复数据
     * 如果数据包不是重复数据，则添加成功，返回 true，否则返回 false
     *
     * @param source      生成该数据包的机器的唯一标识符
     * @param destination 目标机器的唯一标识符
     * @param timestamp   该数据包到达路由器的时间戳，会按非递减顺序添加
     * @return
     */
    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        if (!set.add(packet)) {
            // 存在元素
            return false;
        }
        if (size == queue.size()) {
            // 删除数据包
            forwardPacket();
        }
        queue.add(packet);
        // 键值对添加
        pairs.computeIfAbsent(destination, k -> new Pair()).timestamps.add(timestamp);
        return true;
    }

    /**
     * 按 FIFO（先进先出）转发也给数据包，如果是空的，则返回空数组
     *
     * @return 按 [source, destination, timestamp] 返回
     */
    public int[] forwardPacket() {
        if (queue.isEmpty()) {
            return new int[]{};
        }
        Packet poll = queue.poll();
        set.remove(poll);
        Pair pair = pairs.get(poll.destination);
        pair.head = pair.head + 1;

        return new int[]{poll.source, poll.destination, poll.timestamp};
    }

    /**
     * 返回路由器中的数据包，且地址为指定 destination，时间戳在 [startTime, endTime] 范围内
     *
     * @param destination 目标地址
     * @param startTime   开始时间戳
     * @param endTime     结束时间戳
     * @return
     */
    public int getCount(int destination, int startTime, int endTime) {
        Pair pair = pairs.get(destination);
        if (pair == null) {
            return 0;
        }
        // >= endTime + 1 的 index -1
        // >= startTime 的 index
        return lowerBound(pair.timestamps, endTime + 1, pair.head - 1) - lowerBound(pair.timestamps, startTime, pair.head - 1);
    }

    private int lowerBound(List<Integer> list, int x, int l) {
        int r = list.size();
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (list.get(mid) >= x) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }
}
