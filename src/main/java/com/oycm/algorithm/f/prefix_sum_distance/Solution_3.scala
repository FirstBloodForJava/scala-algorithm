package com.oycm.algorithm.f.prefix_sum_distance

object Solution_3 {

  /**
   * 2602. 使数组元素全部相等的最少操作次数 1903
   * https://leetcode.cn/problems/minimum-operations-to-make-all-array-elements-equal/description/
   *
   * 每个 ans(i) 表示 将 nums 中所有元素 按 以下操作 变成 queries(i) 的最小次数：
   *  - 将数组里一个元素 增大 或者 减小 1，记一次操作次数
   *
   * @param nums    正整数数组
   * @param queries 待查询数组
   * @return
   */
  def minOperations(nums: Array[Int], queries: Array[Int]): List[Long] = {
    /*
    本质就是求的就是 所有 nums 与 queries(i) 的绝对值和，问题是怎么快速求和？
    原先是知道 queries(i) 在 nums 中的位置，现在 queries(i) 可能在 nums 都不存在

    可以对 nums 进行排序，遍历 queries 时查找 >= queries 的下标

    时间复杂度 O(n log n + m log n)
    空间复杂度 O(n)
    */
    val ans = Array.fill(queries.length)(0L)
    val sort = nums.sorted
    val sums = Array.fill(nums.length + 1)(0L)
    for (i <- sort.indices) {
      // 注意使用 sort 做前缀和
      sums(i + 1) = sums(i) + sort(i)
    }

    def lowerBound(arr: Array[Int], target: Int): Int = {
      var l = -1
      var r = arr.length
      while (l + 1 < r) {
        val mid = l + (r - l) / 2
        if (arr(mid) >= target) {
          r = mid
        } else {
          l = mid
        }
      }
      r
    }

    for (i <- queries.indices) {
      val same = queries(i)
      val index = lowerBound(sort, same)

      ans(i) = index * same.toLong - sums(index) + sums.last - sums(index) - (nums.length - index) * same.toLong
    }
    ans.toList
  }

  def main(args: Array[String]): Unit = {
    //    println(minOperations(Array(3, 1, 6, 8), Array(1, 5)))
    //    println(minOperations(Array(2, 9, 6, 3), Array(10)))
    println(minOperations(Array(47, 50, 97, 58, 87, 72, 41, 63, 41, 51, 17, 21, 7, 100, 69, 66, 79, 92, 84, 9, 57, 26, 26, 28, 83, 38),
      Array(50, 84, 76, 41, 64, 82, 20, 22, 64, 7, 38, 92, 39, 28, 22, 3, 41, 46, 47, 50, 88, 51, 9, 49, 38, 67, 26, 65, 89, 27, 71, 25, 77, 72, 65, 41, 84, 68, 51, 26, 84, 24, 79, 41, 96, 83, 92, 9, 93, 84, 35, 70, 74, 79, 37, 38, 26, 26, 41, 26)))
  }

}
