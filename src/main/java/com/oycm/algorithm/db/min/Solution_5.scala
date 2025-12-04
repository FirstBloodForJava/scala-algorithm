package com.oycm.algorithm.db.min


object Solution_5 {

  /**
   * 3296. 移山所需的最少秒数 1695
   * https://leetcode.cn/problems/minimum-number-of-seconds-to-make-mountain-height-zero/description/
   *
   * 工人们需要 同时 进行工作以 降低 山的高度。对于工人 i：
   *  - 山的高度降低 x，需要花费 workerTimes[i] + workerTimes[i] * 2 + ... + workerTimes[i] * x 秒
   *
   * 工人们使山的高度降低到 0 所需的 最少 秒数
   *
   * @param mountainHeight 表示山的高度
   * @param workerTimes    表示工人的工作时间
   * @return
   */
  def minNumberOfSeconds(mountainHeight: Int, workerTimes: Array[Int]): Long = {
    /*
    工人降低 h 需要花费的时间 t = workerTime * (1 + 2 + .. + h) = workerTime * h * (h+1) / 2
    由于工人是有限的，且同时工作，时间 和 工人能降低山的高度 成正比，可间接查询 >= mountainHeight 的第一个 index
    最大时间 max(workerTime) * h * (h+1) / 2
    怎么求 h * (h + 1) / 2 * workerTime <= time 这个表达式 h 的最大值，可二分
     */
    var max = 0
    var l = 0L
    for (elem <- workerTimes) {
      max = Math.max(max, elem)
    }
    var r = max.toLong * mountainHeight * (mountainHeight + 1) / 2 + 1

    // 求给定 time，所有功能能降低的山高度
    def getH(nums: Array[Int], time: Long): Long = {
      var h = 0L
      for (num <- nums) {
        var left = 0L
        var right = time * 2 / num
        while (left + 1 < right) {
          val mid = left + (right - left) / 2
          if (mid * (mid + 1) / 2 * num <= time) {
            left = mid
          } else {
            right = mid
          }
        }
        h += left
      }

      h
    }

    while (l + 1 < r) {
      val mid = l + (r - l) / 2
      if (getH(workerTimes, mid) >= mountainHeight) {
        r = mid
      } else {
        l = mid
      }

    }

    r
  }

  def main(args: Array[String]): Unit = {
    //    println(minNumberOfSeconds(4, Array(2, 1, 1)))
    //    println(minNumberOfSeconds(10, Array(3, 2, 2, 4)))
    //    println(minNumberOfSeconds(5, Array(1)))
    println(minNumberOfSeconds(100000, Array(1)))
  }
}
