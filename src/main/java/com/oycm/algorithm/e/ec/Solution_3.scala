package com.oycm.algorithm.e.ec

object Solution_3 {

  /**
   * 1329. 将矩阵按对角线排序 1548
   * https://leetcode.cn/problems/sort-the-matrix-diagonally/description/
   *
   * @param mat m * n 的整数矩阵
   * @return 求 矩阵对角线 上的元素按升序排序后 的矩阵
   */
  def diagonalSort(mat: Array[Array[Int]]): Array[Array[Int]] = {
    /*
    时间复杂度 O(mn log (min(m, n)))
    空间复杂度 O(min(m, n))
    */
    val m = mat.length
    val n = mat(0).length
    val minHeap = scala.collection.mutable.PriorityQueue.empty[Int](Ordering[Int].reverse)
    // 分两次遍历 排对角线
    for (k <- 0 until m - 1) {
      var i = k
      var j = 0
      while (i < m && j < n) {
        minHeap.addOne(mat(i)(j))
        i += 1
        j += 1
      }
      i = k
      j = 0
      while (i < m && j < n) {
        mat(i)(j) = minHeap.dequeue()
        i += 1
        j += 1
      }
    }

    // 分两次遍历 列对角线
    for (k <- 1 until n - 1) {
      var i = 0
      var j = k
      while (i < m && j < n) {
        minHeap.addOne(mat(i)(j))
        i += 1
        j += 1
      }
      i = 0
      j = k
      while (i < m && j < n) {
        mat(i)(j) = minHeap.dequeue()
        i += 1
        j += 1
      }
    }

    mat
  }

  def main(args: Array[String]): Unit = {
    diagonalSort(Array(
      Array(11, 25, 66, 1, 69, 7),
      Array(23, 55, 17, 45, 15, 52),
      Array(75, 31, 36, 44, 58, 8),
      Array(22, 27, 33, 25, 68, 4)))
      .foreach(row => println(row.mkString("[", ", ", "]")))
  }

}
