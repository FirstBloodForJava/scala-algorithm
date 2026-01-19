package com.oycm.algorithm.f.prefix_sum_hash

object Solution_12 {

  /**
   * 1124. 表现良好的最长时间段 1908
   * https://leetcode.cn/problems/longest-well-performing-interval/description/
   *
   * hours(i) > 8 时，这一天是劳累的，否则是不劳累的
   *
   * 表现良好时间段：劳累的天数 严格大于 不劳累的天数
   *
   * @param hours 工作时间表数组
   * @return
   */
  def longestWPI(hours: Array[Int]): Int = {
    /*
    是不是可以把 hours(i) > 8 看作 1，hours(i) <= 8 的看作 -1
    sums(r) - sums(l) > 0 的最大长度
    怎么找最大长度？
    如果 sums(r) 大于 0，则 ans = r + 1
    sums(r) <= 0 时，只需要找到 sums(r) - 1 = sums(l) 第一次出现的 index，max(r - index + 1, ans), 不存在，说明没有符合要求的时间段
    如果 sums(r) == 0, [0, r-1] 之间肯定有 nums(i) == 1
    如果 nums(r) < 0, [0, r-1] 之间可能有 nums(i) == 1
     */
    var ans = 0
    var sum = 0
    val map = scala.collection.mutable.Map[Int, Int]()
    for (i <- hours.indices) {
      val num = if (hours(i) > 8) 1 else -1
      sum += num
      if (sum > 0) {
        ans = i + 1
      } else {
        val l = map.getOrElse(sum - 1, i + 1)
        ans = Math.max(ans, i - l)
      }
      map(sum) = map.getOrElse(sum, i)

    }

    ans
  }

  def optimize(hours: Array[Int]): Int = {
    /*
    对 sums 取反，
     */
    val n = hours.length
    var ans = 0
    var sum = 0
    // 数组记录前缀和首次出现的位置
    val map = Array.fill(n + 2)(n)
    for (i <- hours.indices) {
      val num = if (hours(i) > 8) -1 else 1
      sum += num
      if (sum < 0) {
        ans = i + 1
      } else {
        if (map(sum + 1) < n) {
          ans = Math.max(ans, i - map(sum + 1))
        }
        if (map(sum) == n) {
          map(sum) = i
        }
      }
    }

    ans
  }


  def main(args: Array[String]): Unit = {
    println(longestWPI(Array(9, 9, 6, 0, 6, 6, 9)))
    println(longestWPI(Array(6, 6, 6)))
    println(longestWPI(Array(6, 9, 6)))

    println(optimize(Array(9, 9, 6, 0, 6, 6, 9)))
    println(optimize(Array(6, 6, 6)))
    println(optimize(Array(6, 9, 6)))
  }

}
