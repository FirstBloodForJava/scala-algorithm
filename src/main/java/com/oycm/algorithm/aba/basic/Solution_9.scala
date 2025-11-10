package com.oycm.algorithm.aba.basic

object Solution_9 {

  /**
   * 2024. 考试的最大困扰度 1643
   * https://leetcode.cn/problems/maximize-the-confusion-of-an-exam/description/
   *
   * 老师想增加学生对自己做出答案的不确定性，就是最大化 有 连续相同结果的题数。
   *
   * 一个整数 k，能进行以下操作的最多次数：
   *  - 每次操作中，将问题的正确答案改为 'T' 或者 'F'
   *
   * @param answerKey n 道判断题的答案，T/F 表示判断题的答案
   * @param k         修改次数 [1, n]
   * @return 返回在不超过 k 次操作的情况下，最大化连续 T/F 的数目
   */
  def maxConsecutiveAnswers(answerKey: String, k: Int): Int = {
    /*
    TTTTFTFTF 字符串中 F 出现次数小于等于 k 的最长连续子串
    FFFFTFTFT 字符串中 T 出现次数小于等于 k 的最长连续子串

    时间复杂度 O(n)
     */
    def maxTOrFSub(answerKey: String, k: Int, c: Char): Int = {
      var ans = 0
      var l = 0
      var cnt = 0
      for (r <- answerKey.indices) {
        if (answerKey(r) == c) {
          cnt += 1
        }
        // 因为 k > 1, l 永远不会 大于 r
        while (cnt > k) {
          if (answerKey(l) == c) {
            cnt -= 1
          }
          l += 1
        }
        ans = Math.max(ans, r - l + 1)
      }
      ans
    }

    Math.max(maxTOrFSub(answerKey, k, 'T'), maxTOrFSub(answerKey, k, 'F'))
  }

  def optimize(answerKey: String, k: Int): Int = {
    /*
    TTTTFTFTF 字符串中 F 出现次数小于等于 k 的最长连续子串
    FFFFTFTFT 字符串中 T 出现次数小于等于 k 的最长连续子串
    题解转换：字符串中 T/F 出现次数小于等于 k 的最长连续子串
    T = 0101 0100
    F = 0100 0110
    'T' >> 1 & 1 = 0, 'F' >> 1 & 1 = 1
     */
    var ans = 0
    val cnt = Array.fill(2)(0)
    var l = 0
    for (r <- answerKey.indices) {
      cnt(answerKey(r) >> 1 & 1) += 1
      while (cnt(0) > k && cnt(1) > k) {
        cnt(answerKey(l) >> 1 & 1) -= 1
        l += 1
      }
      ans = Math.max(ans, r - l + 1)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxConsecutiveAnswers("TTFF", 2) == 4)
    println(maxConsecutiveAnswers("TFFT", 1) == 3)
    println(maxConsecutiveAnswers("TTFTTFTT", 1) == 5)

    println(optimize("TTFF", 2) == 4)
    println(optimize("TFFT", 1) == 3)
    println(optimize("TTFTTFTT", 1) == 5)
  }
}
