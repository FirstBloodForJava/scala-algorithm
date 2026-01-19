package com.oycm.algorithm.e.enum_right_log_left.basic

object Solution_13 {

  /**
   * 面试题 16.24. 数对和
   * https://leetcode.cn/problems/pairs-with-sum-lcci/description/
   *
   * 找出 数组中两数之和为指定值的所有整数对
   *
   * @param nums
   * @param target
   * @return
   */
  def pairSums(nums: Array[Int], target: Int): List[List[Int]] = {
    /*
    匹配后，记录答案变了而已
     */
    val ans = scala.collection.mutable.ArrayBuffer[List[Int]]()
    val map = scala.collection.mutable.Map[Int, Int]()
    for (num <- nums) {
      val cnt = map.getOrElse(target - num, 0)
      if (cnt > 0) {
        map(target - num) -= 1
        ans.append(List(target - num, num))
      } else {
        map(num) = map.getOrElse(num, 0) + 1
      }
    }

    ans.toList
  }


}
