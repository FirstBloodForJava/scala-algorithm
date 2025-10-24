package com.oycm.algorithm.a.basic

object Solution_5 {

  /** 2379.得到 K 个黑块的最少涂色次数 1360
   * https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks/submissions/673258265/
   * 每一个操作中可以将 白色 涂成 黑色
   *
   * @param blocks blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。字符 'W' 和 'B' 分别表示白色和黑色。
   * @param k      整数 k，表示想要连续黑色块的数目
   * @return 至少出现一个 连续 k 个黑色块的 最少 操作数
   */
  def minimumRecolors(blocks: String, k: Int): Int = {
    // 注意：求最小，这里要最大
    var ans = Int.MaxValue
    var temp = 0

    for (i <- blocks.indices) {
      // 入
      if ('W' == blocks(i)) {
        temp += 1
      }

      // 窗口形成
      if (i - k + 1 >= 0) {
        // 更新答案
        ans = Math.min(ans, temp)

        // 出
        if ('W' == blocks(i - k + 1)) {
          temp -= 1
        }
      }

    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(minimumRecolors("WBBWWBBWBW", 7) == 3)
    println(minimumRecolors("WBWBBBW", 2) == 0)
  }

}
