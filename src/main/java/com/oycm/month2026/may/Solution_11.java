package com.oycm.month2026.may;

import java.util.ArrayList;
import java.util.List;

public class Solution_11 {

    /**
     * 2553. <a href="https://leetcode.cn/problems/separate-the-digits-in-an-array/description/">分割数组中数字的数位</a> 1217
     *
     * @param nums 正整数数组
     * @return 返回一个数组 answer
     */
    public int[] separateDigits(int[] nums) {
        /*
        需要将 nums 中每个整数进行数位分割后，按照 nums 中出现的 相同顺序 放入答案数组中。
        对一个整数进行数位分割，指的是将整数各个数位按原本出现的顺序排列成数组。
        比方说，整数 10921 ，分割它的各个数位得到 [1,0,9,2,1] 。
         */
        /*
        数位 109 模 10，再除以 10，是倒序获取 109 的数位
        109 / 100 => 1 100/10 => 10 109 % 100 => 9
        9 / 10
        9 / 1
        这样可以正序得到所有数位
         */
        /*
        倒序遍历 nums，num % 10 取数位 num /= 10 计算下一个，这样就得到了与答案相反的结果
        最后反转数组
         */
        List<Integer> ans = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int x = nums[i]; x > 0; x /= 10) {
                ans.add(x % 10);
            }
        }
        int[] answer = new int[ans.size()];
        int l = 0;
        int r = ans.size() - 1;
        while (l <= r) {
            answer[l] = ans.get(r);
            answer[r] = ans.get(l);
            l++;
            r--;
        }

        return answer;
    }


}
