package src.main.java.com.oycm.datastructure.stack


object Solution_8 {

  /**
   * 71. 简化路径
   * https://leetcode.cn/problems/simplify-path/description/
   *
   * 将以 / 开始的绝对路径简化
   *  - 一个点 '.' 表示当前目录本身。
   *  - 两个点 '..' 表示将目录切换到上一级（指向父目录）。
   *  - 任意多个连续的斜杠（即，'//' 或 '///'）都被视为单个斜杠 '/'。
   *  - 任何其他格式的点（例如，'...' 或 '....'）均被视为有效的文件/目录名称。
   *
   * @param path
   * @return
   */
  def simplifyPath(path: String): String = {
    /*
    从前往后遍历 根目录在底部，最后拼接是倒序的
    从后往前怎么遍历呢？如果当前目录不是 . 则添加到 stack 中，如果 stack 非空时，看栈顶是否等于 .. ，等于则弹栈，且当前元素也放弃入栈
    栈遍历时，如果元素等于 .. 则放弃
    */
    val paths = path.split("/")
    val stack = scala.collection.mutable.Stack[String]()
    for (p <- paths.reverse if p.nonEmpty) {
      if (!p.equals(".")) {
        if (stack.isEmpty) {
          stack.push(p)
        } else if (!p.equals("..") && stack.top.equals("..")) {
          stack.pop()
        } else {
          stack.push(p)
        }
      }
    }
    var ans = scala.collection.mutable.StringBuilder()
    while (stack.nonEmpty) {
      val pop = stack.pop()
      if (!pop.equals("..")) {
        ans.append("/").append(pop)
      }
    }
    if (ans.isEmpty) {
      ans = scala.collection.mutable.StringBuilder("/")
    }
    ans.toString()
  }

  def optimize(path: String): String = {
    /*
    可以使用栈的思路，但是不使用栈
    */
    val list = scala.collection.mutable.ArrayBuffer[String]()
    for (p <- path.split("/") if p.nonEmpty && !p.equals(".")) {
      if (!p.equals("..")) {
        list.addOne(p)
      } else if (list.nonEmpty) {
        list.remove(list.size - 1)
      }
    }

    "/" + list.mkString("/")
  }

  def main(args: Array[String]): Unit = {
    println(simplifyPath("/home//foo/"))
    println(simplifyPath("/home/user/Documents/../Pictures"))
    println(simplifyPath("/.../a/../b/c/../d/./"))
    println(simplifyPath("/../"))
  }
}
