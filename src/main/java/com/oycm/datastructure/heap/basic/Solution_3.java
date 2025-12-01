package com.oycm.datastructure.heap.basic;

/**
 * @author ouyangcm
 * create 2025/12/1
 */
public class Solution_3 {

    /**
     * 2558. <a href="https://leetcode.cn/problems/take-gifts-from-the-richest-pile/description/">从数量最多的堆取走礼物</a> 1277
     * <p>
     * 正整数数组 gifts 表示各堆礼物的数量，每一秒，需要执行一下操作：
     * - 选择礼物数量最多的那一堆
     * - 如果不止一堆都符合礼物数量最多，从中选择任一堆即可。
     * - 将堆中的礼物数量减少到堆中原来礼物数量的平方根，向下取整。
     * <p>
     * 求 k 秒后剩下的礼物数量
     *
     * @param gifts 正整数数组
     * @param k
     * @return
     */
    public static long pickGifts(int[] gifts, int k) {
        /*
        大顶堆，不需要考虑 index，弹出再入即可，最后求和

        时间复杂度 O(n + k log n)
        空间复杂度 O(1)
         */
        heapify(gifts);
        while (k > 0 && gifts[0] > 1) {
            gifts[0] = (int) Math.sqrt(gifts[0]);
            // 其它位置都是符合堆的要求, 只有堆顶元素被修改了
            sink(gifts, 0);
            k--;
        }

        // 超 Int 最大值
        long sum = 0;
        for (int gift : gifts) {
            sum += gift;
        }
        return sum;
    }

    private static void heapify(int[] h) {
        // 最后一个非叶子节点开始堆化
        for (int i = h.length / 2 - 1; i >= 0; i--) {
            sink(h, i);
        }
    }

    // 大顶堆
    private static void sink(int[] h, int i) {
        int n = h.length;
        // 存在叶子节点, i 是非叶子节点(父节点)
        while (2 * i + 1 < n) {
            // 假设 左子节点比右子节点大
            int l = 2 * i + 1;
            if (l + 1 < n && h[l + 1] > h[l]) {
                l++;
            }
            // 父节点大于等于 最大的子节点
            if (h[i] >= h[l]) {
                break;
            }
            // 交换
            swap(h, i, l);
            // 下沉比较
            i = l;
        }
    }

    private static void swap(int[] h, int i, int j) {
        int temp = h[i];
        h[i] = h[j];
        h[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(pickGifts(new int[]{25, 64, 9, 4, 100}, 4));
    }

}
