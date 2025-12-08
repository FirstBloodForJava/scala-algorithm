package com.oycm.datastructure.queue.design;


public class Solution_5 {

    public static void main(String[] args) {
//        MyCircularQueue circularQueue = new MyCircularQueue(8);
//        circularQueue.enQueue(3);
//        circularQueue.enQueue(9);
//        circularQueue.enQueue(5);
//        circularQueue.enQueue(0);
//
//        circularQueue.deQueue();
//        circularQueue.deQueue();
//
//        circularQueue.isEmpty();
//        circularQueue.isEmpty();
//
//        circularQueue.Rear();
//        circularQueue.Rear();
//
//        circularQueue.deQueue();

        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        circularQueue.enQueue(1);  // 返回 true
        circularQueue.enQueue(2);  // 返回 true
        circularQueue.enQueue(3);  // 返回 true
        circularQueue.enQueue(4);  // 返回 false，队列已满
        circularQueue.Rear();  // 返回 3
        circularQueue.isFull();  // 返回 true
        circularQueue.deQueue();  // 返回 true
        circularQueue.enQueue(4);  // 返回 true
        circularQueue.Rear();  // 返回 4

    }

}

/**
 * 622. <a href="https://leetcode.cn/problems/design-circular-queue/description/">设计循环队列</a>
 * 注意点：当队列为空时 front == rear; 当队列满了时 front == rear(n-1 移动到 0)
 */
class MyCircularQueue {
    int[] q;
    int capacity;
    int front;
    int rear;

    public MyCircularQueue(int k) {
        capacity = k + 1;
        q = new int[capacity];
    }

    /**
     * @param value
     * @return 向循环队列插入一个元素。如果成功插入则返回真
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        q[rear % capacity] = value;
        rear++;
        return true;
    }

    /**
     * @return 从循环队列中删除一个元素。如果成功删除则返回真
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front++;
        return true;
    }

    /**
     * @return 从队首获取元素。如果队列为空，返回 -1
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return q[front % capacity];
    }

    /**
     * @return 获取队尾元素。如果队列为空，返回 -1
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return q[(rear - 1) % capacity];
    }

    public boolean isEmpty() {
        return front == capacity;
    }

    public boolean isFull() {
        return rear != front && (rear + 1) % capacity == front;
    }
}
