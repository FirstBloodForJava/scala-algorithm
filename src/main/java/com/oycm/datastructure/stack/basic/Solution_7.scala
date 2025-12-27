package com.oycm.datastructure.stack.basic

object Solution_7 {

  /**
   * 3412. 计算字符串的镜像分数 1578
   * https://leetcode.cn/problems/find-mirror-score-of-a-string/description/
   *
   * 字母镜像：反转字母表之后对应位置上的字母，'a' 的镜像是 'z'
   *
   * 字符串 s 的初始分数为 0，执行以下过程计算分数：
   *  - 从左到右遍历字符串
   *  - 对于每个下标 i ，找到距离最近的 未标记 下标 j，j < i, s(j) 是 s(i) 的镜像。然后 标记 下标 i 和 j，总分加上 i - j
   *  - 如果对于下标 i，不存在满足条件的下标 j，则跳过该下标，继续处理下一个下标，不需要进行标记。
   *
   * @param s 字符串
   * @return
   */
  def calculateScore(s: String): Long = {
    /*
    栈记录相同字符的 index，先进后出
    */
    var ans = 0L
    val map = Array.fill(26)(scala.collection.mutable.Stack[Int]())

    for (i <- s.indices) {
      if (map('z' - s(i)).nonEmpty) {
        ans += i - map('z' - s(i)).pop()
      } else {
        map(s(i) - 'a').push(i)
      }

    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(calculateScore("aczzx"))
    println(calculateScore("eockppxdqclkhjgvnw"))
  }
}
