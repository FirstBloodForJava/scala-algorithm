package src.main.java.com.oycm.algorithm.db.min

object Solution_6 {

  /**
   * 3639. 变为活跃状态的最小时间 1853
   * https://leetcode.cn/problems/minimum-time-to-activate-string/description/
   *
   * 时间 t = 0 开始，在每个时间点，将字符串 s 中下标为 order[t] 的字符替换为 '*'。
   *  - 如果子字符串包含至少 一个 '*'，则该子字符串有效
   *  - 如果字符串有效 子字符串总数 大于等于 k，则称该字符串为活跃字符串
   *
   * @param s     长为 n 的字符串
   * @param order [0, n-1] 数字的 排列
   * @param k     目标值
   * @return 返回字符串变成活跃状态的最小时间，无法变成活跃状态则返回 -1
   */
  def minTime(s: String, order: Array[Int], k: Int): Int = {
    val n = s.length
    // n = 100000 时超阈值
    val subs = n.toLong * (n + 1) / 2
    if (k > subs) {
      return -1
    } else if (k == subs) {
      return n - 1
    }
    /*
    随着时间 t 右移，有效子字符串是递增的，可以对时间进行二分查找，找到 有效字符串数量大于等于 k 第一个的 index
    如果快速求更改后的 有效字符串数量
    */
    // 标记盖为 * 的字符
    val star = Array.fill(n)(0)

    var l = -1
    var r = n - 1
    while (l + 1 < r) {
      val mid = l + (r - l) / 2
      if (check(mid, k, order, star)) {
        r = mid
      } else {
        l = mid
      }
    }

    r
  }

  def check(mid: Int, target: Int, order: Array[Int], star: Array[Int]): Boolean = {
    var ans = false
    val m = mid + 1
    for (i <- 0 until m) {
      star(order(i)) = m
    }
    var cnt = 0
    // 计算 * 的子串数量
    var last = -1
    for (i <- order.indices if !ans) {
      if (star(i) == m) {
        last = i
      }
      cnt += last + 1
      if (cnt >= target) {
        ans = true
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(100000L * 100001 / 2)
  }

}
