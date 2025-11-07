package com.oycm.algorithm.ba.modify

object Solution_2 {

  /**
   * 26. 删除有序数组中的重复项
   * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/
   *
   * 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
   *
   * nums 的前 k 个元素应包含 排序后 的唯一数字。下标 k - 1 之后的剩余元素可以忽略。
   *
   * @param nums 非严格递增的数组, i < j 则 nums(i) <= nums(j)
   * @return
   */
  def removeDuplicates(nums: Array[Int]): Int = {
    var k = 1
    var pre = nums(0)
    for (i <- 1 until nums.length) {
      if (nums(i) > pre) {
        nums(k) = nums(i)
        pre = nums(k)
        k += 1
      }
    }
    //println(nums.slice(0, k).toSeq)
    k
  }

  def main(args: Array[String]): Unit = {
    println(removeDuplicates(Array(1, 1, 2)))
    println(removeDuplicates(Array(0,0,1,1,1,2,2,3,3,4)))
  }
}
