package com.oycm.algorithm.abd

object Solution_1 {

  /**
   * 825.适龄的朋友 1697
   * https://leetcode.cn/problems/friends-of-appropriate-ages/description/
   * ages 数组表示 n 个用户，ages[i] 是第 i 个用户的年龄
   * 以下任意一个条件为真，那么用户 x 不会向 用户 y 发送好友请求：
   *  - ages[y] <= 0.5 * ages[x] + 7
   *  - ages[y] > ages[x]
   *  - ages[y] > 100 && ages[x] < 100
   *    否则 x 将会向 y 发送一条好友请求
   *    x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求
   *
   * @param ages
   * @return
   */
  def numFriendRequests(ages: Array[Int]): Int = {
    /*
    题解：表达式条件转换 + 转换成年龄滑动窗口
      x 对 y 发送消息满足全部的条件：
       - 0.5 * ages[x] + 7 < ages[y] <= ages[x]
       - ages[y] <= 100 || ages[x] >= 100，注意只要 ages[x] >= ages[y]，这个条件必定满足：x >= 100 时，不管y是何值都符合；x < 100，y < 100 符合
      对于这个年龄窗口，固定 ageX，当 [ageY, ageX] 符合要求时，x 可以和 区间中除了自己的所有人发消息，x 处的年龄人数 * (整个区间人数 - x处的年龄人数)
    */
    var ans = 0
    val count = Array.fill(121)(0)
    for (age <- ages) {
      count(age) += 1
    }
    var ageCount = 0
    var y = 0
    for (x <- count.indices) {
      ageCount += count(x)
      // 不符合要求
      while (y * 2 <= x + 14 && y <= x) {
        ageCount -= count(y)
        y += 1
      }
      /*
      当 x < 15 时 上面的循环肯定不成立
      思考：上面的循环为什么能换成 if
      */
      if (ageCount > 0) {
        ans += count(x) * (ageCount - 1)
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(numFriendRequests(Array(16, 16)))
    println(numFriendRequests(Array(16, 17, 18)))
    println(numFriendRequests(Array(20, 30, 100, 110, 120)))
  }

}
