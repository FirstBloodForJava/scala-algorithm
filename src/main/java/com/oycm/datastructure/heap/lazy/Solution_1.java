package com.oycm.datastructure.heap.lazy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution_1 {


}

/**
 * 2349. <a href="https://leetcode.cn/problems/design-a-number-container-system/description/">设计数字容器系统</a> 1540
 */
class NumberContainers {

    /*
    定义两个 map
    indexMap 记录 当前 下标存放的 number
    numberMap 记录 相同值 下标的堆(懒删除堆)
     */
    Map<Integer,Integer> indexMap = new HashMap<>();
    Map<Integer,LazyHeap> numberMap = new HashMap<>();
    public NumberContainers() {

    }

    /**
     * 指定位置插入或替换一个数组
     *
     * @param index  位置
     * @param number 插入/替换 值
     */
    public void change(int index, int number) {
        if (indexMap.containsKey(index)) {
            // 删除旧元素
            Integer remove = indexMap.put(index, number);
            numberMap.get(remove).remove(index);
        } else {
            // 新增元素
            indexMap.put(index, number);
        }
        // 记录相同元素下标
        if (!numberMap.containsKey(number)) {
            LazyHeap lazyHeap = new LazyHeap(Integer::compare);
            numberMap.put(number, lazyHeap);
            lazyHeap.push(index);
        } else {
            numberMap.get(number).push(index);
        }
    }

    /**
     * @param number 目标值
     * @return 系统中最小下标, 没有则返回 -1
     */
    public int find(int number) {
        if (numberMap.containsKey(number) && numberMap.get(number).size > 0) {
            return numberMap.get(number).top();
        }
        return -1;
    }

    static class LazyHeap extends PriorityQueue<Integer> {
        // 记录堆中元素需要删除的次数
        private final Map<Integer, Integer> removeCnt = new HashMap<>();
        // 堆的实际大小
        private int size = 0;

        public LazyHeap(Comparator<Integer> comparator) {
            super(comparator);
        }

        // 删除堆中所有应该删除的元素后，堆的实际大小
        public int size() {
            return size;
        }

        // 删除
        public void remove(int x) {
            // 标记堆中元素需要删除
            removeCnt.merge(x, 1, Integer::sum);
            size--;
        }

        // 正式执行删除操作, 查看或弹出时执行
        private void applyRemove() {
            while (removeCnt.getOrDefault(peek(), 0) > 0) {
                removeCnt.merge(poll(), -1, Integer::sum);
            }
        }

        // 查看堆顶
        public int top() {
            applyRemove();
            return peek(); // 真正的堆顶
        }

        // 出堆
        public int pop() {
            applyRemove();
            size--;
            return poll();
        }

        // 入堆
        public void push(int x) {
            int c = removeCnt.getOrDefault(x, 0);
            if (c > 0) {
                // 抵消之前的删除
                removeCnt.put(x, c - 1);
            } else {
                offer(x);
            }
            size++;
        }

        public void add(int x) {
            int c = removeCnt.getOrDefault(x, 0);
            if (c > 0) {
                // 抵消之前的删除
                removeCnt.put(x, c - 1);
            } else {
                offer(x);
            }
            size++;
        }
    }

    public static void main(String[] args) {
        NumberContainers nc = new NumberContainers();
        nc.find(10); // -1 。
        nc.change(2, 10); // 容器中下标为 2 处填入数字 10 。
        nc.change(1, 10); // 容器中下标为 1 处填入数字 10 。
        nc.change(3, 10); // 容器中下标为 3 处填入数字 10 。
        nc.change(5, 10); // 容器中下标为 5 处填入数字 10 。
        nc.find(10); // 数字 10 所在的下标为 1 ，2 ，3 和 5 。因为最小下标为 1 ，所以返回 1 。
        nc.change(1, 20); // 容器中下标为 1 处填入数字 20 。注意，下标 1 处之前为 10 ，现在被替换为 20 。
        nc.find(10); // 数字 10 所在下标为 2 ，3 和 5 。最小下标为 2 ，所以返回 2 。
    }

    /*
    题解优化
     */
    static class Answer {

        // 记录当前下标元素
        Map<Integer,Integer> indexMap = new HashMap<>();
        // 记录相同元素下标
        Map<Integer,PriorityQueue<Integer>> numberMap = new HashMap<>();

        public Answer() {

        }

        public void change(int index, int number) {
            indexMap.put(index, number);
            numberMap.computeIfAbsent(number, o -> new PriorityQueue<>()).add(index);
        }

        public int find(int number) {
            PriorityQueue<Integer> min = numberMap.get(number);
            if (min == null) {
                return -1;
            }
            // 符合要求的下标值 value 肯定 == number, 否则就是之前要删除的数据
            while (!min.isEmpty() && indexMap.get(min.peek()) != number) {
                min.poll();
            }
            return min.isEmpty() ? -1 : min.peek();
        }
    }
}

