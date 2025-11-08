package com.oycm.algorithm.ba.modify

object Solution_5 {

  /**
   * 3684. 至多 K 个不同元素的最大和
   * https://leetcode.cn/problems/maximize-sum-of-at-most-k-distinct-elements/description/
   *
   * 择最多 k 个元素，使它们的和最大化。但是，所选的数字必须 互不相同 。
   *
   * @param nums 正整数 数组
   * @param k    整数
   * @return 返回一个包含所选数字的数组，数组中的元素按 严格递减 顺序排序。
   */
  def maxKDistinct(nums: Array[Int], k: Int): Array[Int] = {
    /*
    倒序排序 + 注意数量不够情况
    时间复杂度 O(n log g) 在排序
    */
    val n = nums.length
    val sort = nums.sortWith(_ > _)
    var m = 1
    for (i <- 1 until n if m < k) {
      if (sort(m - 1) != sort(i)) {
        sort(m) = sort(i)
        m += 1
      }
    }
    sort.slice(0, m)
  }

  def main(args: Array[String]): Unit = {
    println(maxKDistinct(Array(84, 93, 100, 77, 90), 3).toSeq)
    println(maxKDistinct(Array(84, 93, 100, 77, 93), 3).toSeq)
    println(maxKDistinct(Array(1, 1, 1, 2, 2, 2), 6).toSeq)
  }
}
