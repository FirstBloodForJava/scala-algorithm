package com.oycm.algorithm.a.basic

object Solution_8 {

  /**
   *
   * 1423.可获得的最大点数 1574
   * https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/description/
   * 每次只能从 头或尾 拿一张卡牌，求拿出恰好 k 张牌的点数和最大值
   * @param cardPoints 整数数组，表示排成一行卡牌的点数
   * @param k 整数，恰好 k 张牌
   * @return
   */
  def maxScore(cardPoints: Array[Int], k: Int): Int = {
    var ans = 0
    var temp = 0
    var left = 0
    var right = cardPoints.length
    var flag = false

    // 可拆分两个 for k 循环，写法更简单
    // 从左边先去 k 张牌求和，去掉头最右边的牌再依次取尾部的牌
    while (left < right && left >= 0) {

      if (!flag) {
        // 左边入
        temp += cardPoints(left)
      } else {
        // 右边入，左边出
        temp += cardPoints(right)
        temp -= cardPoints(left)
        left -= 1
      }

      if (!flag && left == k - 1) {
        flag = true
      }

      if (flag) {
        // 更新
        ans = Math.max(temp, ans)
        right -= 1
      } else {
        left += 1
      }
    }
    ans
  }

  /**
   * 逆向思维，求前后 k 长度的最大值，就是求 n-k 连续子数组的最小值
   * @param cardPoints
   * @param k
   * @return
   */
  def reverseMaxScore(cardPoints: Array[Int], k: Int): Int = {
    val m = cardPoints.length - k
    var s = 0
    for (i <- 0 until m) {
      s += cardPoints(i)
    }
    var total = s
    var min = s
    for (i <- m until cardPoints.length) {
      total += cardPoints(i)
      s += cardPoints(i) - cardPoints(i - m)
      min = Math.min(s, min)
    }

    total - min
  }



  def main(args: Array[String]): Unit = {
    println(maxScore(Array(1,2,3,4,5,6,1), 3) == 12)
    println(maxScore(Array(2,2,2), 2) == 4)
    println(maxScore(Array(9,7,7,9,8,7,9), 4) == 33)
    println(maxScore(Array(1,1000,1), 2) == 1001)
    println(maxScore(Array(1,79,80,1,1,1,200,1), 3) == 202)
  }
}
