package com.oycm.month2026.march;

public class Solution_8 {

    /**
     * 1980. <a href="https://leetcode.cn/problems/find-unique-binary-string/description/">找出不同的二进制字符串</a> 1362
     *
     * @param nums
     * @return
     */
    public String findDifferentBinaryString(String[] nums) {
        /*
        题解思路: 构造一个字符串 ans, ans[i] 和 nums[i][i] 都不相同, 满足 ans 和每个 nums[i] 都至少有一位不同
         */
        int n = nums.length;
        char[] ans = new char[n];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = (char) (nums[i].charAt(i) ^ 1);
        }

        return new String(ans);
    }

}
