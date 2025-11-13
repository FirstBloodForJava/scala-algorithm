package com.oycm.algorithm.bb.subsequence

object Solution_6 {

  /**
   * 3132. 找出与数组相加的整数 II 1620
   * https://leetcode.cn/problems/find-the-integer-added-to-array-ii/description/
   *
   * 从 nums1 中移除两个元素，并且所有其他元素都与变量 x 的和
   *
   * nums1 和 nums2 相等 。当两个数组中包含相同的整数，并且这些整数出现的频次相同时，两个数组 相等 。
   *
   * @param nums1 整数数组
   * @param nums2 整数数组
   * @return
   */
  def minimumAddedInteger(nums1: Array[Int], nums2: Array[Int]): Int = {
    /*
    [4,20,16,12,8] [14,18,10]
    [4,8,12,16,20] [10,14,18]
    找到 nums2, nums1 数组的最小值，

    存在 x，删除元素后，数组相等，则一定有 x = min(nums2) - min(nums1)
    注意：删除两个元素后 x = min(nums2) - min(nums1)，数组不一定相等，还需要判断剩余元素的和是否等于 nums2 的和，因为删除的元素可能不是最大值，会是中间的元素

    对 num1 数组排序，nums1 最小值范围只能是 [0, 2]
    假如 nums1(3) = min(nums2) - x, nums(3) > nums(2), 则 nums1 的 0, 1, 2 的值 加上 x 都不能 等于 nums2 的其它元素，

    时间复杂度 O(n log n) 在排序
     */
    var ans = 10000
    // nums2 排序
    val s2 = nums2.sorted
    // 对 nums1 排序
    val s1 = nums1.sorted
    for (i <- 0 to 2) {
      // 找到要移除的元素
      var remove = i
      var i1 = i
      var i2 = 0
      val x = s2(0) - s1(i)
      while (remove < 3 && i1 < s1.length && i2 < s2.length) {
        if (s1(i1) + x != s2(i2)) {
          i1 += 1
          remove += 1
        } else {
          i1 += 1
          i2 += 1
        }
      }
      if (remove <= 2) {
        ans = Math.min(ans, x)
      }

    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(minimumAddedInteger(Array(4, 20, 16, 12, 8), Array(14, 18, 10)))
    println(minimumAddedInteger(Array(3, 5, 5, 3), Array(7, 7)))
    println(minimumAddedInteger(Array(4, 6, 3, 1, 4, 2, 10, 9, 5), Array(5, 10, 3, 2, 6, 1, 9)))
  }

}
