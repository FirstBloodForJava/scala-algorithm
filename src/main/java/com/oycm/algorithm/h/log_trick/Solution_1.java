package com.oycm.algorithm.h.log_trick;


public class Solution_1 {

    /**
     * 3171. <a href="https://leetcode.cn/problems/find-subarray-with-bitwise-or-closest-to-k/description/">找到按位或最接近 K 的子数组</a> 2163
     * <p>
     * 子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public static int minimumDifference(int[] nums, int k) {
        /*
        当 [l, r] 或值和 小于 k 时, 往后加异或才可能使绝对值更小
        当 [l, r + 1] 或值和 大于等于 k 时, 往后加异或只能是绝对值更大
        记录当前或值和 [0, 29] 下标 1 的数量, 当 nums[l, r] 或值和 大于等于 k, 当前或值下标 1 数量减少 1, 数量为 0 时, ^ (1 << index) 去掉该值
        具有单调性，应该可以使用滑动窗口解决吧
         */
        int ans = Integer.MAX_VALUE;
        int[] cnt = new int[30];
        int n = nums.length;
        int l = 0, r = 0, or = 0;
        while (r < n) {
            int x = nums[r];
            or |= x;
            int i = 0;
            // 查找 x 是 1 的下标
            while (x >= 1 << i) {
                cnt[i] += (x & 1 << i) > 0 ? 1 : 0;
                i++;
            }
            ans = Math.min(ans, Math.abs(or - k));

            while (or >= k) {
                int pre = nums[l];
                int j = 0;
                while (pre >= 1 << j) {
                    cnt[j] -= (pre & 1 << j) > 0 ? 1 : 0;
                    // 1 存在 > 0
                    if (cnt[j] == 0 && (pre & 1 << j) > 0) {
                        or ^= 1 << j;
                    }
                    j++;
                }
                if (or > 0) {
                    ans = Math.min(ans, Math.abs(or - k));
                }
                l++;
            }
            if (ans == 0) {
                break;
            }
            r++;

        }

        return ans;
    }

    public static void main(String[] args) {
        minimumDifference(new int[]{1,3,1,3}, 2);
    }
}
