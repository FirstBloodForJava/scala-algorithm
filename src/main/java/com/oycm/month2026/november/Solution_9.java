package com.oycm.month2026.november;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_9 {

    /**
     * 1606. <a href="https://leetcode.cn/problems/find-servers-that-handled-most-number-of-requests/description/">找到处理最多请求的服务器</a> 2276
     *
     * @param k
     * @param arrival
     * @param load
     * @return
     */
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        /*
        你有 k 个服务器，编号为 0 到 k-1 ，它们可以同时处理多个请求组。
        每个服务器有无穷的计算能力但是 不能同时处理超过一个请求 。请求分配到服务器的规则如下：
            第 i （序号从 0 开始）个请求到达。
            如果所有服务器都已被占据，那么该请求被舍弃（完全不处理）。
            如果第 (i % k) 个服务器空闲，那么对应服务器会处理该请求。
            否则，将请求安排给下一个空闲的服务器（服务器构成一个环，必要的话可能从第 0 个服务器开始继续找下一个空闲的服务器）。比方说，如果第 i 个服务器在忙，那么会查看第 (i+1) 个服务器，第 (i+2) 个服务器等等。
        给你一个 严格递增 的正整数数组 arrival ，表示第 i 个任务的到达时间，和另一个数组 load ，其中 load[i] 表示第 i 个请求的工作量（也就是服务器完成它所需要的时间）。
        你的任务是找到 最繁忙的服务器 。最繁忙定义为一个服务器处理的请求数是所有服务器里最多的。
        请你返回包含所有 最繁忙服务器 序号的列表，你可以以任意顺序返回这个列表。
         */
        int n = arrival.length;
        if (k >= n) {
            List<Integer> ans = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                ans.add(i);
            }
            return ans;
        }
        PriorityQueue<Integer> idle = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            idle.offer(i);
        }
        // [0] 服务器处理完成时间, [1] 服务器编号
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[] cnt = new int[k];
        int maxCnt = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                // 该服务器空闲
                int j = busy.poll()[1];
                //
                int virtual = i + (j - i % k + k) % k;
                idle.offer(virtual);
            }
            // 没有空闲服务器
            if (idle.isEmpty()) {
                continue;
            }
            int j = idle.poll() % k;
            busy.offer(new int[]{arrival[i] + load[i], j});
            cnt[j]++;
            if (cnt[j] > maxCnt) {
                maxCnt = cnt[j];
                ans.clear();
                ans.add(j);
            } else if (cnt[j] == maxCnt) {
                ans.add(j);
            }
        }

        return ans;
    }

}
