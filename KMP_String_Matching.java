import java.util.Scanner;

class KMP_String_Matching
{
    String KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M+1];
        int j = 0;  // index for pat[]
        String r = "";
        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat,M,lps);

        int i = 0;  // index for txt[]
        while (i < N)
        {
            if (pat.charAt(j) == txt.charAt(i))
            {
                j++;
                i++;
            }
            if (j == M)
            {
                System.out.print((i-j)+" ");
                r = r+(i-j)+" ";
                j = lps[j-1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)){
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
        }
        // System.out.println(r);
        System.out.println();
        return r;
    }

    void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0;  // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M)
        {
            if (pat.charAt(i) == pat.charAt(len))
            {
                lps[i++] = ++len;
            }
            else  // (pat[i] != pat[len])
            {
                if (len != 0)
                {
                    len = lps[len-1];
                }
                else  // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    // Driver program to test above function
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);//set up the scanner
        // StringBuilder results = new StringBuilder();
        while(sc.hasNextLine()){
          String pat = sc.nextLine();
          String txt = sc.nextLine();
          // results.append(new KMP_String_Matching().KMPSearch(pat,txt)).append('\n');
          new KMP_String_Matching().KMPSearch(pat,txt);
        }

        // System.out.println(results);
    }
}
