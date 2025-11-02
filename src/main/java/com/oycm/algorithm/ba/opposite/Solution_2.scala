package com.oycm.algorithm.ba.opposite

object Solution_2 {

  /**
   * 3643.垂直翻转子矩阵 1235
   * https://leetcode.cn/problems/flip-square-submatrix-vertically/
   * 垂直翻转子矩阵的行顺序
   *
   * @param grid 整形二维数组，整形矩阵
   * @param x    外层数组下标
   * @param y    内层数组下标
   * @param k    正整数，以 (x, y) 为左上角子矩阵的边长
   * @return
   */
  def reverseSubmatrix(grid: Array[Array[Int]], x: Int, y: Int, k: Int): Array[Array[Int]] = {
    /*
    题目的意思：外层数组就是垂直方向，调换规则如下
    [x, y]-> [x, y+k-1] 和 [x+k-1,y] -> [x+k-1, y+k-1] 之间的行数据翻转
    */
    var ans = grid

    var l = x
    var r = x + k - 1
    while (l < r) {
      // (x,y) 和 (x+k-1, y) 交换
      var y1 = y
      while (y1 < y + k) {
        var temp = grid(l)(y1)
        grid(l)(y1) = grid(r)(y1)
        grid(r)(y1) = temp
        y1 += 1
      }
      l += 1
      r -= 1
    }
    ans
  }
}
