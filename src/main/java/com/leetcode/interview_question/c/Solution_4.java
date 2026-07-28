package com.leetcode.interview_question.c;

import java.util.Deque;
import java.util.LinkedList;

public class Solution_4 {

}

class MyQueue {

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {

    }

    Deque<Integer> inSt = new LinkedList<>();
    Deque<Integer> outSt = new LinkedList<>();

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        inSt.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (outSt.isEmpty()) {
            inToOut();
        }
        return outSt.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (outSt.isEmpty()) {
            inToOut();
        }
        return outSt.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return inSt.isEmpty() && outSt.isEmpty();
    }

    public void inToOut() {
        while (!inSt.isEmpty()) {
            outSt.push(inSt.pop());
        }
    }
}
