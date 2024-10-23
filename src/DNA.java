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
        int radix = 4;
        long prime = 54321102419L;
        int length = STR.length();
        int seqlength = sequence.length();
        int hash = 0;
        long strHash = hash(STR, radix, length, prime);
        long seqHash = hash(sequence.substring(0, length), radix, length, prime);

        int longest = 0;
        int current = 0;
        long Pow = 1;
        for (int i = 1; i < length; i++) {
            Pow = (Pow * radix) % prime;
        }
        for (int i = length; i < seqlength; i++) {
            if(strHash == seqHash){
                current++;
                if(current > longest){
                    longest = current;
                }
            }
            else {
                current = 0;
            }
            seqHash = (seqHash + prime - sequence.charAt(i - length) * Pow  % prime) % prime;
            seqHash = (seqHash * radix + sequence.charAt(i)) % prime;
        }
        return longest;

    }
    public static long hash(String STR, int radix, int length, long prime) {
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash = (int) ((hash * radix + STR.charAt(i)) % prime);
        }
        return hash;
    }

}
