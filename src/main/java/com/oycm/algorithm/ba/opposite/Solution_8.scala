package com.oycm.algorithm.ba.opposite

import sun.nio.cs.ext.MacHebrew

object Solution_8 {

  /**
   * 658. 找到 K 个最接近的元素
   * https://leetcode.cn/problems/find-k-closest-elements/description/
   * 从数组中找到最靠近 x 的 k 个数
   *
   * 整数 a 比整数 b 更接近 x 需要满足：
   *  - |a - x| < |b - x|
   *  - |a - x| == |b - x| 且 a < b
   *
   * 返回的结果必须要是按升序排好的
   *
   * @param arr 升序排序 的数组
   * @param k   整数 [1, n]
   * @param x   整数 [-10000, 10000]
   * @return
   */
  def findClosestElements(arr: Array[Int], k: Int, x: Int): List[Int] = {
    /*
    把 x 看成数轴中的一点
    arr(i) 的值也是数轴中的点，将 l = 0，r = n-1 和 x 进行比较，l 与 r 到 x 的距离
    x <= l 或 x >= r 就是从边界开始循环
    x 在 arr 中，可以找出最远点，从 [0, n-1] 从后面截取 [n-k, n-1] 再排序
    */
    val n = arr.length
    val ans = Array.fill(k)(0)
    if (x <= arr(0)) {
      for (i <- ans.indices) {
        ans(i) = arr(i)
      }
    } else if (x >= arr(n - 1)) {
      for (i <- ans.indices) {
        ans(i) = arr(n - k + i)
      }
    } else {
      var l = 0
      var r = n - 1
      var i = 0
      while (l <= r) {
        val a = Math.abs(arr(l) - x)
        val b = Math.abs(arr(r) - x)
        if (a > b) {
          if (i >= n - k) {
            ans((i + k) % n) = arr(l)
          }
          l += 1
        } else if (a <= b) {
          if (i >= n - k) {
            ans((i + k) % n) = arr(r)
          }
          r -= 1
        }
        i += 1
      }
      return ans.toList.sorted
    }
    ans.toList
  }

  def simple(arr: Array[Int], k: Int, x: Int): List[Int] = {
    /*
    题解：l = 0, r = n-1; a = arr(l), b = arr(r)
    排除距离较远的数，剩下的 k 个数就是答案
    1.循环条件 r - l + 1 > k，
    如果 a > b l++; 如果 a <= b r--
    */
    var l = 0
    var r = arr.length - 1
    // 里面还有 k 多元素
    while (r - l + 1 > k) {
      val a = Math.abs(arr(l) - x)
      val b = Math.abs(arr(r) - x)
      if (a > b) {
        l += 1
      } else {
        r -= 1
      }
    }
    arr.slice(l, r + 1).toList
  }

  def main(args: Array[String]): Unit = {
    println(findClosestElements(Array(1, 2, 3, 4, 5), 4, 3))
    println(findClosestElements(Array(1, 1, 2, 3, 4, 5), 4, -1))
    println(findClosestElements(Array(0, 1, 2, 2, 2, 3, 6, 8, 8, 9), 5, 9))

    println(simple(Array(1, 2, 3, 4, 5), 4, 3))
    println(simple(Array(1, 1, 2, 3, 4, 5), 4, -1))
    println(simple(Array(0, 1, 2, 2, 2, 3, 6, 8, 8, 9), 5, 9))
    println(simple(Array(0, 1, 2, 2, 2, 3, 6, 8, 8, 9), 1, 9))
  }
}
