package com.leetcode.interview_question.c;

public class Solution_1 {


}

/**
 * 面试题 03.01. <a href="https://leetcode.cn/problems/three-in-one-lcci/description/">三合一</a>
 */
class TripleInOne {

    /**
     * @param stackSize 栈的数量
     */
    public TripleInOne(int stackSize) {
        st = new int[3 * stackSize];
        size = stackSize;
        for (int i = 0; i < up.length; i++) {
            loc[i] = i * size;
            low[i] = i * size;
            up[i] = i * size + size;
        }
    }

    int[] st;
    int size;
    int[] loc = new int[3];
    int[] up = new int[3];
    int[] low = new int[3];

    public void push(int stackNum, int value) {
        int idx = loc[stackNum];
        if (idx < up[stackNum]) {
            st[loc[stackNum]] = value;
            loc[stackNum]++;
        }
    }

    public int pop(int stackNum) {
        int idx = loc[stackNum];
        if (idx > low[stackNum]) {
            loc[stackNum]--;
            return st[loc[stackNum]];
        } else {
            return -1;
        }
    }

    public int peek(int stackNum) {
        int idx = loc[stackNum];
        if (idx > low[stackNum]) {
            return st[loc[stackNum] - 1];
        } else {
            return -1;
        }
    }

    public boolean isEmpty(int stackNum) {
        return loc[stackNum] == low[stackNum];
    }
}
