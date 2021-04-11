public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int cmp = Integer.compare(input.length(), pattern.length());
        if (cmp < 0) {
            return -1;
        }
        RollingString rollingPattern = new RollingString(pattern, pattern.length());
        int patternHash = rollingPattern.hashCode();
        for (int i = 0; i < input.length() - pattern.length(); i++) {
            String str = input.substring(i, i + pattern.length());
            RollingString rollingInput = new RollingString(str, str.length());
            int inputHash = rollingInput.hashCode();
            if (patternHash == inputHash) {
                if (rollingPattern.equals(rollingInput)) {
                    return i;
                }
            }
        }
        return -1;
    }

}
