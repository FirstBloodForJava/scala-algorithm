package com.oycm.month2026.march;

import com.oycm.DataCreateUtils;

public class Solution_4 {

    /**
     * 1582. <a href="https://leetcode.cn/problems/special-positions-in-a-binary-matrix/description/">二进制矩阵中的特殊位置</a> 1321
     *
     * @param mat
     * @return
     */
    public int numSpecial(int[][] mat) {
        /*
        mat[i][j] == 1 并且行 i 与列 j 中的所有其他元素都是 0, (i, j) 的数量
        求 每行每列 的和 mat[i][j] == 1 是 rows[i] cols[j] 是否为0
         */
        int ans = 0;
        int n = mat[0].length;
        out:
        for (int[] row : mat) {
            int col = -1;
            for (int i = 0; i < n; i++) {
                if (row[i] == 0) {
                    continue;
                } else {
                    if (col >= 0) {
                        continue out;
                    }
                    col = i;
                }
            }
            if (col < 0) {
                continue;
            }
            // 判断这一列是否只有一个 1
            boolean flag = false;
            for (int[] r : mat) {
                if (r[col] == 0) {
                    continue;
                }
                if (flag) {
                    continue out;
                }
                flag = true;
            }
            ans++;

        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_4().numSpecial(DataCreateUtils.twoDimensionInts("[[0,0,1,0],[0,0,0,0],[0,0,0,0],[0,1,0,0]]")));
    }

}
