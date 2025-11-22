package com.oycm.algorithm.bb

object Solution_5 {

  /**
   * 350. 两个数组的交集 II
   * https://leetcode.cn/problems/intersection-of-two-arrays-ii/description/
   *
   * 返回两数组的交集, 出现次数要一致
   * @param nums1
   * @param nums2
   * @return
   */
  def intersect(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    val ans = scala.collection.mutable.ArrayBuffer[Int]()
    val map = scala.collection.mutable.Map[Int,Int]()
    if (nums1.length < nums2.length) {
      for(num <- nums1) {
        map(num) = map.getOrElse(num, 0) + 1
      }
    } else {
      for (num <- nums2) {
        map(num) = map.getOrElse(num, 0) + 1
      }
    }
    if (nums1.length < nums2.length) {
      for (num <- nums2) {
        if (map.contains(num) && map(num) > 0) {
          ans.addOne(num)
          map(num) -= 1
        }
      }
    } else {
      for (num <- nums1) {
        if (map.contains(num) && map(num) > 0) {
          ans.addOne(num)
          map(num) -= 1
        }
      }
    }

    ans.toArray
  }

  /**
   * 进阶问题：
   * 如果给定的数组已经排好序呢？你将如何优化你的算法？ 可以使用双指针移动，从 0 开始遍历，不相等时，移动较小的值 数组指针
   *
   * 如果 nums1 的大小比 nums2 小，哪种方法更优？
   *
   * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
   */
}
