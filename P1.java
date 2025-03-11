/*
Meeting rooms 2

Time Complexity (TC): O(N log N)

Sorting the intervals takes O(N log N) time, where N is the number of intervals.
For each interval, adding and removing from the priority queue takes O(log N) time, and there are N intervals, resulting in O(N log N) for the loop.
Space Complexity (SC): O(N)

The space used by the priority queue is O(N) since in the worst case, all intervals might overlap, 
and all the meeting end times need to be stored in the priority queue.
Explanation of the code in three sentences:

The code first sorts the intervals by their start times. It then uses a priority queue to track the ongoing meetings, 
adding the end times to the queue. If a meeting starts after the earliest ending meeting (peek of the queue), the meeting room can be reused; 
otherwise, a new room is added, and the function returns the number of rooms needed, which corresponds to the size of the priority queue.
 */

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] interval : intervals) {
            if (!pq.isEmpty() && pq.peek() <= interval[0]) {
                pq.poll();
            }
            pq.add(interval[1]);
        }
        return pq.size();
    }
}