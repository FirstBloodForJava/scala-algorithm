package com.oycm.algorithm.abc.max

object Solution_5 {


  /**
   * LCP 68. 美观的花束
   * https://leetcode.cn/problems/1GxJYY/description/
   *
   * 选择一段区间（连续的）的鲜花做成插花，如果每一品种的鲜花数量都不超过 cnt 朵，那么我们认为这束插花是 美观的
   *
   * 求共有多少种可选择的区间，答案对 10 pow 9 + 7 取模
   *
   * @param flowers 长度为 n，flowers(i) 表示该位置鲜花的品种编号
   * @param cnt     [1, 10 pow 5] 每一品种的鲜花数量
   * @return
   */
  def beautifulBouquet(flowers: Array[Int], cnt: Int): Int = {
    /*
    子数组越短越合法-求最长的子数组
    固定 r，随着 r 不断右移，l 也要不断右移动，才能符合要求，求最小的 l，r - l + 1 中的子数组就是符合要求的
     */
    var ans = 0L
    val map = scala.collection.mutable.Map[Int, Int]()
    var l = 0
    for (r <- flowers.indices) {
      map(flowers(r)) = map.getOrElse(flowers(r), 0) + 1
      while (map(flowers(r)) > cnt) {
        map(flowers(l)) -= 1
        l += 1
      }
      ans += r - l + 1
    }

    (ans % (Math.pow(10, 9).toInt + 7)).toInt
  }

  def main(args: Array[String]): Unit = {
    println(beautifulBouquet(Array(1, 2, 3, 2), 1))
    println(beautifulBouquet(Array(5, 3, 3, 3), 2))
  }
}
