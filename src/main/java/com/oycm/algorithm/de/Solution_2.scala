package com.oycm.algorithm.de

object Solution_2 {

  /**
   * 378. 有序矩阵中第 K 小的元素
   * https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
   *
   * @param matrix n x n 矩阵, 每行和每列元素均按升序排序
   * @param k      整数, [1, n pow 2]
   * @return 找到矩阵中第 k 小的元素
   */
  def kthSmallest(matrix: Array[Array[Int]], k: Int): Int = {
    /*
    题解思路：移除 k - 1 个元素，剩余的元素就是 第 k 小的元素
    使用 小顶堆 来维护最小的 数
    堆中元素使用 三元数组 值, 行, 列

    时间复杂度 (k log n)
    空间复杂度 O(n)
    */
    val n = matrix.length
    val heap = scala.collection.mutable.PriorityQueue[Array[Int]]()(Ordering.by(-_ (0)))
    for (i <- matrix.indices) {
      heap.addOne(Array(matrix(0)(i), 0, i))
    }
    for (i <- 0 until k - 1) {
      val min = heap.dequeue()
      if (min(1) != n - 1) {
        heap.addOne(Array(matrix(min(1) + 1)(min(2)), min(1) + 1, min(2)))
      }
    }
    heap.head(0)
  }

  def answer_2(matrix: Array[Array[Int]], k: Int): Int = {
    /*
    维护一个 [matrix(0)(0), matrix(n-1)(n-1)] 区间 数组 temp，计算 matrix 中 <= temp(i) 数量
    找到第一个 k <= temp(i) 的值，就是第 k 小的答案

    利用矩阵的有序性

    时间复杂度 O(n log (r - l))
    空间复杂度 O(1)
     */
    val n = matrix.length
    var l = matrix(0)(0)
    var r = matrix(n-1)(n-1)

    while (l <= r) {
      val mid = l + (r - l) / 2
      var cnt = 0

      var i = 0
      var j = n - 1
      while (i < n && j >= 0) {
        if (matrix(i)(j) > mid) {
          j -= 1
        } else {
          i += 1
          cnt += j + 1
        }
      }
      if (cnt >= k) {
        r = mid - 1
      } else {
        l = mid + 1
      }

    }
    l
  }

  def main(args: Array[String]): Unit = {
    println(kthSmallest(Array(Array(1, 5, 9), Array(10, 11, 13), Array(12, 13, 15)), 8))

    println(answer_2(Array(Array(1, 5, 9), Array(10, 11, 13), Array(12, 13, 15)), 8))
    println(answer_2(Array(Array(-5)), 1))

  }
}
