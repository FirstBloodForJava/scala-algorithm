package com.leetcode.interview_question.c;

import java.util.ArrayList;
import java.util.List;

public class Solution_3 {
}

/**
 * 面试题 03.03. <a href="https://leetcode.cn/problems/stack-of-plates-lcci/description/">堆盘子</a>
 */
class StackOfPlates {

    /*
    堆盘子。设想有一堆盘子，堆太高可能会倒下来。
    因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。
    请实现数据结构 SetOfStacks，模拟这种行为。
    SetOfStacks 应该由多个栈组成，并且在前一个栈填满时新建一个栈。
    此外，SetOfStacks.push() 和 SetOfStacks.pop() 应该与普通栈的操作方法相同（也就是说，pop() 返回的值，应该跟只有一个栈时的情况一样）。
    进阶：实现一个 popAt(int index) 方法，根据指定的子栈，执行 pop 操作。
    当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
     */
    public StackOfPlates(int cap) {
        size = cap;
    }

    // 表示多个栈
    List<List<Integer>> sts = new ArrayList();
    // 每个栈中元素数量
    int size;

    public void push(int val) {
        if (size == 0) {
            return;
        }
        if (sts.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            list.add(val);
            sts.add(list);
        } else {
            List<Integer> last = sts.get(sts.size() - 1);
            if (last.size() == size) {
                List<Integer> list = new ArrayList<>();
                list.add(val);
                sts.add(list);
            } else {
                last.add(val);
            }
        }
    }

    public int pop() {
        if (sts.isEmpty()) {
            return -1;
        }
        List<Integer> last = sts.get(sts.size() - 1);
        Integer pop = last.remove(last.size() - 1);
        if (last.isEmpty()) {
            sts.remove(sts.size() - 1);
        }
        return pop;
    }

    // 当 index 下标栈清空时，后面的栈下标要前移
    public int popAt(int index) {
        if (index >= sts.size()) {
            return -1;
        }
        List<Integer> cur = sts.get(index);
        Integer pop = cur.remove(cur.size() - 1);
        if (cur.isEmpty()) {
            // 删除栈
            sts.remove(index);
        }
        return pop;
    }
}