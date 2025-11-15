package com.oycm.algorithm.da

object Solution_1 {

  /**
   * 34. 在排序数组中查找元素的第一个和最后一个位置
   * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
   *
   * 不存在目标值，返回 [-1, -1]
   *
   * @param nums   递减顺序排列的整数数组
   * @param target 目标值
   * @return 找出给定目标值在数组中的开始位置和结束位置
   */
  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    /*
    时间复杂度 O(log n)
    */
    val ans = Array.fill(2)(0)
    val start = lowerBound(nums, target)
    if (start == nums.length || nums(start) != target) {
      return Array(-1, -1)
    }
    val end = lowerBound(nums, target + 1) - 1
    ans(0) = start
    ans(1) = end
    ans
  }

  /**
   *
   * @param nums 非递减数组, 长为 n
   * @param x    大于等于 x 的 第一个 index
   * @return
   */
  def lowerBound(nums: Array[Int], x: Int): Int = {
    var r = nums.length - 1
    var l = 0
    // 存在元素
    while (l <= r) {
      val mid = l + (r - l) / 2
      if (nums(mid) >= x) {
        r = mid - 1
      } else {
        l = mid + 1
      }
    }
    /*
    注意循环结束时: l == r + 1
    如果 全部 nums(i) >= x, l = 0, r = -1
    如果 全部 nums(i) < x, l = n, r = n - 1
    如果存在 nums(i) >= x, 且 nums(i) < x
    退出循环前 mid == l == r
      如果 nums(l) < x, 则 l = r + 1, 因为 r 是上次减 1 的到的，所以 nums(r+1) >= x
      如果 nums(l) >= x, 则 l = l, nums(l) >= x
    */
    l
  }

  def main(args: Array[String]): Unit = {
    println(searchRange(Array(5, 7, 7, 8, 8, 10), 8).toSeq)
  }
}
