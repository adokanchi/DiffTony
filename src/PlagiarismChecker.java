import java.util.ArrayList;
/**
 * Plagiarism Checker
 * A tool for finding the longest shared substring between two documents.
 *
 * @author Zach Blick
 * @author Tony Dokanchi
 */
public class PlagiarismChecker {
    /**
     * This method finds the longest sequence of characters that appear in both texts in the same order,
     * although not necessarily contiguously.
     * @param doc1 the first document
     * @param doc2 the second
     * @return The length of the longest shared substring.
     */
    public static int longestSharedSubstring(String doc1, String doc2) {
        final int LARGEST_CHAR = 8218;
        int n1 = doc1.length(), n2 = doc2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        // indices.get(c) is a sorted list of all indices of doc1 with the character c.
        ArrayList<ArrayList<Integer>> indices = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < LARGEST_CHAR; i++) {
            indices.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n1; i++) {
            indices.get(doc1.charAt(i)).add(i);
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                // targetIdx is the index of the last character of doc1 that matches target, before the ith character.
                char target = doc2.charAt(j - 1);
                int targetIdx = findLargest(indices.get(target), i - 1);

                // Exclude next character of doc2
                dp[i][j] = dp[i][j-1];
                // Include next character of doc2
                if (targetIdx != -1) {
                    dp[i][j] = Math.max(dp[i][j - 1], 1 + dp[targetIdx][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }

    // Returns the largest number in (sorted) list less than or equal to max using binary search.
    private static int findLargest(ArrayList<Integer> list, int max) {
        int left = 0, right = list.size() - 1, largest = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= max) {
                largest = list.get(mid);
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return largest;
    }
}