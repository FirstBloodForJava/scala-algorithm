package com.oycm.datastructure.queue.design;


import java.util.LinkedList;
import java.util.Queue;

public class Solution_3 {

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
    }
}

/**
 * 225. <a href="https://leetcode.cn/problems/implement-stack-using-queues/description/">用队列实现栈</a>
 * 仅使用两个队列实现一个后入先出（LIFO）的栈
 */
class MyStack {
    Queue<Integer> in = new LinkedList<>();
    Queue<Integer> out = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        in.add(x);
        while (!out.isEmpty()) {
            in.add(out.poll());
        }
        Queue<Integer> temp = in;
        in = out;
        out = temp;
    }

    public int pop() {
        return out.poll();
    }

    public int top() {
        return out.peek();
    }

    public boolean empty() {
        return out.isEmpty();
    }
}