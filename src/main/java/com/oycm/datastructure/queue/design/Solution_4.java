package com.oycm.datastructure.queue.design;


import java.util.Stack;

public class Solution_4 {

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);

        q.pop();
        q.push(5);

        q.pop();
        q.pop();
        q.pop();
        q.pop();
    }
}

/**
 * 232. <a href="https://leetcode.cn/problems/implement-queue-using-stacks/">用栈实现队列</a>
 * 使用两个栈实现 先入先出 队列
 * [1, 2, 3] 在栈中 [3, 2, 1] 形式存在
 */
class MyQueue {

    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();
    public MyQueue() {

    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        // 不为空时，栈倒过来
        if (out.isEmpty()) {
            inToOut();
        }
        return out.pop();
    }

    public int peek() {
        if (out.isEmpty()) {
            inToOut();
        }
        return out.peek();
    }

    public boolean empty() {
        return out.isEmpty() && in.empty();
    }

    // 倒栈
    public void inToOut(){
        while (!in.empty()) {
            out.push(in.pop());
        }
    }
}