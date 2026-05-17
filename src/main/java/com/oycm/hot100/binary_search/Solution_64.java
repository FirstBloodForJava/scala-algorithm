package com.oycm.hot100.binary_search;

public class Solution_64 {

    /**
     * 74. <a href="https://leetcode.cn/problems/search-a-2d-matrix/description/">搜索二维矩阵</a>
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        /*
        给你一个满足下述两条属性的 m x n 整数矩阵：
            每行中的整数从左到右按非严格递增顺序排列(nums[i][j] <= nums[i][j+1])。
            每行的第一个整数大于前一行的最后一个整数。
         */
        /*
        矩阵二分查找，m log(n) 写法，每一行进行一个二分查找
         */
        int m = matrix.length, n = matrix[0].length;
        for (int[] row : matrix) {
            if (target < row[0] || target >row[n - 1]) continue;
            int l = -1, r = n;
            while (l + 1 < r) {
                int mid = (l + r) >>> 1;
                if (row[mid] == target) {
                    return true;
                } else if (row[mid] > target) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
        }

        return false;
    }

}
