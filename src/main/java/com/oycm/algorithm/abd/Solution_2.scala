package com.oycm.algorithm.abd

object Solution_2 {

  /**
   * 2401.最长优雅子数组 1750
   * https://leetcode.cn/problems/longest-nice-subarray/?envType=problem-list-v2&envId=vOe0WPgi
   *
   * 优雅子数组：nums 中子数组位于不用位置的元素，按 位与(&)运算的结果等于 0，则称为优雅子数组。
   *  - &：与运算，两个为 1 才为 1
   *  - 1 <= nums[i] <= 10 9，二进制最多 30 位
   *
   * @param nums 正整数数组
   * @return
   */
  def longestNiceSubarray(nums: Array[Int]): Int = {
    /*
    两个奇数的 & 结果肯定 不为0
    题解：与运算结果不为 0，子数组中各个元素的 Bit 位之间的位置没有交集，该子数组最长为 30
    可以暴力枚举，子循环不会超过 20
    */
    var ans = 0

    for (r <- nums.indices) {
      // 技巧：用 或运算 记录已遍历元素的 1 位置情况
      var or = 0
      // 枚举右端点
      var l = r
      while (l >= 0 && (nums(l) & or) == 0) {
        or |= nums(l)
        l -= 1
      }
      // 循环退出时 l 是不符合条件的，所以不需要 + 1
      ans = Math.max(ans, r - l)
    }
    ans
  }

  def slidingWindow(nums: Array[Int]): Int = {
    /*
    题解：滑动窗口 + 或运算添加元素 + 异或运算删除元素
    r 不断右移，如果都符合条件，每次更新答案
    当 r 不符合条件时，意味着左端点有元素不符合要求，则需要从子数组中移除元素(异或运算，两个不相同则为 1)，符合条件时，此时就是暴力枚举 r 端点的 ans
    */
    var ans = 0
    // or 里面都是没有 位交集的，和历史元素进行 异或(^) 运算，则可以删除 1 的 bit 位
    var or = 0
    var l = 0
    for (r <- nums.indices) {
      while ((nums(r) & or) > 0) {
        // 移除元素
        or ^= nums(l)
        l += 1
      }
      // 加元素
      or |= nums(r)
      ans = Math.max(ans, r - l + 1)
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(longestNiceSubarray(Array(1, 3, 8, 48, 10)) == 3)
    println(longestNiceSubarray(Array(3, 1, 5, 11, 13)) == 1)

    println(slidingWindow(Array(1, 3, 8, 48, 10)) == 3)
    println(slidingWindow(Array(3, 1, 5, 11, 13)) == 1)
  }
}
