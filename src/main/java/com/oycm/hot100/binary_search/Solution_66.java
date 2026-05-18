package com.oycm.hot100.binary_search;

public class Solution_66 {

    /**
     * 33. <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/description/">搜索旋转排序数组</a>
     *
     * @param nums   nums.length [1, 5000], nums 中的每个值都不相同
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        /*
        整数数组 nums 按升序排列，数组中的值 互不相同。
        在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为
            [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
        例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2]。
        给你 旋转后 的数组 nums 和一个整数 target ，
            如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1。
         */
        /*
        如果 nums[0] > nums[n-1] 说明数组进行了旋转，第一段所有元素都大于 nums[n-1]
        二分查找 target 在哪一段
            如果 nums[mid] == target，返回 target；
            如果 nums[mid] > nums[n-1]，说明数组旋转了，且 mid 在第一段，这样要判断 target 到底在那一段
                如果 target < nums[n-1]，说明 target 在第二段，左边不会存在 nums[i] == target，l = mid；
                如果 target > nums[mid]，说明 target 在第一段的右边 l = mid；
                否则 r = mid
                可以在查找前，先判断 target 和 nums[n-1] 是否相等，避免这里的判断
            如果 nums[mid] < nums[n-1]，说明 mid 可能在第二段或 k = 0
                如果 target < nums[mid]，[mid, r) 都大于 target，r = mid；
                如果 target > nums[mid]，要判断数组是否旋转，
                    如果 nums[0] > nums[mid] 且 target >= nums[0]，说明数组旋转，target 只能在第一段 r = mid；
                    否则，只有一段 l = mid；
                        ，两段 target < nums[0] l = mid；
         */
        int n = nums.length;
        int l = -1;
        int r = nums.length - 1;
        if (nums[n - 1] == target) return n - 1;
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > nums[n - 1]) {
                // 两段升序数组
                if (target < nums[n - 1] || target > nums[mid]) {
                    l = mid;
                } else {
                    r = mid;
                }
            } else {
                // mid 位于第二段数组，或整段都是升序
                if (target < nums[mid]) {
                    r = mid;
                } else if (nums[0] > nums[mid] && target >= nums[0]) {
                    // 两段，大于第一段的最小值
                    r = mid;
                } else {
                    l = mid;
                }
            }
        }
        return nums[r] == target ? r : -1;
    }

    public int search_optimize(int[] nums, int target) {
        /*
        讨论优化：
        二分查找 x = nums[mid] 和 target 的位置关系，
        x 和 target 和 nums[n-1] 可以确定位置关系。
        分类讨论：
            如果 x 和 target 在不同的段：
                x 在第一段，target 在第二段，x 在 target 的左边；(l, mid] 都大于 nums[n-1] 大于 target，l = mid；
                x 在第二段，target 在第一段，x 在 target 的右边，[mid, r) 都小于 target，r = mid;
            如果 x 和 target 在相同的段：
                和 target 直接比大小，二分查找 大于等于 target 的第一个 下标
        开区间二分，初始化 r = n-1，这样 r 不计算，也不会越界
         */
        int n = nums.length;
        int last = nums[n - 1];
        int l = -1;
        int r = n - 1;
        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            int x = nums[mid];
            if (target > last && x <= last) {
                r = mid;
            } else if (target <= last && x > last) {
                l = mid;
            } else if (x >= target) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return nums[r] == target ? r : -1;
    }

    public int search_left(int[] nums, int target) {
        /*
        二分查找大于等于 target 的第一个下标时，当 nums[mid] >= target 不断玩左移动 r，直到循环不成立。
        为了满足返回的 r 能作为判断的条件，只考虑 r = mid 的情况，target 在 x 的左边，或 target 就是 x；否则，target 在 x 的右边。
        target 在 x 左边的情况，分类讨论：
            如果 x > nums[n-1]，说明 x 在第一段，target 在 x 的左边，则要满足 target <= x && target > nums[n-1]；
            如果 x <= nums[n-1]，说明 x 在第二段，或 nums 只有一段，那么 target 可以在第一段，也可以在第二段；
                target 在第一段，一定在左边，要满足 target > nums[n-1]
                target 在第二段，在左边，要满足 target <= x
         */
        int last = nums[nums.length - 1];
        int l = -1;
        int r = nums.length - 1;

        while (l + 1 < r) {
            int mid = (l + r) >>> 1;
            if (check(nums[mid], last, target)) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return nums[r] == target ? r : -1;
    }

    public boolean check(int x, int last, int target) {
        if (x > last) return target <= x && target > last;
        return target > last || x >= target;
    }

}
