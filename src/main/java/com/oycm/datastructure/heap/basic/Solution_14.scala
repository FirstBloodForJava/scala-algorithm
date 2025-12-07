package com.oycm.datastructure.heap.basic

object Solution_14 {

  /**
   * 1942. 最小未被占据椅子的编号 1695
   * https://leetcode.cn/problems/the-number-of-the-smallest-unoccupied-chair/description/
   *
   * 有 [0, n-1] 个椅子可选，当一个朋友到达时，会占用编号最小的未被占用的椅子
   *
   * 例如，当一个朋友到达时，如果椅子 0 ，1 和 5 被占据了，那么他会占据 2 号椅子。
   *
   * @param times        二维数组表示，第 i 个朋友的 到达 和 离开 的时刻，所有到达时间各不相同
   * @param targetFriend 返回该编号朋友占据的椅子
   * @return
   */
  def smallestChair(times: Array[Array[Int]], targetFriend: Int): Int = {
    /*
    维护三个堆
      未被占用椅子小顶堆
      到达时间小顶堆 (到达时间, 朋友编号)
      离开时间小顶堆 (离开时间, 椅子编号) 离开时间相同，椅子小的优先
    */
    var ans = -1
    val h1 = scala.collection.mutable.PriorityQueue.empty[Int](Ordering[Int].reverse)
    val arrives = scala.collection.mutable.PriorityQueue[(Int, Int)]()(Ordering.by[(Int, Int), Int](_._1).reverse)
    val leaves = scala.collection.mutable.PriorityQueue[(Int, Int)]()(Ordering.by[(Int, Int), Int](_._1).reverse)
    for (i <- times.indices) {
      h1.addOne(i)
      arrives.addOne(times(i)(0), i)
    }
    while (arrives.nonEmpty && ans < 0) {
      // 到达的人
      val arrive = arrives.dequeue()
      // 归还椅子
      while (leaves.nonEmpty && arrive(0) >= leaves.head(0)) {
        val leave = leaves.dequeue()
        h1.addOne(leave(1))
      }
      // 椅子
      val h = h1.dequeue()
      leaves.addOne((times(arrive(1))(1), h))
      if (targetFriend == arrive(1)) {
        ans = h
      }
    }

    ans
  }


  def main(args: Array[String]): Unit = {

  }

}



