package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_19 {

  /**
   * 1814. 统计一个数组中好对子的数目 1738
   * https://leetcode.cn/problems/count-nice-pairs-in-an-array/description/
   *
   * rev(x) => rev(123) = 321, rev(120) = 21, 表示将 x 数位反转后得到的结果
   * 好对子定义：
   *  - 0 <= i < j < nums.length
   *  - nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
   *
   * @param nums 非负整数
   * @return
   */
  def countNicePairs(nums: Array[Int]): Int = {
    /*
    问题等价 nums(i) - rev(nums(i)) = nums(j) - rev(nums(i)) 的对数
    全是 1 的情况下 答案 n * (n - 1) / 2 = 5000000000 < Long.MaxValue

    时间复杂度 O(9n)
    空间复杂度 O(n)
     */
    val mod = 1000000007
    var ans = 0L
    val map = scala.collection.mutable.Map[Int, Int]()
    for (num <- nums) {
      var rev = 0
      var k = num
      while (k > 0) {
        rev = rev * 10 + (k % 10)
        k /= 10
      }
      ans += map.getOrElse(num - rev, 0)
      map(num - rev) = map.getOrElse(num - rev, 0) + 1
    }

    (ans % mod).toInt
  }

  def main(args: Array[String]): Unit = {
    println(countNicePairs(Array(42, 11, 1, 97)))
    println(countNicePairs(Array(13, 10, 35, 24, 76)))
  }

}
