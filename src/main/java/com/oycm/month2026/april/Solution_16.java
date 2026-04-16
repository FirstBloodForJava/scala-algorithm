package com.oycm.month2026.april;

import java.util.*;

public class Solution_16 {

    /**
     * 3488. <a href="https://leetcode.cn/problems/closest-equal-element-queries/description/">距离最小相等元素查询</a> 1699
     *
     * @param nums    环形 数组，nums.length [1, 1e5]
     * @param queries queries[i] [0, nums.length)
     * @return
     */
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        /*
        对于每个 queries[i], 查询 nums[j] == nums[queries[i]], j != queries[i], j 与 queries[i] 的最小距离, 不存在 j，则返回 -1
        j 距离最小值为 min(abs(j - queries[i]), n - abs(j - queries[i]))
         */
        /*
        定义 left[i] 表示 i = queries[j] 左边最近的下标
         */
        List<Integer> ans = new ArrayList<>();
        Map<Integer,Integer> pos = new HashMap<>();
        int n = nums.length;
        int[] left = new int[n];


        int[] right = new int[n];

        return ans;
    }

    public static void main(String[] args) {
        Solution_16 solution = new Solution_16();

        solution.solveQueries(new int[]{1, 3, 1, 4, 1, 3, 2}, new int[]{0, 3, 5});
    }

}

class Solution_16_Binary {

    public List<Integer> solveQueries(int[] nums, int[] queries) {

        /*
        map 记录相同值的有序下标
        queries[i] 计算的普通数组的最小距离, 最小距离为 queries[i] 前后两点, pre, queries[i], next, queries[i] 记为 k, 最小距离为 min(k - pre, next - k)。
            如果 k = 0, 或 k = n-1，可以增加两个哨兵节点
                前面添加 -n, k - pre = k + n, 不会取到该值
                后面添加 2n, next - k = 2n - k > n, 不会取到该值
        queries[i] 记为 k，查询环形数组的最小距离，假设都存在前后两点的情况 pre, k, next
            pre -> k = min(k - pre, n + pre - k),
                最小值为 k - pre 时，pre 前面的点只有走环才能取最小距离，最小距离永远 >= next - k
                最小值为 n + pre - k 时，pre 前面点的最小值要经过 next，最小距离永远 >= next - k
                所以 n + pre - k 的情况不用考虑，只需要考虑 k - pre 和 next - k 最小值
            k -> next = min(next - k, n + k - next),
                最小值为 next - k 时，next 后面的点只有走环才能取最小距离，最小距离 >= k - pre
                最小值为 n + k -next 时，next 后面的点只有走环才能取最小距离，最小距离永远 >= k - pre
                所以 n + k - next 的情况不用考虑，只需要考虑 k - pre 和 next - k 的最小值
            当 k == 0 或 k == size - 1 时，就需要特殊判断了，k == 0 对于的下标值记为 first，k == size - 1 的下标记为 last
                k == 0, 没有前一个点，经过 next 到达 size - 1 肯定 >= next - k，所以需要比较 first 走前面到达 last 的距离为 n - (last - first) = n - last + first
                    可以在 first 前面添加一个哨兵节点 first - ? = d, ? = -(n - last) = last - n;
                k == size - 1 时，next 节点是 first，经过 pre 到达 first 距离肯定 >= k - pre, 所以需要比较 next 往后走到达 first 的距离 n - (last - first)
                    可以再 last 后面添加一个哨兵节点，? - last = d, ? - last = n - last + first => ? = n + first
         */
        List<Integer> ans = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], l -> new ArrayList<>()).add(i);
        }
        for (List<Integer> list : map.values()) {
            int first = list.get(0);
            // first 前面节点 last - n
            list.add(0, list.get(list.size() - 1) - n);
            // last 后面节点 n + first
            list.add(n + first);
        }
        for (int k : queries) {
            List<Integer> list = map.get(nums[k]);
            if (list == null || list.size() == 3) {
                ans.add(-1);
                continue;
            }
            int idx = Collections.binarySearch(list, k);
            ans.add(Math.min(k - list.get(idx - 1), list.get(idx + 1) - k));
        }

        return ans;
    }
}
