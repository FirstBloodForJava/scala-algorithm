package com.oycm.datastructure.heap.basic

object Solution_15 {

  /**
   * 1801. 积压订单中的订单总数 1711
   * https://leetcode.cn/problems/number-of-orders-in-the-backlog/description/
   *
   * @param orders
   * @return
   */
  def getNumberOfBacklogOrders(orders: Array[Array[Int]]): Int = {
    /*
    orders(i) = (price, amount, orderType)
    buy 查找 小顶堆 sell (金额, 数量) 订单
    sell 查找大顶堆 byd (金额, 数量) 订单
    */
    val maxBuy = scala.collection.mutable.PriorityQueue[Array[Int]]()(Ordering.by[Array[Int], Int](_.apply(0)))
    val minSell = scala.collection.mutable.PriorityQueue[Array[Int]]()(Ordering.by[Array[Int], Int](_.apply(0)).reverse)
    for (order <- orders) {
      var price = order(0)
      var amount = order(1)
      if (order(2) == 0) {
        // buy，查找 minSell
        while (minSell.nonEmpty && amount > 0 && price >= minSell.head(0) ) {
          val sell = minSell.head
          if (amount < sell(1)) {
            sell(1) = sell(1) - amount
            amount = 0
          } else {
            amount -= sell(1)
            minSell.dequeue()
          }
        }
        if (amount > 0) {
          maxBuy.addOne(Array(price, amount))
        }
      } else {
        // sell 查找 maxBuy
        while (maxBuy.nonEmpty && amount > 0 && price <= maxBuy.head(0) ) {
          val buy = maxBuy.head
          if (amount < buy(1)) {
            buy(1) = buy(1) - amount
            amount = 0
          } else {
            amount -= buy(1)
            maxBuy.dequeue()
          }
        }
        if (amount > 0) {
          minSell.addOne(Array(price, amount))
        }
      }
    }
    val mod = 1000000007
    var ans = 0L
    while (minSell.nonEmpty) {
      ans = (ans + minSell.dequeue()(1)) % mod
    }
    while (maxBuy.nonEmpty) {
      ans = (ans + maxBuy.dequeue()(1)) % mod
    }

    ans.toInt
  }

}
