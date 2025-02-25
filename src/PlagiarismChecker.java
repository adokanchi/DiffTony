/**
 * Plagiarism Checker
 * A tool for finding the longest shared substring between two documents.
 *
 * @author Zach Blick
 * @author YOUR NAME HERE
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
        int n1 = doc1.length();
        int n2 = doc2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                // Compute dp[i][j]

                // We are trying to add the jth character of doc2
                char target = doc2.charAt(j);

                int idx = -1;
                for (int k = i - 1; k >= 0; k--) {
                    if (doc2.charAt(k) == target) {
                        idx = k;
                        break;
                    }
                }
                if (idx != -1) dp[i][j] = Math.max(dp[i][j - 1], 1 + dp[idx - 1][j - 1]);
            }
        }

        return dp[n1][n2];
    }
}
