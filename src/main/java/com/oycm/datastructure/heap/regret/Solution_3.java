package com.oycm.datastructure.heap.regret;


import com.oycm.DataCreateUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_3 {

    /**
     * 630. <a href="https://leetcode.cn/problems/course-schedule-iii/description/">课程表 III</a>
     *
     * @param courses courses[i] = [duration, lastDay]  表示第 i 门课将会 持续 上 duration 天课，并且必须在不晚于 lastDay 的时候完成。
     * @return 学期从第 1 天开始, 且不能同时修读两门及两门以上的课程, 求最多能修读的课程数目
     */
    public static int scheduleCourse(int[][] courses) {
        /*
        lastDay >= duration 这门课才能修，是按持续时间还是截至时间排序处理？
        按持续时间排序[[1,9], [1,10]]排序, 持续时间相同, 按截至时间升序排序, 不能重复, 优先选持续时间最短的, 才能选更多的课程
            [[1,9], [1,10], [4,4]] 但是如果后面有 结束时间比较短的课程, 该怎么判断 是否能选?

        按结束时间排序[[4,4], [1,9], [1,10]] 选 [4,4] 时, 记录消耗的选择时间(cost), 能知道下次的选择的开始时间(start)
            如果 start + curDuration <= curLastDay, 则当前课程可选; 记录 cost, 更新 start
            如果 start + curDuration > curLastDay, 则可以取历史最大的 cost 和 curDuration 比较,
                如果 cost > curDuration, 则 不选前面开始时间短, 耗时长的课程, 弹出最大值, 记录 cost 和 更新 start

         */

        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a);
        // 消耗 cost [i, i + cost - 1] i+cost-1 是不可取的
        int start = 0;
        for (int[] cour : courses) {
            int cost = cour[0];
            int last = cour[1];
            if (cost + start <= last) {
                // 可选课
                start += cost;
                max.add(cost);
            } else if (!max.isEmpty() && max.peek() > cost) {
                // 当前 cost + start > last
                start += cost - max.poll();
                max.add(cost);

            }
        }

        return max.size();
    }

    public static void main(String[] args) {
        System.out.println(scheduleCourse(DataCreateUtils.twoDimensionInts("[[7,17],[3,12],[10,20],[9,10],[5,20],[10,19],[4,18]]")));
    }

}
