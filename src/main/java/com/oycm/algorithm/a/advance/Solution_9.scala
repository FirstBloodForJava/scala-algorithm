package com.oycm.algorithm.a.advance

object Solution_9 {

  /**
   * 2653.滑动子数组的美丽值 1786
   * https://leetcode.cn/problems/sliding-subarray-beauty/
   *
   * 子数组的 美丽值 定义：子数组中第 x 小整数 是 负数 ，那么美丽值为第 x 小的数，否则美丽值为 0 。
   * n == nums.length
   * 1 <= k <= n
   * 1 <= x <= k
   * 返回一个长度 n-k+1 的数据，记录 从 0 开始子数组的美丽值
   *
   * @param nums 长度为 n 的整数数组
   * @param k 子数组长度
   * @param x
   * @return
   */
  def getSubarrayBeauty(nums: Array[Int], k: Int, x: Int): Array[Int] = {
    val n = nums.length
    val ans = Array.fill(n - k + 1)(0)
    // 记录负数出现的次数
    val negativeMap = scala.collection.mutable.TreeMap[Int, Int]()
    for (r <- nums.indices) {
      // 1. 入
      if (nums(r) < 0) {
        negativeMap(nums(r)) = negativeMap.getOrElse(nums(r), 0) + 1
      }
      val l = r - k + 1
      if (l >= 0) {
        // 2. 更新结果
        var seq = 0
        // 退出标志
        var found = false
        for ((k1, v1) <- negativeMap if !found) {
          seq += v1
          if (seq >= x) {
            ans(l) = k1
            found = true
          }
        }

        // 3. 出
        if (nums(l) < 0) {
          negativeMap(nums(l)) -= 1
          if (negativeMap(nums(l)) == 0) {
            negativeMap.remove(nums(l))
          }
        }
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(getSubarrayBeauty(Array(1, -1, -3, -2, 3), 3, 2).toSeq)
    println(getSubarrayBeauty(Array(-1,-2,-3,-4,-5), 2, 2).toSeq)
    println(getSubarrayBeauty(Array(-3,1,2,-3,0,-3), 2, 1).toSeq)

  }
}
