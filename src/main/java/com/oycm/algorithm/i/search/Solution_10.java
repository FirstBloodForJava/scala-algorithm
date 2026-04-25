package com.oycm.algorithm.i.search;

import java.util.*;

public class Solution_10 {

    /**
     * 3669. <a href="https://leetcode.cn/problems/balanced-k-factor-decomposition/description/">K 因数分解</a> 1917
     *
     * @param n
     * @param k
     * @return
     */
    public int[] minDifference(int n, int k) {
        /*
        两个整数 n 和 k，将数字 n 恰好分割成 k 个正整数，使得这些整数的 乘积 等于 n。
        返回一个分割方案，使得这些数字中 最大值 和 最小值 之间的 差值 最小化。结果可以以 任意顺序 返回。
         */
        /*
        k 个 整数肯定都是 n 的因子，先找出 n 的所有因子集合
        一个数可以多选, 肯定有一个答案是 k-1 个 1, 1 个 n;
         */
        ans = new int[k];
        Arrays.fill(ans, 1);
        ans[k - 1] = n;

        List<Integer> factors = getFactors(n);
        dfs(0, 0, 1, new int[k], n, factors);
        System.out.println("cnt" + x);
        return ans;
    }

    int[] ans;
    int x = 0;

    public void dfs(int i, int start, int prod, int[] path, int n, List<Integer> factors) {
        // 选的乘积过大，剪枝
        if (prod > n || start == factors.size()) {
            return;
        }
        if (i == path.length) {
            x++;
            if (prod == n && path[path.length - 1] - path[0] < ans[ans.length - 1] - ans[0]) {
                for (int k = 0; k < ans.length; k++) {
                    ans[k] = path[k];
                }
            }
            return;
        }
        // 不选
        dfs(i, start + 1, prod, path, n, factors);

        // 选
        path[i] = factors.get(start);
        dfs(i + 1, start, prod * path[i], path, n, factors);
    }

    // 获取 n 的所有因子，从小到大排序
    public List<Integer> getFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                if (i * i != n) {
                    factors.add(n / i);
                }
            }
        }
        Collections.sort(factors);
        return factors;
    }

    public static void main(String[] args) {
        Solution_10 solution = new Solution_10();
        int[] ints = solution.minDifference(100, 2);
        System.out.println(ints[0] + ", " + ints[1]);
    }
}

class Solution_3669 {

    public int[] minDifference(int n, int k) {
        /*
        20 [1, 20, 2, 10, 4, 5]
        拆分 n 一对因子，20 = [1, 20]
        可以预处理因子，枚举因子做法
         */
        int[] path = new int[k];
        dfs(0, Integer.MAX_VALUE, Integer.MIN_VALUE, n, path);
        return ans;
    }

    private int minDiff = Integer.MAX_VALUE;
    private int[] ans;

    public void dfs(int i, int min, int max, int n, int[] path) {
        if (i == path.length - 1) {
            // 最后一个数是 n
            int d = Math.max(n, max) - Math.min(n, min);
            if (d < minDiff) {
                path[i] = n;
                minDiff = d;
                ans = Arrays.copyOfRange(path, 0, path.length);
            }
            return;
        }
        for (int p = 1; p <= n; p++) {
            if (n % p == 0) {
                path[i] = p;
                dfs(i + 1, Math.min(min, p), Math.max(max, p), n / p, path);
            }
        }
    }

}

class Solution_3669_1 {

    private static final int MX = 100_001;
    private static final List<Integer>[] divisors = new ArrayList[MX];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        // 预处理每个数的因子
        Arrays.setAll(divisors, l -> new ArrayList<>());
        for (int i = 1; i < MX; i++) {
            for (int j = i; j < MX; j += i) { // 枚举 i 的倍数 j
                divisors[j].add(i); // i 是 j 的因子
            }
        }
    }

    private int minDiff = Integer.MAX_VALUE;
    private int[] ans;

    public int[] minDifference(int n, int k) {
        init();
        int[] path = new int[k];
        dfs(0, n, path);
        return ans;
    }

    private void dfs(int i, int n, int[] path) {
        if (i == path.length - 1) {
            // path[0] 最小，n 最大
            if (n - path[0] < minDiff) {
                minDiff = n - path[0];
                path[i] = n;
                ans = path.clone();
            }
            return;
        }
        int maxD = (int) Math.sqrt(n);
        for (int d : divisors[n]) { // 枚举 n 的因子 d
            // 剪枝
            if (d > maxD || i > 0 && d - path[0] >= minDiff) {
                break;
            }
            if (i == 0 || d >= path[i - 1]) {
                path[i] = d;
                dfs(i + 1, n / d, path);
            }
        }
    }

}
