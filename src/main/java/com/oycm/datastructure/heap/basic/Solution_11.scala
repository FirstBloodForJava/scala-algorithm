package com.oycm.datastructure.heap.basic

object Solution_11 {

  /**
   * 2208. 将数组和减半的最少操作次数 1550
   * https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/description/
   *
   * 每一次操作中，你可以从 nums 中选择 任意 一个数并将它减小到 恰好 一半
   *
   * @param nums
   * @return 返回将 nums 数组和 至少 减少一半的 最少 操作数
   */
  def halveArray(nums: Array[Int]): Int = {
    /*
    构建一个浮点数大顶堆的 可以解决问题
    */
    val heap = scala.collection.mutable.PriorityQueue[Double]()
    var sum = 0.0
    for (elem <- nums) {
      heap.addOne(elem.toDouble)
      sum += elem
    }
    sum = sum / 2
    var ans = 0
    var sum2 = 0.0
    while (sum2 < sum) {
      var poll = heap.dequeue() / 2
      sum2 += poll
      heap.addOne(poll)
      ans += 1
    }
    ans
  }

}
