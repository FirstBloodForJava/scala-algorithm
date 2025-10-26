package com.oycm.algorithm.a.advance

object Solution_6 {

  /**
   * 2134.最少交换次数来组合所有的 1 II 1748
   * https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together-ii/
   * 交换：一个数组两个 互不相同 的位置交换两者的值
   * 环形数组：是一个数组，可以认为 第一个 元素和 最后一个 元素 相邻
   * 求在任意位置将所有 1 聚集在一起需要的最少交换次数
   *
   * @param nums 二进制环形数组，元素只有 1 或 0
   * @return
   */
  def minSwaps(nums: Array[Int]): Int = {
    var ans = 0

    // 转换：找到一个长为 k(就是数组中所有的 1) 的子数组，把子数组全变成 1
    // 只要不断把子数组的中的 0 进行交换，就可以得到全是 1 的子数组，求交换的次数最小值，就是求长为 k 连续子数组中 1 的个数最大值（k-max(1)），或 0 的个数的最小值
    // 计算 1 的个数比较方便，所以求 1 的个数最大值
    var k = 0
    for (i <- nums.indices) {
      k += nums(i)
    }
    // 没有 1
    if (k == 0) {
      return 0
    }
    // 在环形数组中，找到 k 长子数组，1 个数的最大值
    // 第一个窗口 [0, k-1]
    // 最后一个窗口 [n-1, n + k -2]，环形数组中，这里再继续下去，[n, n+k-1]就是对应第一个窗口 [0, k-1]
    // 当 i >= n 时，则可以通过取模(%) 进行映射
    val n = nums.length
    var temp = 0
    for (i <- 0 until n + k - 1) {
      temp += nums(i % n)
      // 窗口形成
      if (i - k + 1 >= 0) {
        // 更新
        ans = Math.max(ans, temp)
        // 出，由于 i 最大为 n+k-2 - k+1 = n-1 不会超过数组长度
        temp -= nums(i - k + 1)
      }
    }
    k - ans
  }

  def main(args: Array[String]): Unit = {
    println(minSwaps(Array(0, 1, 0, 1, 1, 0, 0)) == 1)
    println(minSwaps(Array(0, 1, 1, 1, 0, 0, 1, 1, 0)) == 2)
    println(minSwaps(Array(1, 1, 0, 0, 1)) == 0)
  }
}
