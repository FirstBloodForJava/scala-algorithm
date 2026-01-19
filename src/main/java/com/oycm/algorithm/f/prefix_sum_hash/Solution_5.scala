package com.oycm.algorithm.f.prefix_sum_hash

object Solution_5 {

  /**
   * 523. 连续的子数组和
   * https://leetcode.cn/problems/continuous-subarray-sum/description/
   *
   * 好的子数组定义：
   *  - 长度至少为 2
   *  - 子数组元素总和为 k 的倍数
   *
   * @param nums nums[i] 范围 [0, 10 pow 9]
   * @param k
   * @return
   */
  def checkSubarraySum(nums: Array[Int], k: Int): Boolean = {
    /*
    [l, r) 前缀和查找是否存在 (s(r) - s(l)) % k == 0
    即 s(r) % k == s(l) % k 且 r - l >= 2
    由于 nums(i) >= 0, s(i) <= s(i+1)
    为了使子数组更长 s(l) 可以只记录前面出现的下标
    用 map 的 key 记录 s(l) value 记录 key 第一次出现的下标
    */
    var ans = false
    val map = scala.collection.mutable.Map[Int, Int]()
    var sum = 0
    for (i <- nums.indices if !ans) {
      // 记录 s(l)
      map(sum) = map.getOrElse(sum, i)
      // 计算 s(r)
      sum = (sum + nums(i) % k) % k
      if (map.getOrElse(sum, -1) >= 0 && i - map(sum) + 1 >= 2) {
        ans = true
      }

    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(checkSubarraySum(Array(23, 2, 4, 6, 7), 6))
    println(checkSubarraySum(Array(23, 2, 6, 4, 7), 6))
    println(checkSubarraySum(Array(23, 2, 6, 4, 7), 13))
  }

}
