/*
Time Complexity (TC): The algorithm performs a binary search on the element values (O(log(max-min))) and 
traverses the matrix once for each binary search iteration (O(n)), resulting in a time complexity of O(n * log(max-min)).

Space Complexity (SC): The space complexity is O(1) as only a few variables are used for the binary search and traversal, 
with no additional space required apart from the input matrix.

Code Explanation: The code performs a binary search on the possible values in the matrix and counts how many elements are less than or equal to the 
midpoint value in each iteration. The search space is narrowed based on whether the count is less than or greater than k, 
and the k-th smallest element is returned once the search converges.
 */

 class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        // Step 1: Edge case check: if the matrix is null or has no elements, return 0
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        // Step 2: Initialize low and high for binary search
        int low = matrix[0][0];  // Start with the smallest element in the matrix (top-left corner)
        int n = matrix.length - 1;  // Calculate the last index of the matrix (n x n matrix)
        int high = matrix[n][n];  // Start with the largest element in the matrix (bottom-right corner)

        // Step 3: Perform binary search to find the k-th smallest element
        while (low < high) {
            int mid = low + (high - low) / 2;  // Calculate mid (possible value for the k-th smallest)

            int count = 0;  // Count of elements less than or equal to mid
            int j = matrix[0].length - 1;  // Start from the last column of the current row
            // Step 4: Traverse the matrix row by row
            for (int i = 0; i < matrix.length; i++) {
                // Step 5: Move from right to left in the row to find elements <= mid
                while (j >= 0 && matrix[i][j] > mid) {
                    j--;  // Move left if the current element is greater than mid
                }
                // Step 6: Add the count of elements <= mid in the current row
                count = count + j + 1;  // j + 1 because j is the index of the last valid element
            }

            // Step 7: Binary search adjustment based on the count
            if (count < k) {
                // If there are fewer than k elements <= mid, increase the low boundary
                low = mid + 1;
            } else {
                // If count >= k, decrease the high boundary to search for smaller values
                high = mid;
            }
        }

        // Step 8: Return the k-th smallest element (low will point to the k-th smallest element)
        return low;
    }
}
