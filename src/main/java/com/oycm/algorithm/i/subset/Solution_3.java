package com.oycm.algorithm.i.subset;

import java.util.ArrayList;
import java.util.List;

public class Solution_3 {

    /**
     * 784. <a href="https://leetcode.cn/problems/letter-case-permutation/description/">字母大小写全排列</a>
     *
     * @param s s.length [1, 12]
     * @return
     */
    public List<String> letterCasePermutation(String s) {
        /*
        Answer Perspective 答案视角
        枚举第 1 个数是否转换
        枚举第 2 个数是否转换
        ...
        每个节点都是答案
        回溯三问：
            当前操作？枚举一个下标 j >= i 转换大小写, 加入 path
            子问题？从下标 >= i 的数字中构造子集
            下一个子问题？从下标 >= j+1 的数字中构造字母
         */
        List<String> ans = new ArrayList<>();
        dfs(s.toCharArray(), 0, ans);
        return ans;
    }

    public void dfs(char[] path, int i, List<String> ans) {
        ans.add(new String(path));
        for (int j = i; j < path.length; j++) {
            if (Character.isLetter(path[j])) {
                path[j] ^= 32;
                dfs(path, i + 1, ans);
                // 大小写转换的结果遍历完之后，恢复成不转换
                path[j] ^= 32;
            }
        }
    }


}

class Solution_3_InputPerspective {

    public List<String> letterCasePermutation(String s) {
        /*
        选/不选 思路
        回溯三问：
            当前操作？枚举第 i 个, 如果是字母, 选当前/不选当前(转变)
            子问题？从下标 >= i 构造子集
            下一个子问题？从下标 >= i+1 构造子集
         */
        List<String> ans = new ArrayList<>();
        dfs(s, new char[s.length()], 0, ans);
        return ans;
    }

    public void dfs(String s, char[] path, int i, List<String> ans) {
        if (i == s.length()) {
            ans.add(new String(path));
            return;
        }
        char c = s.charAt(i);
        // 选当前字符
        path[i] = c;
        dfs(s, path, i + 1, ans);
        if (Character.isLetter(c)) {
            path[i] ^= 32;
            dfs(s, path, i + 1, ans);
        }
    }
}
