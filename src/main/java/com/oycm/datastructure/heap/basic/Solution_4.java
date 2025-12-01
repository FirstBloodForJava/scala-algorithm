package com.oycm.datastructure.heap.basic;

import java.util.HashSet;

public class Solution_4 {

    public static void main(String[] args) {
        SmallestInfiniteSet set = new SmallestInfiniteSet();
        System.out.println(set.popSmallest());
        System.out.println(set.popSmallest());
        set.addBack(3);

        System.out.println(set.popSmallest());
        set.addBack(2);

        System.out.println(set.popSmallest());
        System.out.println(set.popSmallest());
    }
}

/**
 * 2336. 无限集中的最小数字 1375
 * https://leetcode.cn/problems/smallest-number-in-infinite-set/description/
 * <p>
 * 一个包含所有正整数的集合
 * <p>
 * 初始化 SmallestInfiniteSet 类包含所有正整数的无限集合
 * <p>
 * popSmallest() 移除并返回无限集合中的最小元素
 * <p>
 * addBack(int num) 如果 num 正整数不在 无线集合中，将 num 添加到
 * <p>
 * 移除 和 添加 方法最多共调用 1000 次
 */
class SmallestInfiniteSet {

    int[] heap = new int[1000];
    int size = heap.length;

    // 用 set 记录可添加的元素。是否可优化？
    Set<Integer> set = new HashSet<>();

    public SmallestInfiniteSet() {
        // 可初始化一个 [1,1000] 的小顶堆数组
        for (int i = 0; i < heap.length; i++) {
            heap[i] = i + 1;
        }
        heapify();
    }

    public int popSmallest() {
        int ans = heap[0];
        heap[0] = heap[--size];
        heap[size] = ans;
        sink(0);
        set.add(ans);
        return ans;
    }

    public void addBack(int num) {
        // 如果 nums >= 无限集合中的最小值，则不用添加进集合
        // 如果 弹出了 2, 3，先添加 2，再添加 3，则 3 需要添加进去确没有进去
        // size < heap.length && num <= heap[size-1] 弹出的小元素放在后面
        if (set.contains(num)) {
            // 堆添加元素
            heap[size] = num;
            siftUp(size);
            size++;
            set.remove(num);
        }
    }

    private void heapify() {
        // 最后一个非叶子节点开始堆化
        for (int i = size / 2 - 1; i >= 0; i--) {
            sink(i);
        }
    }

    // 小顶堆
    private void sink(int i) {
        int n = size;
        // 存在叶子节点, i 是非叶子节点(父节点)
        while (2 * i + 1 < n) {
            // 假设 左子节点比右子节点小
            int l = 2 * i + 1;
            if (l + 1 < n && heap[l + 1] < heap[l]) {
                l++;
            }
            // 父节点大于等于 最大的子节点
            if (heap[i] <= heap[l]) {
                break;
            }
            // 交换
            swap(heap, i, l);
            // 下沉比较
            i = l;
        }
    }

    private void swap(int[] heap, int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 添加元素 上移
    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) >> 1;
            // 加入元素 大于等于 父节点
            if (heap[i] >= heap[parent]) {
                break;
            }
            swap(heap, i, parent);
            i = parent;
        }
    }
}
