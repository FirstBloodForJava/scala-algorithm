package com.oycm.algorithm.e.enum_diagonal

object Solution_2 {

  /**
   * 2711. 对角线上不同值的数量差 1429
   * https://leetcode.cn/problems/difference-of-number-of-distinct-values-on-diagonals/description/
   *
   * 以下面方式得到一个新的 grid：
   *  - topLeft[r][c] 为矩阵 grid 中单元格 (r, c) 对角线 左上角 不同值的数量
   *  - bottomRight[r][c] 为矩阵 grid 中单元格 (r, c) 对角线 右上角 不同值的数量
   *
   * @param grid m x n 的二维矩阵
   * @return
   */
  def differenceOfDistinctValues(grid: Array[Array[Int]]): Array[Array[Int]] = {
    /*
    暴力计算
    */
    val m = grid.length
    val n = grid(0).length
    val ans = Array.fill(m)(Array.fill(n)(0))

    val set = scala.collection.mutable.Set[Int]()
    for (i <- 0 until m) {
      for (j <- 0 until n) {
        // 计算该对角线左上角 不同元素数量
        var x = i - 1
        var y = j - 1
        while (x >= 0 && y >= 0) {
          set.add(grid(x)(y))
          x -= 1
          y -= 1
        }
        val topLeft = set.size
        set.clear()
        x = i + 1
        y = j + 1
        while (x < m && y < n) {
          set.add(grid(x)(y))
          x += 1
          y += 1
        }
        ans(i)(j) = Math.abs(set.size - topLeft)
        set.clear()
      }

    }

    ans
  }

}
