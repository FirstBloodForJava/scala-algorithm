package com.oycm.algorithm.e.ec

object Solution_1 {

  /**
   * 3446. 按对角线进行矩阵排序
   * https://leetcode.cn/problems/sort-matrix-by-diagonals/description/
   *
   * 返回一个经过如下调整的矩阵：
   *  - 左下角三角形（包括中间对角线）的对角线按 非递增顺序 排序。
   *  - 右上角三角形 的对角线按 非递减顺序 排序。
   *
   * @param grid n x n 的整数方阵
   * @return
   */
  def sortMatrix(grid: Array[Array[Int]]): Array[Array[Int]] = {
    /*
    使用堆对对角线的元素排序
    grid(0) = [1,7,3]
    grid(1) = [9,8,2]
    grid(2) = [4,5,6]

    时间复杂度 O(n^2 log(n))
    空间复杂度 O(n)
    */
    // 小顶堆
    val minHeap = scala.collection.mutable.PriorityQueue.empty[Int](Ordering[Int].reverse)
    // 大顶堆
    val maxHeap = scala.collection.mutable.PriorityQueue[Int]()

    val n = grid.length
    for (k <- 0 to n - 2) {
      var i = k
      var j = 0
      while (i < n) {
        // 建堆
        maxHeap.addOne(grid(i)(j))
        if (k > 0) {
          minHeap.addOne(grid(j)(i))
        }
        i += 1
        j += 1
      }

      i = k
      j = 0
      while (i < n) {
        // 建堆
        grid(i)(j) = maxHeap.dequeue()
        if (k > 0) {
          grid(j)(i) = minHeap.dequeue()
        }
        i += 1
        j += 1
      }
    }
    grid
  }

  def main(args: Array[String]): Unit = {
    sortMatrix(Array(Array(1, 7, 3), Array(9, 8, 2), Array(4, 5, 6))).foreach(row => println(row.mkString("[", ", ", "]")))
  }

}
