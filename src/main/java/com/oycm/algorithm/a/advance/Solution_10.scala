package com.oycm.algorithm.a.advance

object Solution_10 {

  /**
   * 1888.使二进制字符串字符交替的最少反转次数 2006
   * https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/
   *
   * s 是二进制字符串，可以对字符串中的字符进行以下操作：
   *  - 类型1：删除字符串 s 的第一个字符并将它添加到字符串结尾
   *  - 类型2；选择字符串 s 中的任意一个字符并将其反转，0 => 1，1 => 0
   *
   * 求是字符串 s 变成交替的（相邻字符不相同），类型2 执行的最少次数
   *
   * @param s
   * @return
   */
  def minFlips(s: String): Int = {

    /*
    题解：依据题意，不断对 111000 进行 类型1 操作，有以下结果
    111000
    110001
    100011
    000111
    001110
    011100
    111000(回到原来的字符串)
    注意：以上字符串都是 111000111000（2s 去掉最后一个）的字串
    字符串要是交替的，有以下两种格式
    1010 交替字符串，则满足 i % 2 != s(i)
    0101 交替字符串，则满足 i % 2 = s(i)
    设 t = 1110 0011 100，字符串长度为 n 的字串转换成交替字符串有什么规则呢？
    对于 t 字符串，如果 i % 2 != s(i) 记为. i % 2 = s(i) 记为x，则
    .x.. x..x ..x
    对于整个字符串 t，转换成交替字符串
      转换成 1010 格式字符串，需要转换的数量,就是 x 的数量，记为 cnt
      转换成 0101 格式字符串，需要转换的数量,就是 . 的数量，记为 2n-1-cnt
    如果是长为 n 的字串，转换成交替字符串，求转换的最小值
      就是 min(ans, min(cnt, n - cnt))

    这里是否还需要分 开始下标的奇数讨论情况吗？
    */
    val n = s.length
    var ans = n
    var cnt = 0
    for (r <- 0 until 2 * n - 1) {
      // 对 '1' = 49 % 2 就是 1
      if (r % 2 != s(r % n) % 2) {
        cnt += 1
      }
      val left = r - n + 1
      if (left >= 0) {
        ans = Math.min(ans, Math.min(cnt, n - cnt))
        if (left % 2 != s(left % n) % 2) {
          cnt -= 1
        }
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(minFlips("111000"))
    println(minFlips("010"))
    println(minFlips("1110"))
  }
}
