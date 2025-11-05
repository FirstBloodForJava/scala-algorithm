package com.oycm.algorithm.ba.opposite

object Solution_13 {

  /**
   * 2563. 统计公平数对的数目
   * https://leetcode.cn/problems/count-the-number-of-fair-pairs/
   *
   * 公平数对：((i, j) 满足以下全部条件：
   *  - 0 <= i < j < n
   *  - lower <= nums[i] + nums[j] <= upper
   *
   * @param nums 下标从 0 开始、长度为 n 的整数数组
   * @param lower
   * @param upper
   * @return
   */
  def countFairPairs(nums: Array[Int], lower: Int, upper: Int): Long = {
    /*
    对数组排序不影响 i 和 j 的关系
    对数组 nums 进行排序后，外层循环从 l = 0 开始, r = l + 1
    如果 nums(l) + nums(r) > upper nums(r) 和任意数相加都 大于 upper，退出内层循环
    如果 nums(l) + nums(r) < lower r ++
    如果符合要求，记录答案 ++
    时间复杂度：O(n ^ 2)
     */
    val n = nums.length
    var ans = 0L
    val sort = nums.sorted

    for (l <- 0 until n - 1) {
      var r = l + 1
      while (r < n && sort(l) + sort(r) <= upper) {
        if (sort(l) + sort(r) >= lower) {
          ans += 1
        }
        r += 1
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(countFairPairs(Array(0, 1, 7, 4, 4, 5), 3, 6))
    println(countFairPairs(Array(1, 7, 9, 2, 5), 11, 11))
  }

}
