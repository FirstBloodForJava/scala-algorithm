package com.oycm.algorithm.ba.opposite

object Solution_9 {

  /**
   * 1471. 数组中的 K 个最强值
   * https://leetcode.cn/problems/the-k-strongest-values-in-an-array/
   *
   * 中位数：升序的数组 arr，数组列表长为 n，中位数为 arr[(n-1)/2] 当 n 是偶数时，向下取整。
   *
   * 设 m 为数组 arr 的中位数，只要满足下述两个前提之一，就可以判定 arr[i] 的值比 arr[j] 的值更强：
   *  - |arr[i] - m| > |arr[j] - m|
   *  - |arr[i] - m| == |arr[j] - m|，且 arr[i] > arr[j]
   *
   * 返回由数组中最强的 k 个值组成的列表。答案可以以 任意顺序 返回。
   *
   * -100 - 9 100
   *
   * @param arr 整数数组
   * @param k   整数 k
   * @return
   */
  def getStrongest(arr: Array[Int], k: Int): Array[Int] = {
    /*
     当 i > j 时, |i - m| > |j - m| 则仅 i > m 且 (i > j || i + j >= 2m)
     当 i < j 时，|i - m| > |j - m| 则 -i > -j

     把 m 当成 数轴的一个点，对 arr 排序后，l 表示 0 开始的左边，r 表示 n - 1 开始的右边
     如果 |l - m| > |r - m| 则 记录 l, l++
     如果 |l - m| > |r - m| 则 记录 r, r--
     如果 |l - m| == |r - m| 则记录 r, r--
     */
    val n = arr.length
    var l = 0
    var r = n - 1
    var i = 0
    val ans = Array.fill(k)(0)
    val sort = arr.sorted
    val m = sort((n - 1) / 2)
    while (l <= r && i < k) {

      if (Math.abs(sort(r) - m) > Math.abs(sort(l) - m)) {
        ans(i) = sort(r)
        r -= 1
      } else if (Math.abs(sort(r) - m) < Math.abs(sort(l) - m)) {
        ans(i) = sort(l)
        l += 1
      } else {
        ans(i) = sort(r)
        r -= 1
      }
      i += 1

    }

    ans
  }

  def main(args: Array[String]): Unit = {
    println(getStrongest(Array(1, 2, 3, 4, 5), 2).toSeq)
    println(getStrongest(Array(1, 1, 3, 5, 5), 2).toSeq)
    println(getStrongest(Array(6, 7, 11, 7, 6, 8), 5).toSeq)
    println(getStrongest(Array(6, 7, 11, 7, 6, 8), 6).toSeq)
  }
}
