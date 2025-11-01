package com.oycm.algorithm.abc.max

object Solution_1 {

  /**
   * 713.乘积小于 K 的子数组
   * https://leetcode.cn/problems/subarray-product-less-than-k/
   *
   * @param nums 正整数数组
   * @param k    整数 k
   * @return 连续子数组中所有元素乘积小于 k 的个数
   */
  def numSubarrayProductLessThanK(nums: Array[Int], k: Int): Int = {
    /**
     * [l, r] 以左端点 l 开始向右滑动 计算子数组乘积，逻辑如下：
     * - [0, r1] 记录答案 r1 + 1
     * - [1, r2] 右端点可以从 r1 内循环
     * ...
     * - [n-1, n-1]
     * 开始 内循环次数 不会超过 n
     * r > l 才需要 / nums(l-1) 否则 r = l
     *
     *
     */
    var ans = 0
    var temp = 1
    var r = 0
    for (l <- nums.indices) {
      // 下一次循环 l++
      if (r >= l && l > 0) {
        temp = temp / nums(l - 1)
      } else {
        r = l
      }
      var flag = true
      // 当 nums(l) >= k 时，由于回退原因，导致 temp / nums(l - 1) = 0 导致后面计算有问题。
      // 这里就需要 r = l
      while (r < nums.length && flag) {
        temp = temp * nums(r)
        if (temp < k) {
          r += 1
        } else {
          // 回退计算
          temp = temp / nums(r)
          flag = false
        }
      }
      // [l, r) 是符合要求的子数组区间
      if (temp < k) {
        ans += r - l
      }

    }
    ans
  }

  def method_1(nums: Array[Int], k: Int): Int = {
    /**
     * 从左到右计算比较麻烦, [?, r] 找到左端点的最小值 l 符合要求，则 r - l + 1 就是以 r 为右端点的所有连续子数组
     * 随着 r 不断向右移动，乘积不断变大 l 也需要不断向右移动
     */
    var ans = 0
    // 因为 nums(i) >= 1
    if (k <= 1) {
      return ans
    }
    var temp = 1
    var l = 0
    for (r <- nums.indices) {
      temp = temp * nums(r)
      // nums(i) >= k 时 需要 l == r + 1 保证后面结果计算结果 为 0
      while (temp >= k && l <= r) {
        // 这里如果 k 是 1 或 0 循环就会有问题
        // nums(i) >= k 时 l > r
        temp = temp / nums(l)
        l += 1
      }
      ans += r - l + 1
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(numSubarrayProductLessThanK(Array(10, 5, 2, 6), 100) == 8)
    println(numSubarrayProductLessThanK(Array(1, 2, 3), 0) == 0)
    println(numSubarrayProductLessThanK(Array(1, 2, 3, 100), 2) == 1)
    println(numSubarrayProductLessThanK(Array(10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3), 19) == 18)

    println(method_1(Array(10, 5, 2, 6), 100) == 8)
    println(method_1(Array(1, 2, 3), 0) == 0)
    println(method_1(Array(1, 2, 3, 100), 2) == 1)
    println(method_1(Array(10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3), 19) == 18)

  }
}
