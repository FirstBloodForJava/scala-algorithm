package com.oycm.algorithm.ba.modify

object Solution_12 {

  /**
   * 75. 颜色分类
   * https://leetcode.cn/problems/sort-colors/description/
   *
   * 整数 0、 1 和 2 分别表示红色、白色和蓝色。
   * 原地对 nums 进行排序同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
   *
   * @param nums 包含红色、白色和蓝色 的数组
   */
  def sortColors(nums: Array[Int]): Unit = {
    /*
    只有 0, 1, 2 元素的数组, 原地进行升序排序
    两次交换：把 0 全部交换到左边，把 2 全部交换到右边

    时间复杂度 O(n)
    */
    var i0 = 0
    for (i <- nums.indices) {
      if (nums(i) == 0) {
        val temp = nums(i0)
        nums(i0) = nums(i)
        nums(i) = temp
        i0 += 1
      }
    }
    val n = nums.length
    var i2 = n - 1
    var i = n - 1
    while (i >= i0) {
      if (nums(i) == 2) {
        val temp = nums(i2)
        nums(i2) = nums(i)
        nums(i) = temp
        i2 -= 1
      }
      i -= 1
    }
    //println(nums.toSeq)
  }

  def answer(nums: Array[Int]): Unit = {
    /*
    题解思路：
    如何在一个有序数组 a = [0,0,1,1,2,2], 插入一个 元素 0, 并使 数组 a 还保持有序
    暴力做法： 在数组的最左边插入 0, 其它所有元素右移一位, 时间复杂度是 O(n)
    [0,0,1,1,2,2]
    [0,0,0,1,1,2,2]
    对比插入后的表现
    1. a(2) 变成 0
    2. a(4) 变成 1
    3. 末尾新增 2, a(6) = 2

    修改过程中，如何知道修改的下标？
    1. 维护 a 中 0 的数量, 即要修改成 0 的位置, a0 = 2, 即 a(a0) = 0
    2. 维护 a 中 0 和 1 的数量, 即要修改成 1 的位置, a1 = 4, 即 a(a1) = 1
    3. 末尾新增的位置记为 i, 即 a(i) = 2

    注意，如果 a = [1], a 中没有 2, 上面的第 3 步就错了，结果变成了 a = [0,2]
    如果 a = [0], a 中没有 2 和 1, 上面的第 2, 3 步就错了，结果变成了 a = [0,2]
    按照这个逻辑修改，需要进行多种 if-else 判断
    如果倒过来修改呢？是不是就能解决这个问题。
    1. 新增位置记为 i, 即 a(i) = 2
    2. a(a1) = 1
    3. a(a0) = 0
    假设数组长 为 n, 0 的数量记为 a0, 1 的数量记为 a1, 2 的数量记为 a2
    如果 a2 > 0 正反修改都没有问题
    a2 == 0, a1 即要插入的位置 i, a(i) 先修改成 2, 再修改成 1, 完全没有问题
    如果 a1 == a2 == 0, 逻辑同上

    如果在有序数组 a 中插入的是 1
    1. a(i) = 2
    2. a(a1) = 1

    如果在有序数组 a 中插入的是 2
    1. a(i) = 2

    */
    var a0 = 0
    var a1 = 0
    for (i <- nums.indices) {
      val x = nums(i)
      // 0 | 1 | 2 的第 1 步
      nums(i) = 2
      // 0 或 1 的第 2 步
      if (x <= 1) {
        nums(a1) = 1
        a1 += 1
      }
      // 0 的第三步
      if (x == 0) {
        nums(a0) = 0
        a0 += 1
      }
    }
  }

  def main(args: Array[String]): Unit = {
    sortColors(Array(2, 0, 2, 1, 1, 0))
    answer(Array(2, 0, 2, 1, 1, 0))
  }

}
