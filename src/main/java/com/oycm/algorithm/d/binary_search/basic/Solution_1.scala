package com.oycm.algorithm.d.binary_search.basic

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
    val start = lowerBound_2(nums, target)
    if (start == nums.length || nums(start) != target) {
      return Array(-1, -1)
    }
    val end = lowerBound_2(nums, target + 1) - 1
    ans(0) = start
    ans(1) = end
    ans
  }


  /**
   *
   * @param nums 非递减数组(升序), 长为 n
   * @param x    大于等于 x 的 第一个 index
   * @return
   */
  def lowerBound_1(nums: Array[Int], x: Int): Int = {
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

  /**
   * 左闭右开区间写法
   *
   * @param nums 非递减数组(升序), 长为 n
   * @param x 大于等于 x 的 第一个 index
   * @return
   */
  def lowerBound_2(nums: Array[Int], x: Int): Int = {
    var r = nums.length
    var l = 0
    // 存在元素
    while (l < r) {
      val mid = l + (r - l) / 2
      if (nums(mid) >= x) {
        r = mid
      } else {
        l = mid + 1
      }
    }
    /*
    注意循环结束时: l == r
  如果存在 nums(i) >= x, 根据循环条件
    则 nums(r) >= x, nums(l - 1) < x, nums(l) = nums(r) >= x
  如果不存在 nums(i) >= x, 根据循环条件, 则 l 不断右移, 直到 l = n -1 = r - 1

  所以 l|r 都可以是答案, 当 r < n 时, 就是答案
  */
    r
  }

  /**
   * 开区间写法
   * @param nums 非递减数组(升序), 长为 n
   * @param x 大于等于 x 的 第一个 index
   * @return
   */
  def lowerBound_3(nums: Array[Int], x: Int): Int = {
    var r = nums.length
    var l = -1
    // 存在元素
    while (l + 1 < r) {
      val mid = l + (r - l) / 2
      if (nums(mid) >= x) {
        r = mid
      } else {
        l = mid
      }
    }
    /*
    注意循环结束时: l + 1 == r, l = r - 1
  如果存在 nums(i) >= x, 根据循环条件
    则 nums(r) >= x, nums(l) < x, nums(r-1) < x, nums(r) >= x
  如果不存在 nums(i) >= x, 根据循环条件, 则 l 不断右移, 直到 l = n - 1 = r - 1

  所以 r 是答案, 当 r < n 时, 就是答案
  */
    r
  }

  def main(args: Array[String]): Unit = {
    println(searchRange(Array(5, 7, 7, 8, 8, 10), 8).toSeq)
  }
}
