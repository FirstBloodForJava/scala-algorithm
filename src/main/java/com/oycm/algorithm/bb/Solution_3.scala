package com.oycm.algorithm.bb

object Solution_3 {

  /**
   * 88. 合并两个有序数组
   * https://leetcode.cn/problems/merge-sorted-array/description/
   *
   * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
   *
   * @param nums1 非递减顺序 整数数组
   * @param m     nums1 的长度
   * @param nums2 非递减顺序 整数数组
   * @param n     nums2 的长度
   */
  def merge(nums1: Array[Int], m: Int, nums2: Array[Int], n: Int): Unit = {
    /*
    从后面遍历数组, 每次取一个最大的放在 num1 的后面，哪个取了元素的数组 下标 自减
    时间复杂度 m + n
    */
    val k = nums1.length
    var i = m - 1
    var j = n - 1
    for (l <- k - 1 to 0 by -1) {
      if (j >= 0 && i >= 0 && nums2(j) >= nums1(i)) {
        nums1(l) = nums2(j)
        j -= 1
      } else if (j >= 0 && i >= 0 && nums2(j) < nums1(i)) {
        nums1(l) = nums1(i)
        i -= 1
      } else if (j == -1) {
        nums1(l) = nums1(i)
        i -= 1
      } else if (i == -1) {
        nums1(l) = nums2(j)
        j -= 1
      }
    }
    //println(nums1.toSeq)
  }

  def main(args: Array[String]): Unit = {
    merge(Array(1, 2, 3, 0, 0, 0), 3, Array(2, 5, 6), 3)
    merge(Array(1), 1, Array(), 0)
    merge(Array(0), 0, Array(1), 1)
  }
}
