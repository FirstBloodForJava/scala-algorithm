package com.oycm.algorithm.a.other

object Solution_1 {

  /**
   * 2200. 找出数组中的所有 K 近邻下标
   * https://leetcode.cn/problems/find-all-k-distant-indices-in-an-array/description/
   *
   * K 近邻下标：nums 中的一个下标 i ，并满足至少存在一个下标 j 使得 |i - j| <= k 且 nums[j] == key 。
   *
   * 列表形式返回按 递增顺序 排序的所有 K 近邻下标
   *
   * @param nums 整数数组，长为 n
   * @param key  nums 整数数组中一个元素
   * @param k    [1, n]
   * @return
   */
  def findKDistantIndices(nums: Array[Int], key: Int, k: Int): List[Int] = {
    /*
    nums(j) == key时，[j-k, j+k] 范围的下标都符合

    时间复杂度 O(n * 2k)
    空间复杂度 O(n)
     */
    val ans = scala.collection.mutable.ArrayBuffer[Int]()
    val map = scala.collection.mutable.Map[Int, Int]()
    val n = nums.length
    for (j <- nums.indices) {
      if (nums(j) == key) {
        var i = Math.max(0, j - k)
        while (i <= Math.min(j + k, n - 1)) {
          if (!map.contains(i)) {
            ans.append(i)
            map(i) = i
          }
          i += 1
        }
      }
    }

    ans.toList
  }

  def main(args: Array[String]): Unit = {
    println(findKDistantIndices(Array(3, 4, 9, 1, 3, 9, 5), 9, 1))
    println(findKDistantIndices(Array(2, 2, 2, 2, 2), 2, 2))
  }


}
