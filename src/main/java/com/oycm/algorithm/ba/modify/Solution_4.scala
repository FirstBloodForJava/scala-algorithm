package com.oycm.algorithm.ba.modify

object Solution_4 {

  /**
   * 2273. 移除字母异位词后的结果数组
   * https://leetcode.cn/problems/find-resultant-array-after-removing-anagrams/description/
   *
   * 字母异位词：是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。例如，"dacb" 是 "abdc" 的一个字母异位词。
   *
   * @param words 小写英文字母组成的字符串数组
   * @return
   */
  def removeAnagrams(words: Array[String]): List[String] = {
    /*
    时间复杂度 O(n L), n 是数组的长度, L 是字符串数组中的最长字符
    */
    if (words.length == 1) {
      return words.toList
    }
    var preTemp = Array.fill(26)(0)
    for (i <- words(0).indices) {
      preTemp(words(0)(i) - 'a') += 1
    }
    val ans = scala.collection.mutable.ArrayBuffer[String]()
    ans.append(words(0))
    for (i <- 1 until words.length) {
      val temp = Array.fill(26)(0)
      for (j <- words(i).indices) {
        temp(words(i)(j) - 'a') += 1
      }
      if (preTemp.toSeq != temp.toSeq) {
        preTemp = temp
        ans.append(words(i))
      }
    }

    ans.toList
  }

  def optimizeSpace(words: Array[String]): List[String] = {
    val n = words.length

    if (n == 1) {
      return words.toList
    }
    var k = 1
    var preTemp = Array.fill(26)(0)
    for (i <- words(0).indices) {
      preTemp(words(0)(i) - 'a') += 1
    }

    for (i <- 1 until words.length) {
      val temp = Array.fill(26)(0)
      for (j <- words(i).indices) {
        temp(words(i)(j) - 'a') += 1
      }
      if (preTemp.toSeq != temp.toSeq) {
        preTemp = temp
        words(k) = words(i)
        k += 1
      }
    }
    words.slice(0, k).toList
  }

  def main(args: Array[String]): Unit = {
    println(removeAnagrams(Array("abba", "baba", "bbaa", "cd", "cd")))
    println(removeAnagrams(Array("a", "b", "c", "d", "e")))

    println(optimizeSpace(Array("abba", "baba", "bbaa", "cd", "cd")))
    println(optimizeSpace(Array("a", "b", "c", "d", "e")))
  }
}
