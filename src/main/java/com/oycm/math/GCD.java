package com.oycm.math;

public class GCD {


    public int gcd(int a, int b) {
        /*
        辗转相除法
        gcd(a, b) == gcd(b, a % b)，a > b 且 r = a mod b，r 不为 0。
         */
        /*
        证明一：
        a 可以表示为 a = kb + r（a，b，k，r 都是正整数，且 b > 0）。
        一：假设 d 是 a 和 b 的一个公约数，记 a = ud, b = vd，
        则 r = a - kb = ud - kvd = (u - kv)d，显然 d 是 r 的因子，那么 d 是 b 和 r 的一个公约数；
        二：同理，设 e 是 b 和 r 的一个公约数，记 b = xe, r = ye，
        则 a = kb + r = kxe + ye = (kx + y) e，显然 e 是 a 的因子，那么 e 是 a 和 b 的一个公约数；

        设 d = gcd(a, b), e = gcd(b, r)，d，e 分别是 a 和 b，b 和 r 的最大公约数
            根据一：d 是 b 和 r 的一个公约数，则 d 是 e 的因子，e = pd；
            根据二：e 是 a 和 b 的一个公约数，则 e 是 d 的因子，d = qe；
            结合两点 e = pqe 或 d = qpd，得 pq = 1，pq 均是正数，所以 p = q = 1，进而 d = e
         */
        /*
        证明二：
        假设 c = gcd(a, b)，则存在 m, n 使得 a = mc, b = nc；
        令 r = a % b，则存在 k，使得 r = a - kb = mc - knc = (m - kn)c
        则 gcd(b, a % b) = gcd(b, r) = gcd(nc, (m - kn)c) = gcd(n, m-kn)c（后续需要证明 gcd(n, m-kv) = 1）；
        则 c 是 b 与 a % b 的公约数；
        假设 d = gcd(n, m-kn)，则存在 x, y 使得 n = xd, m-kn = yd，则 m = yd + kn = (y+kx)d
        a = mc = (y+kx)dc，b = xdc；可得 gcd(a, b) = gcd((y+kx)dc, xdc) = dc（由于 d 最大公约数，所以 gcd(x ,y) = 1，所以 gcd(y + kx, x) = 1）
        由于 c = gcd(a, b) = dc，则 d = 1，所以 gcd(b, a % b) = gcd(n, m-kn)c = c
         */
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int reduceGcd(int a, int b) {
        // 仅 减法
        // 处理其中一个为 0 的情况
        if (a == 0) return b;
        if (b == 0) return a;

        // 更相减损：当两数不等时，用大数减小数
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public int reduceGcdOptimize(int a, int b) {
        // 仅 减法
        // 处理其中一个为 0 的情况
        if (a == 0) return b;
        if (b == 0) return a;

        int shift = 0;
        // 提取公因子 2
        while (((a & 1) == 0) && ((b & 1) == 0)) {
            a >>= 1;
            b >>= 1;
            shift++;
        }

        /*
        下面的循环，也可换成 大数-小数的循环
         */

        // 确保 a 为奇数（若 b 为偶数则交换）
        while ((a & 1) == 0) {
            a >>= 1;
        }

        while (b != 0) {
            // b 变成奇数
            while ((b & 1) == 0) {
                b >>= 1;
            }
            // 确保 a 较大
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            // 奇数 - 奇数 => 偶数
            b = b - a;
        }
        // 还原被提取的 2 的因子
        return a << shift;
    }



}
