package com.oycm.datastructure.heap.opposite;

import java.util.PriorityQueue;

public class Solution_2 {

    /**
     * 295. <a href="https://leetcode.cn/problems/find-median-from-data-stream/description/">数据流的中位数</a>
     * <p>
     * 中位数是有序整数列表中的中间值。如果是偶数，则没有中间值，中位数两个中间值的平均值
     */
    static class MedianFinder {

        /*
        小顶堆放 中间右边的数
        大顶堆放 中间左边的数
         */
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a);

        public MedianFinder() {

        }

        /**
         * 将 num 添加到数据流中
         *
         * @param num
         */
        public void addNum(int num) {
            /*
            定义添加元素的规则 num min.peek() max.peek()
                都为空时, 元素先添加到 min 中;
                还需要维持 大小堆 元素个数 差 <= 1
            题解思路, 定义两个堆 max 堆表示中间左边的堆 其中堆中所有元素小于 min 堆中的元素, 且当 数量为奇数时 max.size = min.size + 1
            分类讨论:
                当 max.size() == min.size() 时;
                    如果 num >= min.peek(), 需要将 max.add(min.poll()), 再 min.add(num);
                    如果 num < min.peek(), max.add(num)
                    综上所述, 不管 num 和 min 的大小如何, 只需要 执行 min.add(num); max.add(min.poll());

                当 max.size() == min.size() + 1 时;
                    如果 num >= min.peek(), min.add(num)
                    如果 num < min.peek(), 需要 判断 max.peek() 和 num 哪个更大, 哪个添加到 min
                    综上所述, 不管 num 和 min 的大小如何, 只需要执行 max.add(num); min.add(max.poll());
             */
            if (max.size() == min.size()) {
                min.add(num);
                max.add(min.poll());
            } else {
                max.add(num);
                min.add(max.poll());
            }

        }

        /**
         * @return 求所有元素的中位数
         */
        public double findMedian() {
            if (min.size() < max.size()) {
                return max.peek();
            }
            return (min.peek() + max.peek())/ 2.0;
        }
    }

}
