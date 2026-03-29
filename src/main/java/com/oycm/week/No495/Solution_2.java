package com.oycm.week.No495;

import java.util.*;

public class Solution_2 {

    class EventManager {
        Map<Integer, Integer> eventMap;
        PriorityQueue<int[]> priorityMap;


        public EventManager(int[][] events) {
            eventMap = new HashMap<>();
            priorityMap = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);
            for (int[] event : events) {
                eventMap.put(event[0], event[1]);
                priorityMap.add(event);

            }
        }

        public void updatePriority(int eventId, int newPriority) {
            priorityMap.add(new int[]{eventId, newPriority});
            eventMap.put(eventId, newPriority);
        }

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
