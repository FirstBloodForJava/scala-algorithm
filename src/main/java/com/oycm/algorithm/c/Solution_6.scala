package com.oycm.algorithm.c

object Solution_6 {

  /**
   * 2348. 全 0 子数组的数目 1316
   * https://leetcode.cn/problems/number-of-zero-filled-subarrays/description/
   *
   * 统计全是 0 的子数组数
   *
   * @param nums
   * @return
   */
  def zeroFilledSubarray(nums: Array[Int]): Long = {
    /*
    用一个集合 记录 0 出现的次数
      当前 元素 是 0 时 添加集合，集合不为空时，子数组就是 集合元素的数量
      当前 元素 不是 0 时，清空元素

    时间复杂度 O(n)
    空间复杂度 O(n) 全是 0 的时候
     */
    var ans = 0L
    val list = scala.collection.mutable.ArrayBuffer[Int]()
    for (num <- nums) {
      if (num == 0) {
        list.addOne(num)
      } else {
        list.clear
      }
      ans += list.size
    }
    ans
  }

  def groupedLoop(nums: Array[Int]): Long = {
    /*
    分组循环，先找到 0 开始点 i，j = i + 1，找 一组 都是 0 的子数组 直到不是 1 的下标，ans += n * (n+1) / 2

    全是 0, 100000 * 100001 / 2 > Int.MaxValue

    时间复杂度 O(n)
    空间复杂度 O(1)
     */
    val n = nums.length

    var ans = 0L
    var l = 0
    while (l < n) {
      if (nums(l) == 0) {
        var r = l + 1
        while (r < n && nums(r) == 0) {
          r += 1
        }
        ans += (r - l).toLong * (r - l + 1) / 2
        l = r
      }

      l += 1

    }
    ans
  }


  def main(args: Array[String]): Unit = {
    println(zeroFilledSubarray(Array(1, 3, 0, 0, 2, 0, 0, 4)) == 6)
    println(zeroFilledSubarray(Array(0, 0, 0, 2, 0, 0)) == 9)
    println(zeroFilledSubarray(Array(2, 10, 2019)) == 0)

    println(groupedLoop(Array(1, 3, 0, 0, 2, 0, 0, 4)) == 6)
    println(groupedLoop(Array(0, 0, 0, 2, 0, 0)) == 9)
    println(groupedLoop(Array(2, 10, 2019)) == 0)
    println(groupedLoop(Array.fill(100000)(0)) == 100000L * 100001 / 2)

  }

}
