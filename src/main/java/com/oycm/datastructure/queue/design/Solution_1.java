package com.oycm.datastructure.queue.design;


import java.util.Deque;
import java.util.LinkedList;

public class Solution_1 {

    public static void main(String[] args) {
        FrontMiddleBackQueue q = new FrontMiddleBackQueue();
        q.pushMiddle(772749);
        q.pushMiddle(264112);
        q.pushFront(339812);

        q.popFront();
        q.popMiddle();
        q.popMiddle();
    }

}

/**
 * 1670. <a href="https://leetcode.cn/problems/design-front-middle-back-queue/">设计前中后队列</a> 1611
 * 设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 * 问题关键点是如何在 队列中间执行 push 和 pop
 *
 * 维护两个队列 pre 和 suf, 维持 pre 和 suf size 差 [0,1]
 * pushFront pre 为空，直接 pre.addFirst(val); pre.size > suf, pre 最后一个元素添加到 suf 的前面;
 */
class FrontMiddleBackQueue {

    Deque<Integer> pre = new LinkedList<>();
    Deque<Integer> suf = new LinkedList<>();

    public FrontMiddleBackQueue() {

    }

    /**
     * 将 val 添加到队列的 最前面
     * @param val
     */
    public void pushFront(int val) {
        if (pre.size() > suf.size()) {
            suf.addFirst(pre.pollLast());
            pre.addFirst(val);
        } else {
            pre.addFirst(val);
        }
    }

    /**
     * 将 val 添加到队列的 正中间
     * @param val
     */
    public void pushMiddle(int val) {
        if (pre.size() == suf.size()) {
            // 1,2 插入在 1 和 2 的中间
            suf.addFirst(val);
        }else if (suf.size() > pre.size()) {
            // [1] [2,3] 插入在 2 的前面
            pre.addLast(val);
        } else {
            // [1,2] [3]
            suf.addFirst(pre.pollLast());
            pre.addLast(val);
        }
    }

    /**
     * 将 val 添加到队里的 最后面
     * @param val
     */
    public void pushBack(int val) {
        if (suf.size() > pre.size()) {
            pre.addLast(suf.pollFirst());
            suf.addLast(val);
        } else {
            suf.addLast(val);
        }
    }

    /**
     *  将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1
     * @return
     */
    public int popFront() {
        if (pre.isEmpty() && suf.isEmpty()) {
            return -1;
        } else if (pre.size() < suf.size()) {
            // 当 pre.size < suf.size
            pre.addLast(suf.pollFirst());
        }
        return pre.pollFirst();
    }

    /**
     * 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1
     * @return
     */
    public int popMiddle() {
        if (pre.isEmpty() && suf.isEmpty()) {
            return -1;
        } else if (pre.size() == suf.size()) {
            return pre.pollLast();
        } else if (pre.size() < suf.size()) {
            return suf.pollFirst();
        } else {
            return pre.pollLast();
        }
    }

    /**
     * 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1
     * @return
     */
    public int popBack() {
        if (pre.isEmpty() && suf.isEmpty()) {
            return -1;
        } else if (pre.size() > suf.size()) {
            suf.addFirst(pre.pollLast());
        }
        return suf.pollLast();
    }
}

