
/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: [Elijah Chandler]
 *</p>
 */

public class DNA {

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        int[] Map = new int[4];
        int radix = 256;
        long prime = 54321102419L;
        int length = STR.length();
        int seqlength = sequence.length();
        int hash = 0;
        // Hash of STR
        long strHash = hash(STR, radix, length, prime);
        // Hash of first letter in str length of the sequence
        long seqHash = hash(sequence.substring(0, length), radix, length, prime);
        int longest = 0;
        int current;
        long Pow = 1;
        // Find the highest power for rolling hash
        for (int i = 1; i < length; i++) {
            Pow = (Pow * radix) % prime;
        }
        // Traverse sequence and find consecutive repeats of STR
        for (int i = 0; i <= seqlength - length; ) {
            current = 0;
            // Check for consecutive repeats of the STR in the sequence
            while (seqHash == strHash && i <= seqlength - length) {
                current++;
                i += length;
                if (i <= seqlength - length) {
                    // Update the sequence hash to the next substring of sequence that is STR's
                    // length
                    seqHash = hash(sequence.substring(i, i + length), radix, length, prime);
                }
            }
            // If the current consecutive repeat of the STR in the sequence is longer than the
            // previous longest consecutive repeat then make it the new longest
            if (current > longest) {
                longest = current;
            }
            // If there was no consecutive repeat then update the hash by one character
            if (current == 0 ) {
                seqHash = (seqHash + prime - sequence.charAt(i) * Pow % prime) % prime;
                if(i < seqlength - length){
                    seqHash = (seqHash * radix + sequence.charAt(i + length)) % prime;
                }
                i++;
            }
        }
        return longest;
    }
    public static long hash(String STR, int radix, int length, long prime) {
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash = ((hash * radix + STR.charAt(i)) % prime);
        }
        return hash;
    }

}
