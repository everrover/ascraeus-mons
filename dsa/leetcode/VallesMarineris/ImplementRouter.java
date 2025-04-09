package dsa.leetcode.VallesMarineris;

import java.util.*;

public class ImplementRouter {

    /**
     * https://leetcode.com/problems/implement-router/description/
     *
     * The Router class manages network packets by using a combination of hash maps and lists.
     * It ensures that the FIFO (First In First Out) principle is maintained, and effectively handles duplicates 
     * and retrieval counts using auxiliary methods and efficient data structures.
     *
     * TC: O(1) for add and forward, O(log n) for getCount
     * SC: O(n)
     * #array #hash-table #binary-search #design #queue #medium
     */

    private int memoryLimit;
    private List<int[]> packetList;
    private Map<String, Integer> packetMap; // Combining "source-destination-timestamp" as key to handle duplication
    private Deque<int[]> packetQueue;

    public ImplementRouter(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        packetList = new ArrayList<>();
        packetMap = new HashMap<>();
        packetQueue = new ArrayDeque<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String packetKey = source + "-" + destination + "-" + timestamp;
        if (packetMap.containsKey(packetKey)) {
            return false; // Duplicate packet
        }
        if (packetQueue.size() >= memoryLimit) {
            int[] oldestPacket = packetQueue.poll();
            String oldestPacketKey = oldestPacket[0] + "-" + oldestPacket[1] + "-" + oldestPacket[2];
            packetMap.remove(oldestPacketKey);
        }
        int[] newPacket = new int[]{source, destination, timestamp};
        packetQueue.offer(newPacket);
        packetMap.put(packetKey, packetQueue.size() - 1);
        return true;
    }

    public int[] forwardPacket() {
        if (packetQueue.isEmpty()) {
            return new int[]{}; // No packet to forward
        }
        int[] packet = packetQueue.poll();
        String packetKey = packet[0] + "-" + packet[1] + "-" + packet[2];
        packetMap.remove(packetKey);
        return packet;
    }

    public int getCount(int destination, int startTime, int endTime) {
        int count = 0;
        for (int[] packet : packetQueue) {
            if (packet[1] == destination && packet[2] >= startTime && packet[2] <= endTime) {
                count++;
            }
        }
        return count;
    }
}