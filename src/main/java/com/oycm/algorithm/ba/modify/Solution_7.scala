package com.oycm.algorithm.ba.modify

class Solution_7 {

  /**
   * 905. 按奇偶排序数组
   * https://leetcode.cn/problems/sort-array-by-parity/description/
   *
   * 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
   *
   * @param nums 整数数组，长为 n
   * @return
   */
  def sortArrayByParity(nums: Array[Int]): Array[Int] = {
    /*
    维护三个指针

    时间复杂度 O(n)
    空间复杂度 O(n)
     */
    val n = nums.length
    val ans = Array.fill(n)(0)
    var l = 0
    var r = n - 1
    for (num <- nums) {
      if (num % 2 == 0) {
        ans(l) = num
        l += 1
      } else {
        ans(r) = num
        r -= 1
      }
    }

    ans
  }

}
