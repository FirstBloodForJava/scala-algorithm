package com.oycm.algorithm.a.advance

object Solution_7 {

  /**
   * 1652.拆炸弹
   * https://leetcode.cn/problems/defuse-the-bomb/description/
   * 将 第 i 个数字按一下规则替换，得到一个新的结果：
   *  - k > 0，第 i 个数字值为当前之后 k 个数之和
   *  - k < 0，第 i 个数字值为当前之前 k 个数之和
   *  - k = 0，第 i 个数用 0 替换
   *
   * @param code 长度为 n 的环形数组
   * @param k
   * @return
   */
  def decrypt(code: Array[Int], k: Int): Array[Int] = {
    val n = code.length
    val ans = Array.fill(n)(0)
    var temp = 0

    if (k > 0) {
      for (i <- 1 until n + k) {
        // 入
        temp += code(i % n)
        // 窗口形成
        if (i >= k) {
          // 更多答案
          ans(i - k) = temp
          // 出 滑动
          // 怎么优化这里的 i-k+1 取模运算，只有一次会用到
          temp -= code((i - k + 1) % n)
        }
      }
    }

    if (k < 0) {
      for (i <- 0 until n - k - 1) {
        temp += code(i % n)
        if (i + k + 1 >= 0) {
          ans((i + 1) % n) = temp
          // 怎么优化这里的 i-k+1 取模运算，只有一次会用到
          temp -= code((i + k + 1) % n)
        }
      }
    }
    ans
  }

  /**
   * 计算优化：去掉取模，将原数组拼接
   * ans(0) = [l, r] sum; ans(1) = [l+1, r+1] sum
   *
   * @param code
   * @param k
   * @return
   */
  def decrypt_1(code: Array[Int], k: Int): Array[Int] = {
    val n = code.length
    val ans = Array.fill(n)(0)
    if (k == 0) {
      return ans
    }
    val newCode = Array.fill(2 * n)(0)
    Array.copy(code, 0, newCode, 0, n)
    Array.copy(code, 0, newCode, n, n)

    var l = if (k > 0) {
      1
    } else {
      n + k
    }
    var r = if (k > 0) {
      k
    } else {
      n - 1
    }
    var temp = 0
    for (i <- l to r) {
      temp += newCode(i)
    }
    for (i <- 0 until n) {
      ans(i) = temp
      temp -= newCode(l)
      temp += newCode(r + 1)
      l += 1
      r += 1
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(decrypt(Array(5, 7, 1, 4), 3).toSeq == Array(12, 10, 16, 13).toSeq)
    println(decrypt(Array(1, 2, 3, 4), 0).toSeq == Array(0, 0, 0, 0).toSeq)
    println(decrypt(Array(2, 4, 9, 3), -2).toSeq == Array(12, 5, 6, 13).toSeq)

    println(decrypt_1(Array(5, 7, 1, 4), 3).toSeq == Array(12, 10, 16, 13).toSeq)
    println(decrypt_1(Array(1, 2, 3, 4), 0).toSeq == Array(0, 0, 0, 0).toSeq)
    println(decrypt_1(Array(2, 4, 9, 3), -2).toSeq == Array(12, 5, 6, 13).toSeq)
  }
}
