package com.oycm.dualweek2023.No98;

public class Solution_4 {

    /**
     * 2543. <a href="https://leetcode.cn/problems/check-if-point-is-reachable/description/">判断一个点是否可以到达</a> 2221
     *
     * @param targetX [1, 1e9]
     * @param targetY [1, 1e9]
     * @return
     */
    public boolean isReachable(int targetX, int targetY) {
        /*
        给你一个无穷大的网格图。一开始你在 (1, 1) ，你需要通过有限步移动到达点 (targetX, targetY)。
        每一步 ，你可以从点 (x, y) 移动到以下点之一：
            (x, y - x)
            (x - y, y)
            (2 * x, y)
            (x, 2 * y)
        给你两个整数 targetX 和 targetY ，分别表示你最后需要到达点的 X 和 Y 坐标。如果你可以从 (1, 1) 出发到达这个点，请你返回true ，否则返回 false 。
         */
        /*
        gcd(x, y - x) = gcd(x, y), 起始点的 gcd = 1，在只进行第一二部操作的情况下，gcd 始终保持为 1，
        如果只进行第三步操作，x 变成 2^k 幂，gcd 还是为 1，
        如果第三步，第四步同时操作，则 gcd 变成相同操作次数 k 的 2^k，所以 gcd(targetX, targetY) 为 2^k，则能移动到终点
         */
        /*
        构造移动国产
        如果反过来移动，则可以看成点 (x, y) 移动到以下点之一：
            (x, y + x)
            (x + y, y)
            (x / 2, y) x 是偶数
            (x, y / 2) y 是偶数
        知道 x==y，且 x 为奇数，停止移动（3 + 3都是移动到自己），按以下规则移动
            任意一个是偶数，则按第三或第4步移动
            如果 x 和 y 都是奇数，x < y，要想移动越靠近 (1, 1)，要让 x, y 变小，此时移动 x，x+y/2 大于 x，x < y => 2x < x + y < 2y => x < (x+y)/2 < y
                x < y, (x, (x+y)/2)
                x > y, ((x+y)/2, y)
         */
        int n = gcd(targetX, targetY);
        return (n & (n - 1)) == 0;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
