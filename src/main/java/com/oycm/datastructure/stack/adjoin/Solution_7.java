package com.oycm.datastructure.stack.adjoin;

import java.util.ArrayList;
import java.util.List;

public class Solution_7 {

    /**
     * 1209. <a href="https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string-ii/description/">删除字符串中的所有相邻重复项 II</a> 1542
     *
     * @param s
     * @param k
     * @return 重复删除字符出现 k 次的结果
     */
    public String removeDuplicates(String s, int k) {
        /*
        怎么知道 一个字符是否连续出现了 k 次？ List<Stack>
        list 为空时，新建一个栈添加元素
        list 不为空，s[i] 是否和 中元素相同，不相同则新开一个栈记录，相同判断是否符合删除要求，符合要求删除栈，并丢弃该栈
        开始下标和结束下标
         */
        int n = s.length();
        // 二元组 [i, cnt]
        List<int[]> list = new ArrayList<>();
        char[] cs = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            ans.append(cs[i]);
            if (list.isEmpty()) {
                list.add(new int[]{i, 1});
            } else if (cs[i] == cs[list.get(list.size() - 1)[0]]) {
                int[] ints = list.get(list.size() - 1);
                ints[1]++;
                if (ints[1] == k) {
                    // 删除
                    ans.delete(ans.length() - k, ans.length());
                    // 删除栈
                    list.remove(list.size() - 1);
                }
            } else {
                list.add(new int[]{i, 1});
            }
        }

        return ans.toString();
    }

}
