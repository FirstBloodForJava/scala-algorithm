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

    public static int logTrick(int[] nums, int k) {
        /*
        可以使用一个二重循环暴力计算 or 值和, 外层循环, 从 i = 0 开始, 从左到右; 内存循环从 j = i-1 开始, 从右到左, 更新 nums[j] = nums[j] | nums[i]
        这样就能枚举所有 i 开始的 从右到左的所有子数组
        i = 1, 子数组有 nums[1]; nums[0] = nums[0] | nums[1];
        i = 2, 子数组有 nums[2]; nums[1] = nums[1] | nums[2]; nums[0] = nums[0] | nums[2](由于前面 nums[0] = nums[0] | nums[1], 这里就是 0,1,2)
        i = 3, 子数组有 nums[3]; nums[2] = nums[2] | nums[3]; nums[1] = nums[1] | nums[3](由于前面 nums[1] = nums[1] | nums[2], 这里就是 1,2,3)
                        nums[0] = nums[0] | nums[3], nums[0] = 0,1,2 这里就是 0,1,2,3
        按照上面的计算过程, 能枚举出所有的子数组 or 值
        把修改后的 nums[i] 看作集合 A[]
        i = 1, A0 包含 A1
        i = 2, A0 包含 A1, A1 包含 A2
        i = 3, A0 包含 A1, A1 包含 A2, A2 包含 A3
        A0 , A1, A2, A3 在计算 nums[j] | nums[i] 的过程中, 如果 nums[j] | nums[i] == nums[j], 说明后续的在上次已经遍历过了
         */
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            ans = Math.min(ans, Math.abs(x - k));
            for (int j = i - 1; j >= 0 && (x | nums[j]) != nums[j]; j--) {
                nums[j] |= x;
                ans = Math.min(ans, Math.abs(nums[j] - k));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        minimumDifference(new int[]{1, 3, 1, 3}, 2);
    }
}
