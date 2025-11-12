package com.oycm.algorithm.ba.modify

object Solution_8 {

  /**
   * 922. 按奇偶排序数组 II
   * https://leetcode.cn/problems/sort-array-by-parity-ii/description/
   *
   * nums 中一半整数是 奇数 ，一半整数是 偶数 。
   *
   * 对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
   *
   * @param nums 正整数数组，长度为 n [2, 2*10 pow 4]，且是偶数
   * @return
   */
  def sortArrayByParityII(nums: Array[Int]): Array[Int] = {
    /*
    题目只是说一半的数是 偶数，一半的数是 奇数
    相向双指针：左边不符合要求的 奇数值偶数下标 和 右边不符合要求的 偶数值奇数下标 交换
    维护一个指针 l，从 0 到 n-1，l 是偶数，nums(l) 是奇数，另一个指针 r，n-1 到 1，r 为奇数，nums(r) 是偶数，nums(l) 和 numrs(r) 交换值 l+=2, r-=2
    不断循环直到 不满足 l < n, r > 0

    l 执行 n/2 次, r 执行 n/2 次

    时间复杂度 O(n)
     */
    val n = nums.length
    var l = 0
    var r = n - 1
    while (l < n) {
      // 找 nums(l) % 2 == 0
      while (l < n && nums(l) % 2 == 0) {
        l += 2
      }
      // 找 nums(l) % 2 == 0 && r % 2 == 1
      while (r > 0 && nums(r) % 2 == 1) {
        r -= 2
      }
      // 判断边界
      if (l < n && r > 0) {
        val temp = nums(l)
        nums(l) = nums(r)
        nums(r) = temp
        l += 2
        r -= 2
      }
    }
    nums
  }

  def same(nums: Array[Int]): Array[Int] = {
    /*
    同向双指针：
    i = 0 nums(i) % 2 == 0; i+=2
    j = 1 nums(j) % 2 == 1; j+=2
    否则交换 i+=2; j+=2
     */
    val n = nums.length
    var i = 0
    var j = 1
    while (i < n) {
      if (nums(i) % 2 == 0) {
        i += 2
      } else if (nums(j) % 2 == 1) {
        j += 2
      } else {
        val temp = nums(i)
        nums(i) = nums(j)
        nums(j) = temp
        i += 2
        j += 2
      }
    }

    nums
  }

  def main(args: Array[String]): Unit = {
    println(sortArrayByParityII(Array(4, 2, 5, 7)).toSeq)
    println(sortArrayByParityII(Array(2, 3)).toSeq)
    println(sortArrayByParityII(Array(2, 1, 0, 1, 1, 2, 0, 3)).toSeq)

    println(same(Array(4, 2, 5, 7)).toSeq)
    println(same(Array(2, 3)).toSeq)
    println(same(Array(2, 1, 0, 1, 1, 2, 0, 3)).toSeq)
  }
}
