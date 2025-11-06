package com.oycm.algorithm.ba.opposite

object Solution_27 {

  /**
   * 581. 最短无序连续子数组
   * https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/description/
   *
   * 找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序
   *
   * @param nums 整数数组 nums
   * @return 求 最短子数组 的长度
   */
  def findUnsortedSubarray(nums: Array[Int]): Int = {
    /*
    最短无序连续子数组
    左边升序子数组的最大值 要小于等于 无序子数组的最小值
    右边升序子数组的最小值 要大于等于 无序子数组的最大值
    时间复杂度 O(3n)
    空间复杂度 O(n)
     */
    val ans = 0
    val n = nums.length
    if (n == 1) {
      return ans
    }
    val ascStack = scala.collection.mutable.Stack[Int]()
    ascStack.push(nums(0))
    var push = true
    for (i <- 1 until n if ascStack.nonEmpty) {
      // last 是栈底, head 是栈顶
      while (ascStack.nonEmpty && ascStack.head > nums(i)) {
        if (push) {
          push = false
        }
        ascStack.pop()
      }
      if (push) {
        ascStack.push(nums(i))
      }
    }
    if (ascStack.size == n) {
      return ans
    }
    val descStack = scala.collection.mutable.Stack[Int]()
    descStack.push(nums(n - 1))
    push = true
    for (i <- n - 2 to 0 by -1 if descStack.nonEmpty) {
      while (descStack.nonEmpty && nums(i) > descStack.head) {
        if (push) {
          push = false
        }
        descStack.pop()
      }
      if (push) {
        descStack.push(nums(i))
      }
    }

    n - ascStack.size - descStack.size
  }

  def optimize(nums: Array[Int]): Int = {
    /*
    优化时间复杂度和空间复杂度
    A 表示左边升序子数组，B 表示中间无序子数组 [l, r]，C 表示右边升序子数组
    题解：
    B 和 C 中的任意一个数都 大于等于 A 中的任意一个数，即 nums(i) <= min(nums(j)) j in [i+1, n)
    从大到小枚举 i，min 记录最小值 min(nums(i))，每次移动，都能 O(1) 的更新 min，最后一个不等式成立的 i 就是 l
    同理
    A 和 B 中的任意一个数都 小于等于 C 中的任意一个数，即 max(num(j)) <= nums(i) j in [j+1, n)，
    从小到大枚举 j，max 记录最大值 max(nums(j))，每次移动，都能 O(1) 的更新 max，最后一个不等式成立的 j 就是 r
    特殊情况处理 数组就是有序的
     */
    var ans = 0
    val n = nums.length
    var l = -1
    var min = Int.MaxValue

    var r = -1
    var max = Int.MinValue
    for (i <- nums.indices) {
      // 从小到大
      if (max > nums(i)) {
        r = i
      } else {
        max = nums(i)
      }
      // 从大到小
      if (min < nums(n - 1 - i)) {
        l = n - 1 - i
      } else {
        min = nums(n - 1 - i)
      }

    }
    if (l != -1) {
      ans = r - l + 1
    }
    ans
  }


  def main(args: Array[String]): Unit = {
    println(findUnsortedSubarray(Array(2, 6, 4, 8, 10, 9, 15)) == 5)
    println(findUnsortedSubarray(Array(1, 2, 3, 4)) == 0)
    println(findUnsortedSubarray(Array(1)) == 0)
    println(findUnsortedSubarray(Array(2, 1)) == 2)

    println(optimize(Array(2, 6, 4, 8, 10, 9, 15)) == 5) // 1, 5
    println(optimize(Array(1, 2, 3, 4)) == 0)
    println(optimize(Array(1)) == 0)
    println(optimize(Array(2, 1)) == 2)
  }
}
