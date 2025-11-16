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

  def main(args: Array[String]): Unit = {
    println(kthSmallest(Array(Array(1, 5, 9), Array(10, 11, 13), Array(12, 13, 15)), 8))
  }
}
