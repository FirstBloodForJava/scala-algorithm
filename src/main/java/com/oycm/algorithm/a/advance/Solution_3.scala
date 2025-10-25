package com.oycm.algorithm.a.advance


object Solution_3 {

  /**
   * 3439.重新安排会议得到最多空余时间 I 1729
   * https://leetcode.cn/problems/reschedule-meetings-for-maximum-free-time-i/
   * 会议时间满足以下条件，会议时间不重叠：
   *  - endTime[i] <= startTime[i + 1] i 范围 [0, n - 2]
   * 重新移动 k 次会议，要求如下：
   *  - 移动前后的会议之间的相对顺序要保持一致，且会议时间不重叠
   *  - 会议不能安排在 eventTime 之外
   *  求重新安排会议后，求两个相邻会议之间的最大空余时间
   * @param eventTime 整数，活动的总时长，活动的时间范围 [0, eventTime]
   * @param k         整数，最多能重新安排的会议
   * @param startTime 长度为 n 的整数数组，会议开始时间
   * @param endTime   长度为 n 的整数数组，会议结束时间
   * @return
   */
  def maxFreeTime(eventTime: Int, k: Int, startTime: Array[Int], endTime: Array[Int]): Int = {
    var ans = 0
    val n = startTime.length
    // i in [1, n - 1] 标识第 i 个会议和第 i-1 个会议的空余时间 0 和 n 标识首尾的前后的空余时间
    // 可以用 函数来获取每个 i 个空闲时间，简化空间复杂度
    val free = Array.fill(n + 1)(0)
    free(0) = startTime(0)
    free(n) = eventTime - endTime(n - 1)
    // 求启动 至多 k 次的会议以获取最长的空余时间，就是求 free 中连续 k + 1 连续子数组的最大值
    var tempFree = 0
    for (i <- 0 to n) {
      if (i < n && i > 0) {
        free(i) = startTime(i) - endTime(i - 1)
      }
      // 入
      tempFree += free(i)
      // 形成子数组
      if (i - k >= 0) {
        ans = Math.max(ans, tempFree)
        // 出
        tempFree -= free(i - k)
      }

    }
    ans
  }

  // 空间优化：去掉数组
  def maxFreeTimeTripArray(eventTime: Int, k: Int, startTime: Array[Int], endTime: Array[Int]): Int = {
    var ans = 0
    val n = startTime.length
    // 求启动 至多 k 次的会议以获取最长的空余时间，就是求 free 中连续 k + 1 连续子数组的最大值
    var tempFree = 0
    for (i <- 0 to n) {
      // 入
      tempFree += get(i, eventTime, startTime, endTime)
      // 形成子数组
      if (i - k >= 0) {
        ans = Math.max(ans, tempFree)
        // 出
        tempFree -= get(i - k, eventTime, startTime, endTime)
      }

    }
    ans
  }
  private def get(i: Int, eventTime: Int, startTime: Array[Int], endTime: Array[Int]): Int = {
    if (i == 0) {
      startTime(0)
    } else if (i == startTime.length) {
      eventTime - endTime(i - 1)
    } else {
      startTime(i) - endTime(i - 1)
    }
  }

  def main(args: Array[String]): Unit = {
    println(maxFreeTime(5, 1, Array(1, 3), Array(2, 5)) == 2)
    println(maxFreeTimeTripArray(10, 1, Array(0, 2, 9), Array(1, 4, 10)) == 6)
    println(maxFreeTime(5, 2, Array(0, 1, 2, 3, 4), Array(1, 2, 3, 4, 5)) == 0)
  }
}
