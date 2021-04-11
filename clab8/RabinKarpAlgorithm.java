public class RabinKarpAlgorithm {

    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
//    public static int rabinKarp(String input, String pattern) {
//        int cmp = Integer.compare(input.length(), pattern.length());
//        if (cmp < 0) {
//            return -1;
//        }
//        RollingString rollingPattern = new RollingString(pattern, pattern.length());
//        RollingString rollingInput = new RollingString(input.substring(0, pattern.length()), pattern.length());
//        int readPosition = pattern.length();
//        int patternHash = rollingPattern.hashCode();
//        int inputHash = rollingInput.hashCode();
//        for (int i = 0; i < input.length() - pattern.length(); i++) {
//            if (patternHash == inputHash) {
//                if (rollingPattern.equals(rollingInput)) {
//                    return i;
//                }
//            }
//            rollingInput.addChar(input.charAt(readPosition));
//            inputHash = rollingInput.hashCode();
//            readPosition++;
//        }
//        return -1;
//    }
    public static int rabinKarp(String input, String pattern) {
        int cmp = Integer.compare(input.length(), pattern.length());
        if (cmp < 0) {
            return -1;
        } else if (cmp == 0) {
            if (input.equals(pattern)) {
                return 0;
            } else {
                return -1;
            }
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
