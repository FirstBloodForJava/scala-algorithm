package com.oycm.algorithm.e.ea.basic

object Solution_5 {

  /**
   * 1128. 等价多米诺骨牌对的数量 1333
   * https://leetcode.cn/problems/number-of-equivalent-domino-pairs/description/
   *
   * dominoes[i] = [a, b] 与 dominoes[j] = [c, d] 等价 条件：
   *  - a == c 且 b == d 或 a == d 且 b == c
   *
   * @param dominoes 多米诺骨牌
   * @return 找出等价的 数量
   */
  def numEquivDominoPairs(dominoes: Array[Array[Int]]): Int = {
    /*
    一次记录，两次查找
    重新 hash key 的判断
    时间复杂度 O(n)
    空间复杂度 O(n)

    由于 1 <= dominoes[i][j] <= 9, 也可以使用 cnt[][] = 10x10 的二维数组记录相同 key 的次数

    初始化数组默认值为 -1, cnt[min][max]++  ans += cnt[min][max], 这样第一次不会计算
    初始化数组默认值为 -1, ans += cnt[min][max], cnt[min][max]++, 先更新答案再计数
     */
    case class UnorderedPair(a: Int, b: Int) {
      private val (min, max) = if (a <= b) (a, b) else (b, a)

      override def equals(obj: Any): Boolean = obj match {
        case UnorderedPair(otherA, otherB) =>
          val (otherMin, otherMax) = if (otherA <= otherB) (otherA, otherB) else (otherB, otherA)
          min == otherMin && max == otherMax
        case _ => false
      }

      override def hashCode(): Int = (min, max).hashCode()

      def toArray: Array[Int] = Array(min, max)

      override def toString: String = s"UnorderedPair($min, $max)"
    }
    var ans = 0
    val map = scala.collection.mutable.Map[UnorderedPair, Int]()
    for (pair <- dominoes) {
      val key = UnorderedPair(pair(0), pair(1))
      map(key) = map.getOrElse(key, -1) + 1
      ans += map(key)

    }
    ans
  }


  def main(args: Array[String]): Unit = {
    println(numEquivDominoPairs(Array(Array(1, 2), Array(2, 1), Array(3, 4), Array(5, 6))))
    println(numEquivDominoPairs(Array(Array(1, 2), Array(2, 1), Array(1, 1), Array(2, 2), Array(2, 1))))


  }
}
