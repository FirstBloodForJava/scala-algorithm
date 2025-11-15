package com.oycm.algorithm.a.advance

object Solution_13 {

  /**
   * 30. 串联所有单词的子串
   * https://leetcode.cn/problems/substring-with-concatenation-of-all-words/description/
   *
   * 串联子串：包含  words 中所有字符串以任意顺序排列连接起来的子串。
   *
   * @param s     字符串
   * @param words 长度相同的字符串数组
   * @return 所有串联子串在 s 中的开始索引
   */
  def findSubstring(s: String, words: Array[String]): List[Int] = {
    /*
    记 words 中字符串长度为 m, 数组长度为 k, t = m * k
    s 长度记为 n，把 s 分割为 长为 m 的 k 个子串，如果子串的单词数和 words 中的单词数相同，则 i-t 就是开始索引的下标
    [0, m),[m, 2m)...,[(k-1)m, km)
    [1, m+1),[m+1, 2m+1)...,[(k-1)m+1, km+1)
    ...
    [n-km, n-km+m)...[n-m,n)
    s 移动一次，需要 t 次找出自己的 分割子串，和 words 进行比较次数

    时间复杂度 O(k * m + k * m * (n - k*m))
              O(t + t * (n -t)
    空间复杂度 O(k)
    */
    val n = s.length

    val k = words.length
    val m = words(0).length
    val t = k * m
    val map = scala.collection.mutable.Map[String, Int]()
    var cnt = 0

    val ans = scala.collection.mutable.ArrayBuffer[Int]()
    for (word <- words) {
      map(word) = map.getOrElse(word, 0) + 1
      if (map(word) == 1) {
        cnt += 1
      }
    }
    for (i <- 0 to n - t) {
      val mapT = map.clone()
      var cntT = cnt
      // t 次循环查找字符数
      for (j <- i until i + t by m) {
        val word = s.substring(j, j + m)
        if (mapT.contains(word)) {
          mapT(word) -= 1
          if (mapT(word) == 0) {
            cntT -= 1
          }
        }
      }
      if (cntT == 0) {
        ans.append(i)
      }

    }
    ans.toList
  }

  def answer(s: String, words: Array[String]): List[Int] = {
    /*
    题解：
    n = s.length, k = words.length, m = words(0).length
    在窗口 [0, k) 滑动, 分割截取字符串和 words 的字符数比较

    时间复杂度 O(k*m + m * n) 一个窗口 判断是否符合要求的时间复杂度是 O(n)
    */
    val n = s.length
    val m = words(0).length
    val t = m * words.length
    val map = scala.collection.mutable.Map[String, Int]()
    for (word <- words) {
      map(word) = map.getOrElse(word, 0) + 1
    }
    val ans = scala.collection.mutable.ArrayBuffer[Int]()
    for (i <- 0 until m) {
      val cnt = scala.collection.mutable.Map[String, Int]()
      var over = 0
      for (j <- i + m to n by m) {
        val word = s.substring(j - m, j)
        // 相同次数后再次出现, 达到 t 长度也无法符合要求
        if (cnt.getOrElse(word, 0) == map.getOrElse(word, 0)) {
          over += 1
        }
        cnt(word) = cnt.getOrElse(word, 0) + 1
        val left = j - t
        if (left >= 0) {
          // 更新答案
          if (over == 0) {
            ans.append(left)
          }
          // 出窗口 over 怎么变化
          val outWord = s.substring(left, left + m)
          cnt(outWord) -= 1
          // 相同字符删除
          if (cnt(outWord) == map.getOrElse(outWord, 0)) {
            over -= 1
          }

        }
      }

    }

    ans.toList
  }

  def main(args: Array[String]): Unit = {
    println(findSubstring("barfoothefoobarman", Array("foo", "bar")))
    println(findSubstring("wordgoodgoodgoodbestword", Array("word", "good", "best", "word")))
    println(findSubstring("barfoofoobarthefoobarman", Array("bar", "foo", "the")))

    println(answer("barfoothefoobarman", Array("foo", "bar")))
    println(answer("wordgoodgoodgoodbestword", Array("word", "good", "best", "word")))
    println(answer("barfoofoobarthefoobarman", Array("bar", "foo", "the")))
  }
}
