package com.oycm.algorithm.bb

object Solution_2 {

  /**
   * 2540. 最小公共值
   * https://leetcode.cn/problems/minimum-common-value/description/
   *
   * 返回两个数组的 最小公共整数，没有则返回 -1
   *
   * @param nums1 非降序排序 正整数数组
   * @param nums2 非降序排序 正整数数组
   * @return
   */
  def getCommon(nums1: Array[Int], nums2: Array[Int]): Int = {
    /*
    初始化 i = 0, 在 num1 数组中移动
    初始化 j = 0，在 num2 数组中移动
    哪个数组的下标值更小，则 指针右移

    时间复杂度 n + m
    */
    var ans = -1
    val m = nums1.length
    var i = 0
    val n = nums2.length
    var j = 0
    while (i < m && j < n && ans == -1) {
      if (nums1(i) > nums2(j)) {
        j += 1
      } else if (nums1(i) < nums2(j)) {
        i += 1
      } else {
        ans = nums1(i)
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(getCommon(Array(1, 2, 3), Array(2, 3)))
    println(getCommon(Array(1, 2, 3, 6), Array(2, 3, 4, 5)))
    println(getCommon(Array(1, 2, 6), Array(4, 5)))
  }

}
