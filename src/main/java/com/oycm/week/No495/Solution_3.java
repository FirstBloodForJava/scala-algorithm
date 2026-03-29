package com.oycm.week.No495;

public class Solution_3 {

    /**
     * 3886. <a href="https://leetcode.cn/problems/sum-of-sortable-integers/description/">可排序整数求和</a>
     *
     * @param nums
     * @return 所有 可排序整数 k 的和
     */
    public int sortableIntegers(int[] nums) {
        /*
        k 是 n 的 因数，且可以通过依次执行以下操作将 nums 排序为 非递减顺序:
            将 nums 划分为长度为 k 的 连续子数组。
            独立地对每个子数组进行循环移动（左移或右移任意次数）。
            3,1,2, 4,6,5
         */
        /*
        解题思路:
        可排序数组条件分析：为 k 的可排序数组，意味着 每个长为 k 的数组是非递减的；且当前子数组的最小值 大于等于 上一个子数组的最大值
        子数组循环移动变成非递减：
            子数组本来就是非递减
            只有两段非递减子数组，且第一段最小值，大于第二段的最大值

        使用 nextDesc 数组记录 nums[i] 下一个递减的位置 j, j >= i, 且 nums[j] > nums[j+1], 如果不存在 j = n
         */
        int ans = 0, n = nums.length;
        int[] nextDesc = new int[n];
        nextDesc[n - 1] = n;
        int p = n;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                p = i;
            }
            nextDesc[i] = p;
        }
        for (int k = 1; k * k <= n; k++) {
            if (n % k == 0) {
                ans += solve(k, nums, nextDesc);
                int nk = n / k;
                if (nk != k) {
                    ans += solve(nk, nums, nextDesc);
                }
            }
        }
        return ans;
    }

    public int solve(int k, int[] nums, int[] nextDesc) {
        int lastMax = 0;
        for (int r = k - 1; r < nums.length; r += k) {
            int l = r - k + 1;
            int m = nextDesc[l];
            // [l, r] 非递减
            if (m >= r) {
                if (nums[l] < lastMax) {
                    return 0;
                }
                lastMax = nums[r];
            } else {
                /*
                只有两段非递减 nextDesc[m+1] >= r
                第一段最小值，大于第二段的最大值 nums[l] >= nums[r]
                最小值大于上一段最大值 nums[m + 1] > lastMax
                 */
                if (nextDesc[m + 1] < r || nums[l] < nums[r] || nums[m + 1] < lastMax) {
                    return 0;
                }
                // [l, m] 区间最大
                lastMax = nums[m];
            }
        }
        return k;
    }


    public static void main(String[] args) {
        int n = 83160;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                ans += i;
            }
        }
        System.out.println(ans);
    }

    static class Solution_3_1 {
        public int sortableIntegers(int[] nums) {
        /*
        k 是 n 的 因数，且可以通过依次执行以下操作将 nums 排序为 非递减顺序:
            将 nums 划分为长度为 k 的 连续子数组。
            独立地对每个子数组进行循环移动（左移或右移任意次数）。
            3,1,2, 4,6,5
         */
        /*
        先看单个区间
        检查区间 [l, r] (r = l + k - 1)是否满足以下两种情况：
            区间非递减;
            分为两段: [l, m], [m + 1, r] 非递减区间, 且满足以下条件
                [l, m] 区间非递减
                [m + 1, r] 区间非递减
                nums[l] >= nums[r]
        n/k 个区间连起来, 下一个区间的最小值 要大于等于上一个区间的最大值
         */
            int n = nums.length;
            int ans = 0;

            int k = 1;
            while (k * k <= n) {
                if (n % k == 0) {
                    if (check(nums, k)) {
                        ans += k;
                    }
                    // 另一半因数
                    int nk = n / k;
                    if (k != nk && check(nums, nk)) {
                        ans += nk;
                    }
                }
                k++;
            }

            return ans;
        }

        public boolean check(int[] nums, int k) {
            int last = 0;
            for (int i = 0; i < nums.length; i += k) {
                int[] mx = minMax(nums, i, i + k - 1);
                if (mx[0] >= last) {
                    last = mx[1];
                } else {
                    return false;
                }
            }
            return true;
        }

        public int[] minMax(int[] nums, int l, int r) {
            int i = l, last = 0;
            while (i <= r && nums[i] >= last) {
                last = nums[i];
                i++;
            }
            // [l, r] 区间非递减
            if (i > r) {
                return new int[]{nums[l], nums[r]};
            }
            // 是否满足第二种情况
            int m = i - 1;
            last = 0;
            while (i <= r && nums[i] >= last) {
                last = nums[i];
                i++;
            }
            if (i > r && nums[l] >= nums[r]) {
                return new int[]{nums[m + 1], nums[m]};
            }
            return new int[]{-1, -1};
        }
    }


}
