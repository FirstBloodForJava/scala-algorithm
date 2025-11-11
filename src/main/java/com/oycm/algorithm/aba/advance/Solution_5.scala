package com.oycm.algorithm.aba.advance

object Solution_5 {

  /**
   * 2831. 找出最长等值子数组 1976
   * https://leetcode.cn/problems/find-the-longest-equal-subarray/description/
   *
   * 等值子数组：子数组中所有元素都相等
   *
   * 从 nums 中删除最多 k 个元素后，返回可能的最长等值子数组的长度。
   *
   * @param nums 长度为 n 的整数, n [1, 10 pow 5], nums(i) [1, n]
   * @param k    [0, n]
   * @return
   */
  def longestEqualSubarray(nums: List[Int], k: Int): Int = {
    /*
    求 1,1,1,1,2,1,3,1 不同元素数量不超过 k + 1 的最长子数组，计算其中最多相同元素的数量
    逻辑有问题：4, 4, 2, 2, 4

    时间复杂度 O(n * k)
     */
    var ans = 0
    // 用来记录相同元素
    var same = nums.head
    val map = scala.collection.mutable.Map[Int, Int]()
    var l = 0
    for (r <- nums.indices) {
      map(nums(r)) = map.getOrElse(nums(r), 0) + 1
      if (map(nums(r)) >= map(same)) {
        same = nums(r)
      }
      while (map.size > k + 1) {
        map(nums(l)) -= 1
        if (map(nums(l)) == 0) {
          map.remove(nums(l))
          if (nums(l) == same) {
            // 重新计算最大值 same
            var temp = 0
            for ((k, v) <- map) {
              if (v > temp) {
                same = k
                temp = v
              }
            }
          }
        }
        l += 1
      }
      ans = Math.max(ans, map(same))
    }
    ans
  }

  def answer(nums: List[Int], k: Int): Int = {
    /*
    假设可以找到数组中每个不同元素构建的 最长等值子数组，即可找到全局最大值。
    可以对数组中的元素进行分类，然后依次枚举出 每个不同元素构成的最长等值子数组长度。

    假设 [l, r] 区间是一个符合要求等值子数组，x 为等值，cnt[x] 为等值出现的次数，需要删除元素的数量为 r - l + 1 - cnt[x]
    可以维护相同元素的一个哈希表，key 表示等值元素，value 是 pos 集合，存储相同元素的下标
    原子数组长度 (pos(r) - pos(l) + 1), 相同元素次数 (r - l + 1) 当 (pos(r) - pos(l)) - (r - l) > k 时，右移 l
    pos 的 r - l + 1 就是答案

    时间复杂度 O(n)
    空间复杂度 O(n)
    scala 运行超时
    */
    var ans = 0
    val map = scala.collection.mutable.Map[Int, scala.collection.mutable.ArrayBuffer[Int]]()
    for (i <- nums.indices) {
      if (!map.contains(nums(i))) {
        map(nums(i)) = scala.collection.mutable.ArrayBuffer[Int]()
      }
      map(nums(i)).append(i)
    }

    for (v <- map.values if v.size > ans) {

      var l = 0
      for (r <- v.indices) {
        while (v(r) - v(l) - r + l > k) {
          l += 1
        }
        ans = Math.max(ans, r - l + 1)
      }
    }

    ans
  }

  def answerOptimize(nums: List[Int], k: Int): Int = {
    var ans = 0

    val map = scala.collection.mutable.Map[Int,Int]()
    var l = 0
    for (r <- nums.indices) {
      // map 记录同值元素数量
      map(nums(r)) = map.getOrElse(nums(r), 0) + 1
      // 以 l 为同值元素的 起始点
      while (r - l + 1 - map(nums(l)) > k) {
        map(nums(l)) -= 1
        l += 1
      }
      ans = Math.max(ans, map(nums(r)))
    }
    ans
  }


  def main(args: Array[String]): Unit = {
    println(longestEqualSubarray(Array(4, 4, 2, 2, 4).toList, 1))
    println(longestEqualSubarray(Array(1, 3, 2, 3, 1, 3).toList, 3))
    println(longestEqualSubarray(Array(1, 1, 2, 2, 1, 1).toList, 2))

    println(answer(Array(4, 4, 2, 2, 4).toList, 1) == 2)
    println(answer(Array(1, 3, 2, 3, 1, 3).toList, 2) == 3)
    println(answer(Array(1, 1, 2, 2, 1, 1).toList, 2) == 4)

    println(answerOptimize(Array(4, 4, 2, 2, 4).toList, 1) == 2)
    println(answerOptimize(Array(1, 3, 2, 3, 1, 3).toList, 2) == 3)
    println(answerOptimize(Array(1, 1, 2, 2, 1, 1).toList, 2) == 4)
  }
}
