package com.oycm.month2026.september;

public class Solution_13 {

    /**
     * 835. <a href="https://leetcode.cn/problems/image-overlap/description/">图像重叠</a> 1970
     *
     * @param img1
     * @param img2
     * @return
     */
    public int largestOverlap(int[][] img1, int[][] img2) {
        /*
        给你两个图像 img1 和 img2 ，两个图像的大小都是 n x n ，用大小相同的二进制正方形矩阵表示。二进制矩阵仅由若干 0 和若干 1 组成。
        转换 其中一个图像，将所有的 1 向左，右，上，或下滑动任何数量的单位；然后把它放在另一个图像的上面。
        该转换的 重叠 是指两个图像 都 具有 1 的位置的数目。
        请注意，转换 不包括 向任何方向旋转。越过矩阵边界的 1 都将被清除。
        最大可能的重叠数量是多少？
         */
        int n = img1.length;
        int ans = 0;
        for (int dx = 1 - n; dx < n; dx++) {
            for (int dy = 1 - n; dy < n; dy++) {
                // 移动方向 x [1-n, n-1] * y [1-n ,n-1]
                int cnt = 0;
                for (int i = Math.max(-dx, 0); i < Math.min(n - dx, n); i++) {
                    for (int j = Math.max(-dy, 0); j < Math.min(n - dy, n); j++) {
                        cnt += img1[i][j] * img2[i + dx][j + dy];
                    }
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

}
