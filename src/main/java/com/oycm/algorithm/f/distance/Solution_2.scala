package com.oycm.algorithm.f.distance

object Solution_2 {

  /**
   * 2615. 等值距离和 1793
   * https://leetcode.cn/problems/sum-of-distances/description/
   *
   * 对于 nums(i), arr(i) 表示所有 nums(i) == nums(j) 且 i != j 所有 |i - j| 之和
   *
   * @param nums 正整数数组
   * @return
   */
  def distance(nums: Array[Int]): Array[Long] = {
    /*
    维护一个相同值的 index 数组, 对 index 计算前缀和
    就算距离和 小于 当前 index 是 preCnt * val - preSum + sufSum - (n - preCnt) * val
    必须要知道当前 元素再 index 数组的下标
    */
    val arr = Array.fill(nums.length)(0L)
    val map = scala.collection.mutable.Map[Int, scala.collection.mutable.ArrayBuffer[Long]]()
    for (i <- nums.indices) {
      var sums = map.getOrElse(nums(i), null)
      if (sums == null) {
        sums = scala.collection.mutable.ArrayBuffer[Long](0L)
        map(nums(i)) = sums
      }
      sums.addOne(sums.last + i)
    }
    // 计算答案
    // 记录 当前元素下标
    val cnt = scala.collection.mutable.Map[Int, Int]()
    for (i <- nums.indices if map(nums(i)).size > 2) {
      // 至少要有两个相同元素
      val num = nums(i)
      cnt(num) = cnt.getOrElse(num, -1) + 1
      val sums = map(num)
      // 乘法考虑溢出
      val pre = cnt(num) * i.toLong - sums(cnt(num))
      val suf = sums.last - sums(cnt(num)) - (sums.size - 1 - cnt(num)) * i.toLong
      arr(i) = pre + suf
    }

    arr
  }

  def main(args: Array[String]): Unit = {
    println(distance(Array(1, 3, 1, 1, 2)).toSeq)
    println(distance(Array(0, 5, 3)).toSeq)
  }

}
