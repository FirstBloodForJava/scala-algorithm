package com.oycm.algorithm.abc.min

object Solution_5 {

  /**
   * 2537. 统计好子数组的数目
   * https://leetcode.cn/problems/count-the-number-of-good-subarrays/description/
   *
   * 好子数组：一个子数组 arr 如果有 至少 k 对下标 (i, j) 满足 i < j 且 arr[i] == arr[j]
   *
   * @param nums 整数数组, n [1, 10 pow 5]
   * @param k    [1, 10 pow 9]
   * @return
   */
  def countGood(nums: Array[Int], k: Int): Long = {
    /*
    越短越合法，固定 l，找到 r，满足 [l, r] 子数组中下标对 恰好等于 k，则，固定 l 合法的子数组为 n - l
    下标对的数量 temp 怎么统计？
    用 哈希表 记录 元素出现的次数，cnt += map(num) - 1, 2 个才能构成一对
    l 右移动，怎么减少 cnt 的次数

    时间复杂度 O(n)
    空间复杂度 O(m) m 表示 nums 重复元素的数量
     */
    val n = nums.length
    val map = scala.collection.mutable.Map[Int, Int]()
    var ans = 0L
    var r = 0
    var cnt = 0
    for (l <- nums.indices) {
      while (r < n && cnt < k) {
        map(nums(r)) = map.getOrElse(nums(r), 0) + 1
        cnt += map(nums(r)) - 1
        r += 1
      }
      if (cnt >= k) {
        ans += n - r + 1
      }
      // l 右移动, 剩余数量大于 1，减 1 后就是要减少的数量
      map(nums(l)) -= 1
      if (map(nums(l)) > 0) {
        cnt -= map(nums(l))
      }
    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(countGood(Array(1, 1, 1, 1, 1), 10))
    println(countGood(Array(3, 1, 4, 3, 2, 2, 4), 2))
  }
}
