package com.leetcode.interview_question.c;

import java.util.Deque;
import java.util.LinkedList;

public class Solution_6 {

}

/**
 * 面试题 03.06. <a href="https://leetcode.cn/problems/animal-shelter-lcci/description/">动物收容所</a>
 */
class AnimalShelf {

    /*
    动物收容所。有家动物收容所只收容狗与猫，且严格遵守 “先进先出” 的原则。队列
    在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。
    换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，
    比如enqueue、dequeueAny、dequeueDog和dequeueCat。允许使用Java内置的LinkedList数据结构。
     */
    public AnimalShelf() {

    }

    Deque<Integer> dogQueue = new LinkedList<>();
    Deque<Integer> catQueue = new LinkedList<>();

    public void enqueue(int[] animal) {
        if (animal[1] == 0) {
            catQueue.add(animal[0]);
        } else {
            dogQueue.add(animal[0]);
        }
    }

    public int[] dequeueAny() {
        if (dogQueue.isEmpty() && catQueue.isEmpty()) return new int[]{-1, -1};
        if (dogQueue.isEmpty()) {
            return dequeueCat();
        }
        if (catQueue.isEmpty()) {
            return dequeueDog();
        }
        return dogQueue.peek() < catQueue.peek() ? dequeueDog() : dequeueCat();
    }

    public int[] dequeueDog() {
        if (dogQueue.isEmpty()) return new int[]{-1, -1};
        return new int[]{dogQueue.pop(), 1};
    }

    public int[] dequeueCat() {
        if (catQueue.isEmpty()) return new int[]{-1, -1};
        return new int[]{catQueue.pop(), 0};
    }
}
