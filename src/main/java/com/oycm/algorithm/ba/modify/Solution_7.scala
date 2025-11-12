package com.oycm.algorithm.ba.modify

object Solution_7 {

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

  def optimizeModify(nums: Array[Int]): Array[Int] = {
    /*
    题解：找到最左边的 奇数 l 和 最右边的偶数 r 交换，交换后 l++, r-- 不断循环，知道 l >= r 没有可交换的数时，数组是符合要求的

    相向双指针

    时间复杂度 O(n)
    空间复杂度 O(1)
     */
    var l = 0
    var r = nums.length - 1
    while (l < r) {
      if (nums(l) % 2 == 0) {
        // 左边，当前是 偶数，查找下一个
        l += 1
      } else if (nums(r) % 2 != 0) {
        // 右边，当前是 奇数，查找下一个
        r -= 1
      } else {
        // 左边是 奇数，右边是 偶数 交换
        val temp = nums(l)
        nums(l) = nums(r)
        nums(r) = temp
        l += 1
        r -= 1
      }
    }

    nums
  }
}
