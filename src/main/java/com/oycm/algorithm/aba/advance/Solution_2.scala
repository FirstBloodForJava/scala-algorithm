package com.oycm.algorithm.aba.advance

object Solution_2 {

  /**
   * 2779. 数组的最大美丽值
   * https://leetcode.cn/problems/maximum-beauty-of-an-array-after-applying-operation/description/
   *
   * 数组的美丽值：为数组中由相等元素组成的最长子序列的长度。
   * 数组的子序列：经由原数组删除一些元素（也可能不删除）得到的一个新数组，且在此过程中剩余元素的顺序不发生改变
   *
   * 在一步操作中，你可以执行下述指令：
   *  - 在范围 [0, nums.length - 1] 中选择一个 此前没有选过 的下标 i
   *  - 将 nums[i] 替换为范围 [nums[i] - k, nums[i] + k] 内的任一整数。
   *
   * 只能对每个下标执行 一次 此操作
   *
   * @param nums 整数数组
   * @param k    非负整数
   * @return
   */
  def maximumBeauty(nums: Array[Int], k: Int): Int = {
    /*
    求 修改后 元素相同 最长子序列，可以对原数组任意元素进行 删除/保留操作
    问题就是求 一个新数组，存一个值的范围 [nums(i)-k, nums(i) + k]，范围交集最多的数量
    可以对数组进行升序排序，当 l < r 时，则 nums(l) <= nums(r)，如果 [nums(l), nums(l) + k] 和 [nums(r) - k, nums(r)] 之间有交集 ， r - l 答案
    为什么只考虑这个区间？计算区间有交集
      假如 nums(l) < nums(r)，如果存在 nums(l) + l1 = nums(r) + r1，l1, r1 in [-k, k]
        则 l1 > 0, r1 >= 0, 当第一次匹配时，l1 和 r1 同时在[-k, k] 的范围内自增或自减都是一样的，如果 nums(l) + k >= nums(r) - k 那他们之间进行操作肯定会相对
     固定 l，l1 => r1, l2 => r2，r 随着 l 的同向移动，当 nums(l) + k >= nums(r) - k，r++，不符合条件时 r - l 就是答案
     固定 r，当 nums(l) + k < nums(r) - k 时，l++，符合条件，退出循环 r - l + 1 就是答案

     时间复杂度 O(n log n)，在排序
     */
    val sort = nums.sorted
    var ans = 0

    var l = 0
    for (r <- sort.indices) {
      while (sort(l) < sort(r) - 2 * k) {
        l += 1
      }
      ans = Math.max(r - l + 1, ans)
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maximumBeauty(Array(4, 6, 1, 2), 2))
    println(maximumBeauty(Array(1, 1, 1, 1), 0))
  }
}
