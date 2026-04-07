package com.oycm.month2026.april;

public class Solution_7 {

    /**
     * 2069. <a href="https://leetcode.cn/problems/walking-robot-simulation-ii/description/">模拟行走机器人 II</a> 1919
     */
    class Robot {

        /*
        机器人只能在最外圈移动, 移动 mod = 2 * (w + h - 2) 回到原点, 注意：此时方向朝南
        设当前移动总步数模 mod 的结果为 s，分类讨论：
            s < w; 往东走了 s 步, 方向 East, 坐标 (s, 0)
            w <= s < w + h - 1;
                先往东走了 w-1, 再往北走 s-(w-1),
                方向 North, 坐标 (w-1, s-w+1)
            w + h - 1 <= s < 2w + h - 2;
                先往东走了 w-1, 再往北走 h-1, 玩西走 s - (w-1) - (h-1) 步, x 坐标 w-1 - (s - (w-1) - (h-1)) = 2w + h - s - 3
                方向 West, (2w + h - s - 3, h-1);
            否则, 先往东走了 w-1, 再往北走 h-1, 再往西走 w-1, 再玩南走 s - (w-1) - (h-1) - (w-1) 步, y 坐标 h-1 - (s - (w-1) - (h-1) - (w-1))
                h-1 - s + (w-1) + (h-1) + (w-1) = 2h + 2w - s - 4
                方向 South, (0, 2h + 2w - s - 4)
            注意：当 s = 0 时，此时应该时朝南，上面的讨论结果时朝东
            原先的取模范围是 [0, 2 * (w + h - 2) - 1], 调整到 [1, 2 * (w + h - 2)]
            取模之前先减少 1, 然后再加 1
         */
        int w;
        int h;
        int s;

        public Robot(int width, int height) {
            w = width;
            h = height;
        }

        public void step(int num) {
            s = (s + num - 1) % (2 * (w + h - 2)) + 1;
        }

        public int[] getPos() {
            Object[] state = getState();
            return new int[] {(int) state[0], (int) state[1]};
        }

        public String getDir() {
            return (String) getState()[2];
        }

        public Object[] getState() {
            if (s < w) {
                return new Object[]{s, 0, "East"};
            } else if (s < w + h - 1) {
                return new Object[]{w - 1, s - w + 1, "North"};
            } else if (s < 2 * w + h - 2) {
                return new Object[]{2 * w + h - s - 3, h - 1, "West"};
            } else {
                return new Object[]{0, 2 * (h + w - 2) - s, "South"};
            }
        }
    }

}

class Robot_1 {
    public static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static final String[] dirStr = {"East", "North", "West", "South"};
    int k;
    int x;
    int y;
    int width;
    int height;
    int mod;

    /**
     * 网格图, 左下角格子 (0, 0), 右上角格子 (width-1, height-1)
     *
     * @param width
     * @param height
     */
    public Robot_1(int width, int height) {
        this.width = width - 1;
        this.height = height - 1;
        this.mod = 2 * (width + height);
    }

    /**
     * 如果机器人下一步将到达的格子 超出了边界 ，机器人会 逆时针 转 90 度，然后再尝试往前一步
     *
     * @param num 机器人移动指定的 步数
     */
    public void step(int num) {
        while (num > 0) {
            int nx = x + dirs[k][0];
            int ny = y + dirs[k][1];
            if (nx > width || nx < 0 || ny > height || ny < 0) {
                // k = (k + 1) % 4;
                // 执行这么多步数回到远点, 当前转变方向的点
                if (num >= mod) {
                    num = num % (2 * (width + height));
                } else {
                    k = (k + 1) % 4;
                }
                continue;
            }
            x = nx;
            y = ny;
            num--;

        }
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        return dirStr[k];
    }
}
