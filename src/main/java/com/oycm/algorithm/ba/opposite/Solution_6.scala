package com.oycm.algorithm.ba.opposite

object Solution_6 {

  /**
   * 2105. 给植物浇水 II 1507
   * https://leetcode.cn/problems/watering-plants-ii/
   *
   * plants 长度为 n 的正整数数组，plants[i] 表示一行植物中，第 i 株需要浇水的水量。
   *
   * Alice 和 Bob 每人有一个水罐，最初是满的 。按下面描述的方式完成浇水
   *  - Alice 按 从左到右（0开始） 的顺序给植物浇水。Bob 按 从右到左（n-1） 的顺序给植物浇水。他们 同时 给植物浇水。
   *  - 无论需要多少水，为每株植物浇水所需的时间都是相同的。
   *  - 如果 Alice/Bob 水罐中的水足以 完全 灌溉植物，他们 必须 给植物浇水。否则，他们 首先（立即）重新装满罐子，然后给植物浇水。
   *  - 如果 Alice 和 Bob 到达同一株植物，那么当前水罐中水 更多 的人会给这株植物浇水。如果他俩水量相同，那么 Alice 会给这株植物浇水。
   *
   * @param plants    长度为 n 的正整数数组
   * @param capacityA Alice 水罐的容量，最初是满的
   * @param capacityB Bob 水罐的容量，最初是满的
   * @return 返回两人浇灌所有植物过程中重新灌满水罐的 次数 。
   */
  def minimumRefill(plants: Array[Int], capacityA: Int, capacityB: Int): Int = {
    /*
    设 l 为 Alice 当前浇水的位置，设 r 为 Bob 当前浇水的位置
    同时浇水，意味着 l+1 的同时 r-1
    n 是偶数时，他们不会给同一株植物浇水，[0, 1, 2, 3] 循环退出时 l > r
    n 是奇数时，他们会给同一株植物浇水，[0, 1, 2, 3, 4] 循环退出时 l == r
    */
    var ans = 0
    val n = plants.length
    if (n == 1) {
      return ans
    }

    var l = 0
    var r = n - 1
    var cA = capacityA
    var cB = capacityB
    while (l < r) {
      if (cA >= plants(l)) {
        cA -= plants(l)
      } else {
        cA = capacityA - plants(l)
        ans += 1
      }
      if (cB >= plants(r)) {
        cB -= plants(r)
      } else {
        cB = capacityB - plants(r)
        ans += 1
      }
      l += 1
      r -= 1
    }
    // 给同一株浇水
    if (l == r) {
      if (cA < plants(l) && cB < plants(l)) {
        ans += 1
      }
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(minimumRefill(Array(2,2,3,3), 5, 5) == 1)
    println(minimumRefill(Array(2,2,3,3), 3, 4) == 2)
    println(minimumRefill(Array(5), 10, 8) == 0)
  }
}
