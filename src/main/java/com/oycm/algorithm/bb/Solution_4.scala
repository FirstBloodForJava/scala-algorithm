package com.oycm.algorithm.bb

object Solution_4 {

  /**
   * 2570. 合并两个二维数组 - 求和法
   * https://leetcode.cn/problems/merge-two-2d-arrays-by-summing-values/description/
   *
   * nums1[i].length == nums2[j].length == 2
   *
   * 二维数组含义：
   *  - nums1[i] = [idi, vali] 表示编号为 idi 的数字对应的值等于 vali
   *  - nums2[i] = [idi, vali] 表示编号为 idi 的数字对应的值等于 vali 。
   *
   * 每个数组都包含 互不相同 的 id ，并按 id 以 递增 顺序排列。
   *
   * 将两个数组合并为一个按 id 以递增顺序排列的数组，并符合下述条件：
   *  - 只有在两个数组中至少出现过一次的 id 才能包含在结果数组内。
   *  - 每个 id 在结果数组中 只能出现一次 ，并且其对应的值等于两个数组中该 id 所对应的值求和。如果某个数组中不存在该 id ，则假定其对应的值等于 0 。
   *
   * @param nums1 二维 整数数组
   * @param nums2 二维 整数数组
   * @return
   */
  def mergeArrays(nums1: Array[Array[Int]], nums2: Array[Array[Int]]): Array[Array[Int]] = {
    /*
    [[1,2],[2,3],[4,5]]
    [[1,4],[3,2],[4,1]]

    [[1,6],[2,3],[3,2],[4,6]]

    每个数组都包含 互不相同 的 id
    nums1(i)(0) == nums(j)(0) i++, j++
    不相同时，移动 较小的指针，继续下一轮比较

    时间复杂度 O(n+m) 两个数组的 id 各不相同
     */
    val n = nums1.length
    val m = nums2.length
    val ans = scala.collection.mutable.ArrayBuffer[Array[Int]]()

    var i = 0
    var j = 0
    while (i < n || j < m) {
      if (i == n) {
        ans.append(nums2(j))
        j += 1
      } else if (j == m) {
        ans.append(nums1(i))
        i += 1
      } else if (nums1(i)(0) == nums2(j)(0)) {
        ans.append(nums1(i))
        nums1(i)(1) += nums2(j)(1)
        i += 1
        j += 1
      } else if (nums1(i)(0) < nums2(j)(0)) {
        ans.append(nums1(i))
        i += 1
      } else {
        ans.append(nums2(j))
        j += 1
      }
    }

    ans.toArray
  }


}
