package com.oycm.datastructure.stack.monotonic.advance;


import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_2 {

    /**
     * 768. <a href="https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/description/">最多能完成排序的块 II</a> 1788
     *
     * @param arr
     * @return 将 arr 分割成连续的子数组, 并将子数组排序后, 按原来的顺序连接结果 和 原数组按升序排序的结果相同
     */
    public int maxChunksToSorted(int[] arr) {
        /*
        如果分割块数 大于 1, 则左边块所有元素 小于等于 右边块所有元素
        题解思路: 新添加的数字会影响现有的分块方式
            如果 新元素 大于等于 前面的 最大元素, 则可以新开一个块
            如果 新元素 小于 前面的 最大元素, 则前面的块需要和当前的块合并, 移除不符合要求的块, 直到 没有元素 或 剩余最大元素 小于等于 新元素
        这里只维护了最大值, 比较过程 从右到左, 可以使用单调栈
         */
        Deque<Integer> max = new ArrayDeque<>();
        for (int num : arr) {
            if (max.isEmpty() || num >= max.peek()) {
                max.push(num);
            } else {
                int mx = max.peek();
                while (!max.isEmpty() && num < max.peek()) {
                    max.pop();
                }
                max.push(mx);
            }
        }

        return max.size();
    }

}
