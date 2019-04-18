import java.io.*;

class Program {

    static char[] expand9Palindrome(char[] result) {
        int length = result.length;
        int range = (length / 2) - (length % 2);

        boolean expanded = false;
        for (int i = range; i >= 0; i--) {
            if (result[i] != '9') {
                expanded = true;

                char newChar = (char) (result[i] + 1);
                result[i] = newChar;
                result[length - i - 1] = newChar;
            }
        }

        if (expanded) {
            return result;
        }

        // Crazy case, 999..., expand to 10...01
        char[] oneExpansion = new char[length + 1];

        for (int i = 0; i < length + 1; i++) {
            oneExpansion[i] = '0';
        }

        oneExpansion[0] = '1';
        oneExpansion[length] = '1';

        return oneExpansion;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // String lines = br.readLine();
        // int t = Integer.parseInt(lines);

        int t = 1;
        String[] inputs = new String[t];

        for (int i = 0; i < t; i++) {
            inputs[i] = br.readLine();
        }

        for (int i = 0; i < t; i++) {
            int length = inputs[i].length();
            int range = (length / 2) - 1;

            char[] result = inputs[i].toCharArray();

            boolean expanded = false;

            if (length % 2 == 1) {
                if (result[range + 1] != '9') {
                    result[range + 1] = (char) (result[range + 1] + 1);
                    expanded = true;
                }
                else{
                    // Set to zero, to be expanded later
                    result[range + 1] = '0';
                }
            } else {
                if (result[range] != '9' || result[range + 1] != '9') {
                    char newChar = (char) (result[range] + 1);
                    result[range] = newChar;
                    result[range + 1] = newChar;
                    expanded = true;
                }
            }

            for (int j = range; j >= 0; j--) {
                int left = j;
                int right = length - j - 1;

                if (expanded) {
                    // set left to right
                    result[right] = result[left];
                } else {
                    if (result[left] != result[right]) {
                        // Expand not equal
                        if (result[right] > result[left]) {
                            char newChar = (char) (result[left] + 1);
                            result[left] = newChar;
                            result[right] = newChar;
                        } else {
                            result[right] = result[left];
                        }
                        expanded = true;
                    } else if (result[left] != '9') {
                        // Expand equal
                        char newChar = (char) (result[left] + 1);
                        result[left] = newChar;
                        result[right] = newChar;
                        expanded = true;
                    } else {
                        // Set to zero, will be expanded in later changes
                        result[left] = '0';
                        result[right] = '0';
                    }
                }
            }

            if (expanded) {
                System.out.println(String.valueOf(result));
            } else {
                // Case of 999..., expand to 10...01
                char[] oneExpansion = new char[length + 1];

                for (int j = 0; j < length + 1; j++) {
                    oneExpansion[j] = '0';
                }

                oneExpansion[0] = '1';
                oneExpansion[length] = '1';
                System.out.println(String.valueOf(oneExpansion));
            }
        }

    }
}