package com.oycm.datastructure.queue.deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_1 {

    /**
     * 2810. <a href="https://leetcode.cn/problems/faulty-keyboard/description/">故障键盘</a> 1193
     *
     * @param s
     * @return
     */
    public static String finalString(String s) {
        Deque<Character> q = new ArrayDeque<>();
        // true 先后添加, false 向前添加
        boolean flag = true;
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                flag = !flag;
            } else if (flag){
                q.addLast(c);
            } else {
                q.addFirst(c);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!q.isEmpty()) {
            if (flag) {
                ans.append(q.removeFirst());
            } else {
                ans.append(q.removeLast());
            }
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        finalString("string");
    }

}
