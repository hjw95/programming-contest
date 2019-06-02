package hackerrank.balancedbrackets;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static boolean isPair(char closing, char opening) {
        if (opening == '[' && closing == ']')
            return true;
        else if (opening == '{' && closing == '}')
            return true;
        else if (opening == '(' && closing == ')')
            return true;
        return false;
    }

    static boolean isOpening(char opening) {
        if (opening == '[' || opening == '{' || opening == '(')
            return true;
        return false;
    }

    static boolean isClosing(char closing) {
        if (closing == ']' || closing == '}' || closing == ')')
            return true;
        return false;
    }

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        char[] sChar = s.toCharArray();
        boolean balanced = true;
        Deque<Character> charStack = new ArrayDeque<Character>();

        for (char curr : sChar) {
            if (isOpening(curr)) {
                charStack.addFirst(curr);
            } else if (isClosing(curr) && charStack.peekFirst() != null && isPair(curr, charStack.peekFirst())) {
                charStack.removeFirst();
            } else {
                balanced = false;
            }
        }

        if (charStack.size() > 0) {
            balanced = false;
        }

        if (balanced) {
            return "YES";
        } else {
            return "NO";
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
