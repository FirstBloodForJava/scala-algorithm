package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_21 {

  /**
   * 2905. 找出满足差值条件的下标 II 1764
   * https://leetcode.cn/problems/find-indices-with-index-and-value-difference-ii/description/
   *
   * 在 nums 中找出下标对 i 和 j 满足以下条件：
   *  - abs(i - j) >= indexDifference 且
   *  - abs(nums[i] - nums[j]) >= valueDifference
   *
   * 存在返回任意一个下标对即可，不存在则返回 [-1, -1]
   * i 和 j 可能相等
   *
   * @param nums            长度为 n [1, 100000] 的整数数组, nums(i) [0, 10 pow 9]
   * @param indexDifference [0, 100000]
   * @param valueDifference [0, 10 pow 9]
   * @return
   */
  def findIndices(nums: Array[Int], indexDifference: Int, valueDifference: Int): Array[Int] = {
    /*
    abs(i - j) >= indexDifference
    枚举 i, 当 j = i - indexDifference + 1 >= 0 时，判断 abs(nums(i) - nums(j)) 是否符合条件
    由于 要 abs(nums(i) - nums(j)) >= valueDifference
    当 nums(i) >= 0 时
      nums(j) < 0, nums(j) 越小越符合条件;
      nums(i) >= nums(j) > 0, nums(j) 越小越符合条件;
      nums(j) > nums(i) 越大越符合条件
        maxDif = max( abs(nums(i) - min), abs(nums(i) - max)
    当 nums(i) < 0 时
      num(j) > 0, nums(j) 越大越符合条件;
      nums(i) <= nums(j) < 0, nums(j) 越大越符合条件;
      nums(j) < nums(i), nums(j) 越大越符合条件;
        maxDif = max(abs(nums(i) - min), abs(nums(i) - max)

    当 i 继续右移动时，min 和 nums(j) 更新最小值如果符合条件则

    问题转换成 和 3584 类似，移动过程中不断维护 min 和 max 的 下标
     */
    var min = 0
    var max = 0
    val ans = Array(-1, -1)
    for (i <- nums.indices if ans(0) < 0) {
      val j = i - indexDifference
      if (j >= 0) {
        min = if (nums(j) < nums(min)) j else min
        max = if (nums(j) > nums(max)) j else max
        if (Math.abs(nums(i) - nums(min)) >= valueDifference) {
          ans(0) = min
          ans(1) = i
        } else if (Math.abs(nums(i) - nums(max)) >= valueDifference) {
          ans(0) = max
          ans(1) = i
        }
      }
    }

    ans
  }

  def moveAbs(nums: Array[Int], indexDifference: Int, valueDifference: Int): Array[Int] = {
    /*
    思考：转换成下面的不等式 为什么可以去掉绝对值
    max - nums(j) >= valueDifference
    nums(j) - min >= valueDifference
    由于 max >= min
      当 nums(j) > max 时, 由于 max >= min, 所以 nums(j) - min >= nums(j) - max
      当 nums(j) < max 时, nums(j) - min 会出现小于 0 符合答案的情况吗？ 出现了，其绝对值也不会大于 max - nums(j)
        nums(j) < min => min - nums(j) <= max - nums(j)

    循环优化
     */
    var min = 0
    var max = 0
    val ans = Array(-1, -1)
    for (i <- indexDifference until nums.length if ans(0) < 0) {
      val j = i - indexDifference
      min = if (nums(j) < nums(min)) j else min
      max = if (nums(j) > nums(max)) j else max
      if (nums(i) - nums(min) >= valueDifference) {
        ans(0) = min
        ans(1) = i
      } else if (nums(max) - nums(i) >= valueDifference) {
        ans(0) = max
        ans(1) = i
      }

    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(findIndices(Array(5, 1, 4, 1), 2, 4).toSeq)
    println(findIndices(Array(2, 1), 0, 0).toSeq)
    println(findIndices(Array(1, 2, 3), 2, 4).toSeq)
    println(findIndices(Array(25, 49, 11, 15, 32, 33), 2, 37).toSeq)
    println(findIndices(Array(3, 12, 40), 0, 9).toSeq)

    println(moveAbs(Array(3, 12, 40), 0, 9).toSeq)

  }

}
