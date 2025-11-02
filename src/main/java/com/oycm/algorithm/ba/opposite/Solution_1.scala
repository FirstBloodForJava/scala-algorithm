package com.oycm.algorithm.ba.opposite

object Solution_1 {

  /**
   * 344.反转字符串
   * https://leetcode.cn/problems/reverse-string/description/
   * @param s
   */
  def reverseString(s: Array[Char]): Unit = {
    /*
    n-1 和 0 交换
    n-2 和 1 交换
    ...
    */
    var l = 0
    var r = s.length - 1
    while (l < r) {
      val temp = s(r)
      s(r) = s(l)
      s(l) = temp
      l += 1
      r -= 1
    }
  }

}
