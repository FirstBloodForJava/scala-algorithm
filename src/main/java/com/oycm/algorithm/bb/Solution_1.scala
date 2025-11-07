package com.oycm.algorithm.bb

object Solution_1 {

  /**
   * 2109. 向字符串添加空格
   * https://leetcode.cn/problems/adding-spaces-to-a-string/description/
   *
   * @param s      字符串，长度记为 n
   * @param spaces 严格递增的整数数组, 范围 [0, s.length - 1]，长度记为 m
   * @return
   */
  def addSpaces(s: String, spaces: Array[Int]): String = {
    /*
    截取 0, spaces(0) 作为开始 字符串，进入下面的循环，i < m - 1
    添加空格，截取 spaces(0), spaces(1)，就是在 spaces(0) 前添加空格的字符
    添加空格，截取 spaces(1), spaces(2)，就是在 spaces(1) 前添加空格的字符
    ...
    添加空格，截取 spaces(m-2), spaces(m-1)，就是在 spaces(m-2) 前添加空格的字符
    循环结束
    添加空格，截取 spaces(m-1), n，就是在 spaces(m-1) 前添加空格的字符

    时间复杂度 O(n)
    空间复杂度
     */
    val n = s.length
    val m = spaces.length
    // 不能直接使用字符串拼接
    val ans = new StringBuilder(s.substring(0, spaces(0)))
    for (i <- spaces.indices if i < m - 1) {
      ans.append(" ")
      ans.append(s.substring(spaces(i), spaces(i + 1)))
    }
    ans.append(" ")
    ans.append(s.substring(spaces(m - 1), n))
    ans.toString
  }

  def doublePoint(s: String, spaces: Array[Int]): String = {
    /*
    初始化 j = 0, 遍历 s，如果 j < m && i == spaces(j) j++ 先添加空格，再追加字符
     */
    val ans = new StringBuilder()
    var j = 0
    for (i <- s.indices) {
      if (j < spaces.length && i == spaces(j)) {
        ans.append(" ")
        j += 1
      }
      ans.append(s(i))
    }

    ans.toString
  }

  def main(args: Array[String]): Unit = {
    println(addSpaces("LeetcodeHelpsMeLearn", Array(8, 13, 15)) == "Leetcode Helps Me Learn")
    println(addSpaces("icodeinpython", Array(1, 5, 7, 9)) == "i code in py thon")
    println(addSpaces("spacing", Array(0, 1, 2, 3, 4, 5, 6)) == " s p a c i n g")

    println(doublePoint("LeetcodeHelpsMeLearn", Array(8, 13, 15)) == "Leetcode Helps Me Learn")
    println(doublePoint("icodeinpython", Array(1, 5, 7, 9)) == "i code in py thon")
    println(doublePoint("spacing", Array(0, 1, 2, 3, 4, 5, 6)) == " s p a c i n g")
  }
}
