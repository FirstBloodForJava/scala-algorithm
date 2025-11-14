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

  def doublePoint(nums: Array[Int]): Unit = {
    /*
    使用额外空间思路，创建一个长为 n 的数组，初始值都为 0，遍历 nums，当元素不为 0 时，赋值给 新数组，i++

    使用双指针，一个遍历当前元素，出现 0 时，和后面的非 0 元素交换，查找下一个 0，这样不好循环

    遍历元素，当 nums(i) == 0 时，查找后面的非0 元素，交换

    时间复杂度 O(n)
     */
    val n = nums.length
    var j = 0
    for (i <- nums.indices if j < n) {
      if (nums(i) == 0) {
        j = i + 1
        while (j < n && nums(j) == 0) {
          j += 1
        }
        // 交换
        if (j < n) {
          nums(i) = nums(j)
          nums(j) = 0
        }
      }
    }
    //println(nums.toSeq)
  }

  def answer_1(nums: Array[Int]): Unit = {
    /*
    题解思路：用一个栈记录非 0 元素，栈的大小 stackSize，剩余元素补 0 即可
    可以把 nums 看成 栈，变量 stackSize 记录栈的大小，元素非0，则 nums(stackSize) = nums(i), stackSize++
    [stackSize, n) 剩余部分补 0 即可
    时间复杂度 O(n)
    空间复杂度 O(1)
     */
    var idx = 0
    for (num <- nums) {
      if (num != 0) {
        nums(idx) = num
        idx += 1
      }
    }
    for (i <- idx until nums.length) {
      nums(i) = 0
    }
    //println(nums.toSeq)
  }

  def main(args: Array[String]): Unit = {
    moveZeroes(Array(0, 1, 0, 3, 12))
    moveZeroes(Array(0))

    doublePoint(Array(0, 1, 0, 3, 12))
    doublePoint(Array(0))

    answer_1(Array(0, 1, 0, 3, 12))
    answer_1(Array(0))
  }

}
