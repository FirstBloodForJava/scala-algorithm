package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_9 {

  /**
   * 2260. 必须拿起的最小连续卡牌数 1365
   * https://leetcode.cn/problems/minimum-consecutive-cards-to-pick-up/description/
   *
   * cards[i] 表示第 i 张卡牌的 值。
   * 如果两张卡牌的值相同，则认为这一对卡牌 匹配 。
   *
   * @param cards 整数数组
   * @return 求拿起的最小连续卡牌数，以使在拿起的卡牌中有一对匹配的卡牌。否则返回 -1
   */
  def minimumCardPickup(cards: Array[Int]): Int = {
    /*
    遍历过程中，用数组维护 值的下标，如果前面存在该值的卡牌，计算答案，更新下标，因为越和后面的成对值越大
    */
    var ans = Int.MaxValue
    val map = scala.collection.mutable.Map[Int, Int]()
    for (i <- cards.indices if ans > 2) {
      val card = cards(i)
      if (map.contains(card)) {
        ans = Math.min(ans, i - map(card) + 1)
      }
      map(card) = i
    }
    if (ans == Int.MaxValue) {
      ans = -1
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(minimumCardPickup(Array(3, 4, 2, 3, 4, 7)))
    println(minimumCardPickup(Array(1, 0, 5, 3)))
  }
}
