package com.oycm.algorithm.ba.modify

object Solution_1 {

  /**
   * 27. 移除元素
   * https://leetcode.cn/problems/remove-element/
   *
   * 原地 移除数组中所有数值等于 val 的元素
   *
   * 假设 nums 中不等于 val 的元素数量为 k，要求 nums 的前 k 个元素包含不等于 val 的元素
   * @param nums
   * @param val
   * @return 返回 nums 中与 val 不同的元素的数量
   */
  def removeElement(nums: Array[Int], `val`: Int): Int = {
    /*
    把 nums 视为栈，不等于 val 的入栈，最后栈的大小就是 不同的数量
    */
    var ans = 0
    for (num <- nums) {
      if (num != `val`) {
        nums(ans) = num
        ans += 1
      }
    }
    ans
  }
}
