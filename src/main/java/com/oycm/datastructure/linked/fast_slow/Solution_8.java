package com.oycm.datastructure.linked.fast_slow;


public class Solution_8 {

    /**
     * 457. <a href="https://leetcode.cn/problems/circular-array-loop/description/">环形数组是否存在循环</a>
     * 每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数:
     * - nums[i] > 0, 向前（下标递增方向）移动 nums[i] 步
     * - nums[i] < 0, 向后（下标递减方向）移动 abs(nums[i]) 步
     *
     * @param nums nums[i] != 0
     * @return 判断数组是否能构成循环, 且循环长度 k > 1, 且循环向同一个方向跳跃
     */
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i; int fast = next(nums, i);
            // 方向相同 &&
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow != next(nums, fast)) {
                        return true;
                    } else {
                        break;
                    }
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }
            // 前面慢指针走过的点, 不符合条件
            int pre = i;
            while (nums[pre] * nums[next(nums, pre)] > 0) {
                int temp = pre;
                pre = next(nums, pre);
                nums[temp] = 0;
            }

        }

        return false;
    }

    public int next(int[] nums, int cur) {
        int n = nums.length;
        return ((cur + nums[cur]) % n + n) % n;
    }

    public static void main(String[] args) {

    }

}
