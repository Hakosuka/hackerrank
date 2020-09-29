import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getTotalX' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        // find LCM of all elements of a
        int lcm = findLowestCommonMultiple(a);
        // find HCF of all elements of b
        int hcf = findHighestCommonFactor(b);
        // Count the number of multiples of LCM that evenly divides the HCF.
        int count = 0;
        for(int i = lcm, j = 2; i<=hcf; i = lcm*j, j++){
            if(hcf % i == 0) {
                count++;
            }
        }
        return count;
    }
    public static int findLowestCommonMultiple(List<Integer> inputList){
        int lcm = inputList.get(0);
        for(int i = 1; i < inputList.size(); i++){
            lcm = lcmOfTwoNums(lcm, inputList.get(i));
        }
        return lcm;
    }
    public static int findHighestCommonFactor(List<Integer> inputList){
        //Take the first element as a default answer
        int hcf = inputList.get(0);
        //Loop through the rest of the array
        for(int i = 1; i < inputList.size(); i++){
            hcf = hcfOfTwoNums(hcf, inputList.get(i));
        }
        return hcf;
    }
    /**
     * Finds the lowest common multiple of two numbers.
     * @params a, b: the two numbers that I want to find the LCM of
     */
    private static int lcmOfTwoNums(int a, int b){
        return a * (b/hcfOfTwoNums(a, b));
    }
    /**
     * Finds the highest common factor of two numbers.
     * @params a, b: the two numbers that I want to find the        HCF of
     */
    private static int hcfOfTwoNums(int a, int b){
        while(b > 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int total = Result.getTotalX(arr, brr);

        bufferedWriter.write(String.valueOf(total));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
