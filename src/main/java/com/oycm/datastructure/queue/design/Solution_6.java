package com.oycm.datastructure.queue.design;

public class Solution_6 {

    public static void main(String[] args) {
        MyCircularDeque q = new MyCircularDeque(5);
        q.insertFront(7);
        q.insertLast(0);
        q.getFront();
        q.insertLast(3);
        q.getFront();
        q.insertFront(9);
        q.getRear();
        q.getFront();
        q.getFront();
        q.deleteLast();
        q.getRear();
    }

}

/**
 * 641. <a href="https://leetcode.cn/problems/design-circular-deque/">设计循环双端队列</a>
 *
 * front = 0, rear = 0, 时, insertFront 后 front = k-1, rear = 0, front 或 rear 哪个先插入，后续谁就可以先赋值再 index 计算，否则就需要 先计算再赋值
 * 注意，只有 按固定规则更新即可，有两种方式
 *
 *  insertFront 先更新再赋值, 头就是 front；insertLast 先赋值再更新，尾就是 (rear - 1 + capacity) % capacity
 *  insertFront 先赋值再更新, 头就是 (front - 1 + capacity) % capacity; insertLast 先更新再赋值，尾就是 rear
 */
class MyCircularDeque {

    int capacity;
    int front;
    int rear;
    int[] q;

    public MyCircularDeque(int k) {
        capacity = k + 1;
        q = new int[capacity];
    }

    /**
     * @param value
     * @return 将一个元素添加到双端队列头部, 是否成功
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + capacity) % capacity;
        q[front] = value;
        return true;
    }

    /**
     * @param value
     * @return 将一个元素添加到双端队列尾部, 是否成功
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        q[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    /**
     *
     * @return 从双端队列头部删除一个元素, 是否成功
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        return true;
    }

    /**
     *
     * @return 从双端队列尾部删除一个元素, 是否成功
     */
    public boolean deleteLast() {
        if (isEmpty()){
            return false;
        }
        rear = (rear - 1 + capacity) % capacity;
        return true;
    }

    /**
     *
     * @return 从双端队列头部获得一个元素, 如果双端队列为空，返回 -1
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return q[front];
    }

    /**
     * @return 获得双端队列的最后一个元素, 如果双端队列为空，返回 -1
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return q[(rear - 1 + capacity) % capacity];
    }

    /**
     *
     * @return 队列是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     *
     * @return 队列是否满了
     */
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}

class MyCircularDeque2 {

    int capacity;
    int front;
    int rear;
    int[] q;

    public MyCircularDeque2(int k) {
        capacity = k + 1;
        q = new int[capacity];
    }

    /**
     * @param value
     * @return 将一个元素添加到双端队列头部, 是否成功
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        q[front] = value;
        front = (front - 1 + capacity) % capacity;
        return true;
    }

    /**
     * @param value
     * @return 将一个元素添加到双端队列尾部, 是否成功
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        rear = (rear + 1) % capacity;
        q[rear] = value;
        return true;
    }

    /**
     *
     * @return 从双端队列头部删除一个元素, 是否成功
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        return true;
    }

    /**
     *
     * @return 从双端队列尾部删除一个元素, 是否成功
     */
    public boolean deleteLast() {
        if (isEmpty()){
            return false;
        }
        rear = (rear - 1 + capacity) % capacity;
        return true;
    }

    /**
     *
     * @return 从双端队列头部获得一个元素, 如果双端队列为空，返回 -1
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return q[(front + 1) % capacity];
    }

    /**
     * @return 获得双端队列的最后一个元素, 如果双端队列为空，返回 -1
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return q[rear];
    }

    /**
     *
     * @return 队列是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     *
     * @return 队列是否满了
     */
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}