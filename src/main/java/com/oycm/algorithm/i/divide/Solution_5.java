package com.oycm.algorithm.i.divide;

import java.util.ArrayList;
import java.util.List;

public class Solution_5 {

    /**
     * 93. <a href="https://leetcode.cn/problems/restore-ip-addresses/description/">复原 IP 地址</a>
     * <p>
     * 有效 IP 地址 正好由四个整数（[0, 255]，且不能含有前导 0），整数之间用 '.' 分隔
     *
     * @param s 只包含数字的字符串
     * @return 所有可能的有效 IP 地址，可以通过在 s 中插入 '.' 来形成。不能 重新排序或删除 s 中的任何数字
     */
    public List<String> restoreIpAddresses(String s) {
        /*
        3 * 4 = 12 超过这个长度肯定无法构成

        站在答案的视角
        是否分割 s [i, j] [j ?] 后续怎么分割
            必须分割的条件：已经分割三次；当前字符是 0
            分隔完毕，分隔次数为 4 才记录答案
         */
        List<String> ans = new ArrayList<>();
        if (s.length() > 12) {
            return ans;
        }


        return ans;
    }

    /**
     * 三重循环
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses_bruteforce(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length() > 12) {
            return ans;
        }
        int n = s.length();
        for (int i = 1; i < n && isValid(s, 0, i); i++) {
            for (int j = i + 1; j < n && isValid(s, i, j); j++) {
                for (int k = j + 1; k < n && isValid(s, j, k); k++) {
                    if (isValid(s, k, n)) {
                        ans.add(String.format("%s.%s.%s.%s",
                                s.substring(0, i),
                                s.substring(i, j),
                                s.substring(j, k),
                                s.substring(k)));
                    }
                }
            }
        }

        return ans;
    }

    public boolean isValid(String s, int i, int j) {
        if (j - i > 3 || j - i > 1 && s.charAt(i) == '0') {
            return false;
        }
        return Integer.parseInt(s.substring(i, j)) <= 255;
    }

    public List<String> restoreIpAddresses_inputPerspective(String s) {
        List<String> ans = new ArrayList<>();
        dfs(0, 0, 0, s, s.length(), new int[4], ans);
        return ans;
    }

    /**
     * 选/不选
     * @param i
     * @param j 选的次数
     * @param ipVal 选的 ip 值
     * @param s
     * @param n
     * @param path
     * @param ans
     */
    private void dfs(int i, int j, int ipVal, String s, int n, int[] path, List<String> ans) {
        if (i == n) { // s 分割完毕
            if (j == 4) { // 必须有 4 段
                int a = path[0], b = path[1], c = path[2];
                ans.add(s.substring(0, a) + "." + s.substring(a, b) + "." + s.substring(b, c) + "." + s.substring(c));
            }
            return;
        }

        if (j == 4) { // j = 4 的时候必须分割完毕，不能有剩余字符
            return;
        }

        // 这样字符串转整数是 O(1)
        ipVal = ipVal * 10 + (s.charAt(i) - '0');
        if (ipVal > 255) { // 不合法
            return;
        }

        // s[i] != '0' 才能不分割
        if (ipVal > 0) {
            dfs(i + 1, j, ipVal, s, n, path, ans);
        }

        // 分割，以 s[i] 为这一段的结尾
        path[j] = i + 1; // 记录这一段的结束位置 + 1
        dfs(i + 1, j + 1, 0, s, n, path, ans);
    }


    public List<String> restoreIpAddresses_answerPerspective(String s) {
        List<String> ans = new ArrayList<>();
        dfs(0, 0, s, s.length(), new String[4], ans);
        return ans;
    }

    /**
     * 答案视角
     * @param i
     * @param j
     * @param s
     * @param n
     * @param path
     * @param ans
     */
    private void dfs(int i, int j, String s, int n, String[] path, List<String> ans) {
        // 剪枝：还剩下 n-i 个字符，需要分成 4-j 段，每段至少 1 个字符，至多 3 个字符，所以 4-j <= n-i <= (4-j)*3
        if (n - i < 4 - j || n - i > (4 - j) * 3) {
            return;
        }
        // 分割完成
        if (i == n) {
            ans.add(String.join(".", path));
            return;
        }

        // 子串左端点为 i
        // 枚举子串右端点 right
        int ipVal = 0;
        for (int right = i; right < n; right++) {
            ipVal = ipVal * 10 + (s.charAt(right) - '0');
            if (ipVal > 255) {
                break;
            }
            path[j] = s.substring(i, right + 1); // 直接覆盖 path[j]，无需恢复现场，因为 i == n 才会记录答案，每次都会更新
            dfs(right + 1, j + 1, s, n, path, ans);
            if (ipVal == 0) {
                // 前导 0，当前不能玩后分割
                break;
            }
        }
    }


    /**
     * 答案视角
     *
     * @param i
     * @param s
     * @param path
     * @param ans
     * @param size 已经分割次数
     */
    public void dfs(int i, String s, List<String> path, List<String> ans, int size) {
        if (size == 3) {
            // 前面分割 3 次了，最后一次必须分割，且有可用字符
            if (i == s.length() || s.charAt(i) == '0' && i != s.length() - 1) {
                return;
            }
            // 必须分割到底
            String sub = s.substring(i);
            int val = Integer.parseInt(sub);
            // 大于 255, 分割无效
            if (val > 255) {
                return;
            }
            path.add(sub);
            dfs(s.length(), s, path, ans, size + 1);
            path.remove(path.size() - 1);
            return;
        }
        // 分割完毕
        if (i == s.length()) {
            if (size == 4) ans.add(String.join(".", path));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (s.charAt(i) == '0') {
                // 0 开始，必须在 [i, i+1]分割
                path.add("0");
                dfs(i + 1, s, path, ans, size + 1);
                path.remove(path.size() - 1);
                break;
            }
            // [i, j]
            String sub = s.substring(i, j + 1);
            int val = Integer.parseInt(sub);
            // 大于 255, 分割无效
            if (val > 255) {
                break;
            }
            path.add(sub);
            dfs(j + 1, s, path, ans, size + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution_5 solution = new Solution_5();
        System.out.println(solution.restoreIpAddresses("1111"));

    }

}

