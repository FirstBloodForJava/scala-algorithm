package com.oycm.algorithm.aba.basic

import scala.collection.mutable

object Solution_6 {


  /**
   * 904.水果成篮 1516
   * https://leetcode.cn/problems/fruit-into-baskets/
   * fruits[i] >= 0 表示 第 i 棵树上 水果种类
   * 采摘谁要要求：
   *  - 只有 2 个篮子，每个篮子只能装 单一类型 的水果，水果总量没有限制
   *  - 可以选择任意一棵树开始采摘，必须从每棵树上采摘一个水果。采摘的水果应该符合篮子中的水果类型，每采摘一次，将移动到下一颗树，继续采摘
   *  - 一但走到某棵树前，但水果不符合篮子的水果类型，就必须停止采摘
   *
   * @param fruits
   * @return
   */
  def totalFruit(fruits: Array[Int]): Int = {
    var ans = 0
    // 问题本质就是寻找 连续子数组重复元素数量不超过 2 的最大长度
    // 记录出现的
    val map = mutable.Map[Int, Int]()
    var l = 0
    for (r <- fruits.indices) {
      map(fruits(r)) = map.getOrElse(fruits(r), 0) + 1
      while (map.size > 2) {
        map(fruits(l)) = map(fruits(l)) - 1
        if (map(fruits(l)) == 0) {
          map.remove(fruits(l))
        }
        l += 1
      }
      ans = Math.max(ans, r - l + 1)
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(totalFruit(Array(1, 2, 1)) == 3)
    println(totalFruit(Array(0, 1, 2, 2)) == 3)
    println(totalFruit(Array(1, 2, 3, 2, 2)) == 4)
    println(totalFruit(Array(3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4)) == 5)
  }
}
