package com.oycm.algorithm.abb.basic

object Solution_6 {

  /**
   * 632. 最小区间
   * https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/
   *
   * 区间大小定义：b-a < d-c 或 b-a == d-c, 当 a < c 时, 区间 [a,b] 比 [c,d] 小
   *
   * 到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
   *
   * @param nums 长为 k 的 非递减排列 的整数列表
   * @return
   */
  def smallestRange(nums: List[List[Int]]): Array[Int] = {
    /*
    题解：
    先求出 最左边的 合法区间，然后去掉最小值 nums(i)(0)，再计算当前 最左边的 合法区间，如果该区间更短，则更新答案
    不断循环，知道某个 nums 中 集合元素为空

    左端点最小值，每次循环之后还需要删除最小值、插入元素、更新最小值，可以使用堆维护
    右端点最大值，可以记录第一次的最大值，删除最左边最小值后，新加入的值和其相比，更大则更新

    堆中需要知道当前值, nums 下标, 当前所在元素下标, 可以使用长为 3 的数组来维护
    记 n 为 nums 长度, L 为 nums 所有 nums(i) 长度之和
    时间复杂度 O(n log L)
    空间复杂度 O(n)
    */
    val heap = scala.collection.mutable.PriorityQueue[Array[Int]]()(Ordering.by(-_ (0)))
    // 最大值 和 小顶堆初始化
    var r = Int.MinValue
    for (i <- nums.indices) {
      val num = Array(nums(i).head, i, 0)
      heap.addOne(num)
      r = Math.max(r, nums(i).head)
    }
    val ans = Array(heap.head(0), r)
    // 数组中还有元素
    while (heap.head(2) + 1 < nums(heap.head(1)).size) {
      val min = heap.dequeue()
      val next = Array(nums(min(1))(min(2) + 1), min(1), min(2) + 1)
      heap.addOne(next)
      r = Math.max(r, next(0))
      if (r - heap.head(0) < ans(1) - ans(0)) {
        ans(0) = heap.head(0)
        ans(1) = r
      }
    }
    ans
  }

  def answer_2(nums: List[List[Int]]): Array[Int] = {
    /*
    题解思路： nums 长度记为 k
    用一个二维数组 记录 nums(i)(j) 和 i 的关系，问题就转换成了 pairs = Array[Array[Int]] 数组中找到一个最小区间, 包含 [0,k) 所有编号
    问题就转换成了 76 最小子串覆盖问题
    要找的区间记为 [l, r]
    组成的新数组 pairs 的要挑选出 一堆 pairs(i)(1) 包含 [0,k) 所有编号，l 就是里面的最小值，r 就是里面的最大值
    元素的顺序和 结果没有关系，可以对 pairs 进行排序 当找到 [l, r] 符合要求时，不断缩短 l，不和要求时，不算右移 r，可以使用滑动窗口解决

    用一个长 k = nums.length 数组 cnt 记录次数
    empty 表示需要多少编号，当为 0 时，则更新答案
    cnt[i] 减少后等于 0 表示已添加一个编号
    l 右移过程中 cnt[l] == 1, 表示已有编号被删除了

    记 L 为 nums 所有整数的数量
    时间复杂度 O(L log L), 在排序
    空间复杂度 O(L)
    */
    var length = 0
    for (num <- nums) {
      length += num.size
    }
    var pairs = Array.fill(length)(Array.fill(2)(0))
    var j = 0
    for (i <- nums.indices) {
      for (num <- nums(i)) {
        pairs(j)(0) = num
        pairs(j)(1) = i
        j += 1
      }
    }
    pairs = pairs.sortBy(_(0))
    val ans = Array(pairs(0)(0), pairs(length - 1)(0))

    var empty = nums.size
    val cnt = Array.fill(empty)(1)
    var l = 0
    for (r <- pairs.indices) {
      cnt(pairs(r)(1)) -= 1
      if (cnt(pairs(r)(1)) == 0) {
        empty -= 1
      }
      while (empty == 0) {
        if (pairs(r)(0) - pairs(l)(0) < ans(1) - ans(0)) {
          ans(0) = pairs(l)(0)
          ans(1) = pairs(r)(0)
        }
        // 出 l
        cnt(pairs(l)(1)) += 1
        if (cnt(pairs(l)(1)) == 1) {
          empty += 1
        }
        l += 1
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(smallestRange(Array(Array(4, 10, 15, 24, 26).toList, Array(0, 9, 12, 20).toList, Array(5, 18, 22, 30).toList).toList).toSeq)
    println(smallestRange(Array(Array(1, 2, 3).toList, Array(1, 2, 3).toList, Array(1, 2, 3).toList).toList).toSeq)

    println(answer_2(Array(Array(4, 10, 15, 24, 26).toList, Array(0, 9, 12, 20).toList, Array(5, 18, 22, 30).toList).toList).toSeq)
    println(answer_2(Array(Array(1, 2, 3).toList, Array(1, 2, 3).toList, Array(1, 2, 3).toList).toList).toSeq)
  }
}
