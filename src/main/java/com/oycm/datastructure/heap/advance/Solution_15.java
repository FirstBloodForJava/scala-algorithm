package com.oycm.datastructure.heap.advance;

import com.oycm.DataCreateUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_15 {

    /**
     * 2402. <a href="https://leetcode.cn/problems/meeting-rooms-iii/description/">会议室 III</a> 2093
     * <p>
     * 会议将会按以下方式分配给会议室：
     * <p>
     * - 每场会议都会在未占用且编号 最小 的会议室举办
     * <p>
     * - 如果没有可用的会议室，会议将会延期，直到存在空闲的会议室。延期会议的持续时间和原会议持续时间 相同。
     * <p>
     * - 当会议室处于未占用状态时，将会优先提供给原 开始 时间更早的会议。
     *
     * @param n        0 to n-1 个会议室
     * @param meetings meetings[i] = [start, end], 表示会议 i 在 [start, end) 时间举办
     * @return 求举办最多次会议的房间 编号 。如果存在多个房间满足此条件，则返回编号 最小 的房间。
     */
    public static int mostBooked(int n, int[][] meetings) {
        /*
        先对 meetings 数组按 会议开始时间升序排序
        模拟过程: meetings 按开始时间 升序, 举办第一个会议, 占用会议室 0
        举办第二个会议时, 需要查看上一个会议是否结束, 结束条件是 start >= lastEnd, 如果结束则优先选择前面的会议室;
            如果未结束, 还有会议室可用, 则使用新的会议室, 记录 会议结束时间;
            如果未结束, 无可用会议室, 则所有会议室需要同时等待最早的会议结束, 等待时间 wait = lastEnd - start, 后续的会议开始时间就需要加上等待时间判断前一个会议是否结束
        同时可以使用 hash 表 或数组记录 会议室使用次数
        注意点: 如果会议都是 [0, 5*100000] 有 100000个会议, n = 1, 每一个会议室都需要等待最长时间, 累计到后面是会超 Int 最大值
        未考虑到 同时有两个会议室到期, 编号较大的会议室, 先结束, 这里就会使用到较大的会议室,
         */
        // 记录历史会议的结束时间, 结束时间相同, 优先返回较小的会议室变化
        PriorityQueue<int[]> max = new PriorityQueue<>((a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            min.add(i);
        }
        int wait = 0;
        int[] cnt = new int[n];
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        for (int[] meet : meetings) {
            int start = meet[0];
            int end = meet[1];

            while (!max.isEmpty() && start >= max.peek()[0]) {
                int[] last = max.poll();
                min.add(last[1]);
            }
            // 没有可用会议室, d等待可用会议室
            if (min.isEmpty()) {
                int[] last = max.poll();
                wait = last[0] - start;
                cnt[last[1]]++;
                max.add(new int[]{end + wait, last[1]});
            } else {
                int curr = min.poll();
                max.add(new int[]{end, curr});
                cnt[curr]++;
            }

        }
        int ans = 0;
        int times = 0;
        for (int j = 0; j < cnt.length; j++) {
            if (cnt[j] > times) {
                times = cnt[j];
                ans = j;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(mostBooked(2, DataCreateUtils.twoDimensionInts("[[43,44],[34,36],[11,47],[1,8],[30,33],[45,48],[23,41],[29,30]]")));
    }

}
