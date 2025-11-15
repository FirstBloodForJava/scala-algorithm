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

  def main(args: Array[String]): Unit = {
    println(smallestRange(Array(Array(4, 10, 15, 24, 26).toList, Array(0, 9, 12, 20).toList, Array(5, 18, 22, 30).toList).toList).toSeq)
    println(smallestRange(Array(Array(1, 2, 3).toList, Array(1, 2, 3).toList, Array(1, 2, 3).toList).toList).toSeq)
  }
}
