package com.oycm.algorithm.ba.modify

object Solution_3 {

  /**
   * 80. 删除有序数组中的重复项 II
   * https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/description/
   *
   * 有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次
   *
   * @param nums 有序数组
   * @return 删除后的数组大小
   */
  def removeDuplicates(nums: Array[Int]): Int = {
    var k = 1
    var time = 1
    var pre = nums(0)
    for (i <- 1 until nums.length) {
      if (nums(i) > pre) {
        nums(k) = nums(i)
        pre = nums(k)
        time = 1
        k += 1
      } else {
        // 相等
        if (time <= 1) {
          time += 1
          nums(k) = nums(i)
          k += 1
        }
      }
    }
    //println(nums.slice(0, k).toSeq)
    k
  }

  def main(args: Array[String]): Unit = {

    println(removeDuplicates(Array(1, 1, 1, 2, 2, 3)))
    println(removeDuplicates(Array(0, 0, 1, 1, 1, 1, 2, 3, 3)))
  }
}
