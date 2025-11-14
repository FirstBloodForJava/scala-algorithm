package com.oycm.algorithm.ba.modify

object Solution_12 {

  /**
   * 75. 颜色分类
   * https://leetcode.cn/problems/sort-colors/description/
   *
   * 整数 0、 1 和 2 分别表示红色、白色和蓝色。
   * 原地对 nums 进行排序同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
   *
   * @param nums 包含红色、白色和蓝色 的数组
   */
  def sortColors(nums: Array[Int]): Unit = {
    /*
    只有 0, 1, 2 元素的数组, 原地进行升序排序
    两次交换：把 0 全部交换到左边，把 2 全部交换到右边

    时间复杂度 O(n)
    */
    var i0 = 0
    for (i <- nums.indices) {
      if (nums(i) == 0) {
        val temp = nums(i0)
        nums(i0) = nums(i)
        nums(i) = temp
        i0 += 1
      }
    }
    val n = nums.length
    var i2 = n - 1
    var i = n - 1
    while (i >= i0) {
      if (nums(i) == 2) {
        val temp = nums(i2)
        nums(i2) = nums(i)
        nums(i) = temp
        i2 -= 1
      }
      i -= 1
    }
    //println(nums.toSeq)
  }

  def main(args: Array[String]): Unit = {
    sortColors(Array(2, 0, 2, 1, 1, 0))
  }

}
