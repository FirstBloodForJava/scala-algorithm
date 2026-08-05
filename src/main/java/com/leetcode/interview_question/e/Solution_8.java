package com.leetcode.interview_question.e;

public class Solution_8 {

    /**
     * 面试题 05.08. <a href="https://leetcode.cn/problems/draw-line-lcci/description/">绘制直线</a>
     *
     * @param length
     * @param w
     * @param x1
     * @param x2
     * @param y
     * @return
     */
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        /*
        已知一个由像素点组成的单色屏幕，每行均有 w 个像素点，所有像素点初始为 0，左上角位置为 (0,0)。
        现将每行的像素点按照「每 32 个像素点」为一组存放在一个 int 中，再依次存入长度为 length 的一维数组中。
        我们将在屏幕上绘制一条从点 (x1,y) 到点 (x2,y) 的直线（即像素点修改为 1），请返回绘制过后的数组。
         */
        /*
        [0, 31], [32, 63], [64, 95], ... [32n, 32(n+1)-1] 初始都为 0
        x1, x2 在 上面的范围中 [x1, x2] 值要改为 1
        一个 int 表示 32 个像素点中有多少个连续 1
        由于 y >= 0，题目的意思是：
            如果 y > 0，那么 第一层 都是 0，意味着 [0, y * w/32] 都填 0
            如果 length > (y + 1) * w/32, 那么意味着 [(y + 1) * w/32, length) 都填 0。
        对于 [x1, x2] 在 [0, w)：
            x1 落在哪个数字上？x1/32 下取整，记为 low，那么第 low 个数字，二进制高位 [0, x1%32) 都是 0，相当于丢弃 x1%32 个高位 1，右边的 1 怎么填，后续再考虑；
            x2 同理， x2/32 下取整，记为 high，那么第 high 个数字，(x2%32, 31] 都是 0，高位要填 x2%32 + 1 个 1
            当 x1/32 < x2/32 时，low 右边都是 1。高位填的 1，可以使用  带符号右移，高位补 1
            当 x1/32 = x2/32 时，(-1 >>> (x1%32)) & (Integer.MIN_VALUE >> (x2%32))
         */
        int[] ans = new int[length];
        int start = w / 32 * y;
        int low = start + x1 / 32;
        int high = start + x2 / 32;
        for (int i = low; i <= high; i++) {
            ans[i] = -1;
        }
        // [0, x1 % 32) 都填 0，由于初始填 -1，需要无符号右移，高位 补 0
        ans[low] = ans[low] >>> (x1 % 32);
        // low = high，low < high 都解决，这里要保留高位 1，要带符号右移，补齐高位 1
        ans[high] = ans[high] & (Integer.MIN_VALUE >> (x2 % 32));

        return ans;
    }

}
