package com.oycm.algorithm.a.advance

object Solution_5 {


  /**
   * 3652.按策略买卖股票的最佳时机
   * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-using-strategy/
   * 连续偶数天数 k，可以对策略 strategy 进行最多一次修改，求可获得的最大利润(strategy[i] * prices[i])
   * 一次修改：
   *  - 连续 k 个元素
   *  - 前 k/2 设为 0(持有)
   *  - 后 k/2 设为 1(卖出)
   *
   * @param prices   整数数组，表示第 i 天的股票价格
   * @param strategy 整数数组，表示第 i 天的交易策略，-1(买入)、0(持有)、1(卖出)
   * @param k        偶数整数
   * @return
   */
  def maxProfit(prices: Array[Int], strategy: Array[Int], k: Int): Long = {
    // method_1(prices, strategy, k)
    // 求被修改后的子数组 [i-k, i-1] 所增加的利润(最大增量)，增量计算
    // 前 k/2, [i-k,i-k/2-1], prices(i) * -strategy(i)
    // 后 k/2, [i-k/2, i-1] prices(i) * (1 - strategy(i))
    var ans: Long = 0
    var total: Long = 0
    // 计算 [0, k) 第一个窗口
    var temp: Long = 0
    for (i <- 0 until k / 2) {
      total += prices(i) * strategy(i)
      temp += prices(i) * -strategy(i)
    }
    for (i <- k / 2 until k) {
      total += prices(i) * strategy(i)
      temp += prices(i) * (1 - strategy(i))
    }
    ans = Math.max(temp, ans)

    // 下一个窗口形成时
    // i 进入 后 k/2：增加：prices(i) * (1 - strategy(i))
    // 中间点 i-k/2 从 后 k/2 移动到 前 k/2：减少 prices(i-k/2)，原因 -p*(1-s)+p*-s = -ps
    // i-k 离开 前 k/2 减少 (prices(i-k) * -strategy(i-k))
    for (i <- k until prices.length) {
      val p = prices(i)
      val s = strategy(i)
      total += p * s
      temp += p * (1 - s) - prices(i - k / 2) + prices(i - k) * strategy(i - k)
      ans = Math.max(temp, ans)
    }
    ans + total
  }

  /**
   * 前缀和
   */
  def method_1(prices: Array[Int], strategy: Array[Int], k: Int): Long = {
    val n = prices.length
    // 利润前缀和数组
    val sums = new Array[Long](n + 1)
    // 价格前缀和数组
    val priceSums = new Array[Long](n + 1)
    for (i <- 0 until n) {
      sums(i + 1) = sums(i) + prices(i) * strategy(i)
      priceSums(i + 1) = priceSums(i) + prices(i)
    }
    // 求修改 [i-k, i-1] 数组后的最大值。
    /**
     * [i-k,i-k/2) 前 k/2，持有，不用管；
     * [i-k/2,i-1] 后 k/2 卖出，priceSums 前缀和计算
     * 利润组成为：
     *  - [0, i-k-1] sums(i-k)
     *  - [i, n-1] sums(n) - sums(i)
     *  - [i-k/2,i-1]
     */
    var ans: Long = sums(n)
    for (i <- k to n) {
      val temp = sums(i - k) + (sums(n) - sums(i)) + (priceSums(i) - priceSums(i - k / 2))
      ans = Math.max(temp, ans)
    }
    ans
  }

  def main(args: Array[String]): Unit = {
    println(maxProfit(Array(4, 2, 8), Array(-1, 0, 1), 2) == 10)
    println(maxProfit(Array(5, 4, 3), Array(1, 1, 0), 2) == 9)
    println(maxProfit(Array(4, 7, 13), Array(-1, -1, 0), 2) == 9)
  }
}
