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

  def optimize(nums: Array[Int], key: Int, k: Int): List[Int] = {
    /*
    nums(j) == key时，[j-k, j+k] 范围的下标都符合
    题解优化：
    在区间 [max(i−k,0),min(i+k,n−1)] 是否存在元素等于 key
    至多 2k+1 长的滑动窗口

    记录 key 最后一次出现的位置 last，last 在窗口中，就把 i 加入答案。

    计算 [0, k-1] 窗口中的位置，i-k <= last 则记录答案
    后续 只要 i+k < n && nums(i+k) == key 更新 last, i-k <= last 则符合要求
    初始化 last = -k - 1，在 [0, k-1] 没有符合要求的 j
     */
    val n = nums.length
    val ans = scala.collection.mutable.ArrayBuffer[Int]()
    var last = -k - 1
    for (i <- k - 1 to 0 by -1 if last < 0) {
      if (nums(i) == key) {
        last = i
      }
    }

    for (j <- nums.indices) {
      if (j + k < n && nums(j + k) == key) {
        last = j + k
      }
      // [0, k-1] 没有符合要求的 j，则 last = -k - 1，会一直不符合要求
      // 更新 last 时，j 其实是最左边符合要求，直到 j - k > last 会不记录答案
      if (j - k <= last) {
        ans.append(j)
      }
    }

    ans.toList
  }

  def main(args: Array[String]): Unit = {
    println(findKDistantIndices(Array(3, 4, 9, 1, 3, 9, 5), 9, 1))
    println(findKDistantIndices(Array(2, 2, 2, 2, 2), 2, 2))

    println(optimize(Array(3, 4, 9, 1, 3, 9, 5), 9, 1))
    println(optimize(Array(2, 2, 2, 2, 2), 2, 2))
  }


}
