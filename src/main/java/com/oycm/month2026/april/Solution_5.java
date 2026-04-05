package com.oycm.month2026.april;

public class Solution_5 {

    /**
     * 657. <a href="https://leetcode.cn/problems/robot-return-to-origin/">机器人能否返回原点</a>
     *
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {
        /*
        up 记为向上的次数, 为 D 时 减少
        right 记为向右的次数, 为 L 时 减少
        up right 都为 0, 即可回到原点
         */
        int up = 0, right = 0;

        for (char c : moves.toCharArray()) {
            if (c == 'U') {
                up++;
            } else if (c == 'D') {
                up--;
            } else if (c == 'R') {
                right++;
            } else {
                right--;
            }
        }
        return up == 0 && right == 0;
    }

}
