package com.oycm.algorithm.ba.opposite

object Solution_14 {

  /**
   * LCP 28. 采购方案
   * https://leetcode.cn/problems/4xy4Wx/description/
   *
   * 小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。
   *
   * @param nums   n 个零件的报价, [1, 10 pow 5]
   * @param target 小力预算
   * @return
   */
  def purchasePlans(nums: Array[Int], target: Int): Int = {
    /*
    对数组进行升序排序，在数组中找 nums(i) + nums(j) <= target 的组合数
    i < j, 如果 nums(i) + nums(j) <= target, j - i 中的组合都符合答案
    l = 0, r = n - 1; sum > target, 则 r--, 当 sum <= target 时, 记录答案 r-l, l++
    l 和 r 不断靠近
    时间复杂度在排序 n log n
     */
    val sort = nums.sorted
    var ans = 0L
    var l = 0
    var r = nums.length - 1
    while (l < r && sort(l) < target) {
      while (l < r && sort(l) + sort(r) > target) {
        r -= 1
      }
      ans += r - l

      l += 1
    }

    (ans % 1000000007).toInt
  }

  def main(args: Array[String]): Unit = {
    println(purchasePlans(Array(2, 5, 3, 5), 6))
    println(purchasePlans(Array(2, 2, 1, 9), 3))
  }

}
