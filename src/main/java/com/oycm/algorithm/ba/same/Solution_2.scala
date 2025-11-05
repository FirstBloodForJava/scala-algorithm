package com.oycm.algorithm.ba.same

object Solution_2 {

  /**
   * 3649. 完美对的数目 1716
   * https://leetcode.cn/problems/number-of-perfect-pairs/
   * 一对下标 (i, j) 满足以下条件，则称其为 完美
   *  - i < j
   *  - 令 a = nums[i], b = nums[j]
   *  - min(|a - b|, |a + b|) <= min(|a|, |b|)
   *  - max(|a - b|, |a + b|) >= max(|a|, |b|)
   *
   * @param nums
   * @return
   */
  def perfectPairs(nums: Array[Int]): Long = {
    /*
    依题意，可得以下信息：
     1. min(|a - b|, |a + b|) 和 min(|b - a|, |b + 1|) 等价，可以对数组进行排序后计算
     2. 第二个等式恒成立
    题解：可以把 a, b, -b 当成一维数轴的 A, B, B' 三点，B' 表示 B 在数轴上相对原点 O 的对称点。下面线段表示的值都是正值
    min(|a - b|, |a + b|) => min(AB, AB')
      如果 A 和 B 在同一侧，min(AB, AB') = AB = |OA - OB|
      如果 A 和 B 在两侧，min(AB, AB') = AB' = |OA - OB'| = |OA - OB|
      所以 min(AB, AB') == |OA - OB|
    min(|a|, |b|) => min(OA, OB)
    |OA - OB| <= min(OA, OB)
    当 OA <= OB => OB - OA <= OA => OB <= 2OA
    当 OA >= OB => OA - OB <= OB => OA <= 2OB
    较大值的绝对值要小于等于较小值的 2 被
    如果 OA = |a|, OB = |b|，如果 |a| <= |b|, 则 |b| <= 2|a|
    找出 |a| <= |b| <= 2|a|的方案数
    对数组按绝对值进行升序排序
    */
    var ans = 0L
    for (i <- nums.indices) {
      if (nums(i) < 0) {
        nums(i) = -nums(i)
      }
    }
    val sort = nums.sorted
    var l = 0
    for (r <- sort.indices) {
      // 都是正数 b <= 2a, a <= b, a 要离 b 越近 2a 才会越大，当 2a < b 不符合要求时 l 右移
      while (2 * sort(l) < sort(r)) {
        l += 1
      }
      ans += r - l
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(perfectPairs(Array(0, 1, 2, 3)))
    println(perfectPairs(Array(-3, 2, -1, 4)))
    println(perfectPairs(Array(1, 10, 100, 1000)))
  }

}
