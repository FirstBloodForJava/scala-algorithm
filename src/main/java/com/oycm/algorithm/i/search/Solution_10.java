package com.oycm.algorithm.i.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
         */
        int[] ans = new int[k];

        return ans;
    }
}

class Solution_1735 {

    /**
     * 1735. <a href="https://leetcode.cn/problems/count-ways-to-make-array-with-product/description/">生成乘积数组的方案数</a> 2500
     *
     * @param queries queries[i] = [n, k], 第 i 个查询 queries[i] 要求构造长度为 n，每个元素都是正整数的数组，且满足所有元素的乘积为 k，找出有多少种可行的方案，对 1e9 + 7 取模。
     * @return
     */
    public int[] waysToFillArray(int[][] queries) {
        int[] ans = new int[queries.length];

        return ans;
    }

}
