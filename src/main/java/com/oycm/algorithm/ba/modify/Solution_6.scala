package com.oycm.algorithm.ba.modify

object Solution_6 {

  /**
   * 283. 移动零
   * https://leetcode.cn/problems/move-zeroes/description/
   *
   * 将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
   *
   * @param nums
   */
  def moveZeroes(nums: Array[Int]): Unit = {
    /*
    使用 额外的队列 queue，记录 0 的下标
    遇到 非 0 元素时，queue 不为空时，交换值，当前下标就是 0

    时间复杂度 O(n)
    空间复杂度 O(n)
     */
    val queue = scala.collection.mutable.Queue[Int]()
    for (i <- nums.indices) {
      if (nums(i) == 0) {
        queue.append(i)
      } else if (queue.nonEmpty) {
        nums(queue.removeHead()) = nums(i)
        nums(i) = 0
        queue.append(i)
      }
    }
    //println(nums.toSeq)
  }

  def main(args: Array[String]): Unit = {
    println(moveZeroes(Array(0, 1, 0, 3, 12)))
    println(moveZeroes(Array(0)))
  }

}
