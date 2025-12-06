package src.main.java.com.oycm.datastructure.heap.basic

import scala.util.control.Breaks.{break, breakable}

object Solution_12 {

  /**
   * 2233. K 次增加后的最大乘积 1686
   * https://leetcode.cn/problems/maximum-product-after-k-increments/description/
   *
   * 每次操作, 选择 nums 中 任一 元素并将它 增加 1
   *
   * @param nums
   * @param k
   * @return 求 至多 k 次操作， nums 的最大乘积， 结果取模
   */
  def maximumProduct(nums: Array[Int], k: Int): Int = {
    /*
    a * b
      a+1 = a * b * (a+1) / a
      b+1 = a * b * (b+1) / b
    要想乘积最大，就是 x+1/x 最大，x 越小这个值越大
    小顶堆
    */
    val mod = 1000000007
    heapify(nums)
    var ans = 1L
    var temp = k
    while (temp > 0) {
      nums(0) = nums(0) + 1
      sink(nums, 0)
      temp -= 1
    }
    for (num <- nums) {
      // 溢出考虑
      ans = ans * num % mod
    }
    ans.toInt
  }

  def heapify(h: Array[Int]): Unit = {
    for (i <- h.length / 2 - 1 to 0 by -1) {
      sink(h, i)
    }
  }

  def sink(h: Array[Int], i: Int): Unit = {
    val n = h.length
    breakable {
      var parent = i
      while (2 * parent + 1 < n) {
        var l = 2 * parent + 1
        if (l + 1 < n && h(l + 1) < h(l)) {
          l += 1
        }
        if (h(parent) <= h(l)) {
          break()
        }
        swap(h, l, parent)
        parent = l
      }
    }
  }

  def swap(h: Array[Int], i: Int, j: Int): Unit = {
    val temp = h(i)
    h(i) = h(j)
    h(j) = temp
  }

  def main(args: Array[String]): Unit = {
    println(maximumProduct(Array(24, 5, 64, 53, 26, 38), 54))
  }
}
