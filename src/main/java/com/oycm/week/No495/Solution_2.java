package com.oycm.week.No495;

import java.util.*;

public class Solution_2 {

    /**
     * 3885. <a href="https://leetcode.cn/problems/design-event-manager/">设计事件管理器</a> 1548
     *
     */
    class EventManager {
        Map<Integer, Integer> eventMap;
        PriorityQueue<int[]> priorityMap;

        /**
         *
         * @param events events[i] = [eventId, priority] 表示一个事件 id 和事件优先级, id 是唯一的
         */
        public EventManager(int[][] events) {
            eventMap = new HashMap<>();
            priorityMap = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);
            for (int[] event : events) {
                eventMap.put(event[0], event[1]);
                priorityMap.add(event);

            }
        }

        /**
         * 更新一个事件 id 的 优先级
         * @param eventId
         * @param newPriority
         */
        public void updatePriority(int eventId, int newPriority) {
            priorityMap.add(new int[]{eventId, newPriority});
            eventMap.put(eventId, newPriority);
        }

        /**
         *  移除并返回具有 最高优先级 的 活跃事件 的 eventId。如果有多个活动事件具有相同的优先级，则返回 eventId 最小的事件。
         * @return 如果没有活跃事件，则返回 -1
         */
        public int pollHighest() {
            while (!priorityMap.isEmpty()) {
                int[] poll = priorityMap.poll();
                Integer priority  = eventMap.get(poll[0]);
                if (priority == null || priority != poll[1]) {
                    continue;
                }
                eventMap.remove(poll[0]);
                return poll[0];
            }

            return -1;
        }
    }
}
