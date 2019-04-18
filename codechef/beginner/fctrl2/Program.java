package codechef.beginner.fctrl2;

import java.io.*;

class Program {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();
        int t = Integer.parseInt(lines);
        int[] inp = new int[t];
        for (int i = 0; i < t; i++) {
            String line = br.readLine();
            inp[i] = Integer.parseInt(line);
        }
        int maxdigits = 500;
        for (int i = 0; i < t; i++) {
            int[] digits = new int[maxdigits];
            digits[0] = 1;
            for (int j = 1; j <= inp[i]; j++) {
                int[] carryOver = new int[maxdigits];
                for (int k = 0; k < maxdigits; k++) {
                    if (digits[k] == 0 && carryOver[k] == 0) {
                        continue;
                    }
                    int currentDigitMultiplication = j * digits[k];
                    digits[k] = currentDigitMultiplication % 10;
                    digits[k] += carryOver[k] % 10;
                    if (digits[k] > 9) {
                        digits[k] %= 10;
                        carryOver[k + 1] += 1;
                    }
                    currentDigitMultiplication /= 10;
                    int currentDigitIndex = k + 1;
                    while (currentDigitMultiplication > 0) {
                        int currentDigit = currentDigitMultiplication % 10;
                        carryOver[currentDigitIndex] += currentDigit;

                        if (carryOver[currentDigitIndex] > 9) {
                            carryOver[currentDigitIndex + 1] += 1;
                            carryOver[currentDigitIndex] %= 10;
                        }
                        currentDigitMultiplication /= 10;
                        currentDigitIndex++;
                    }
                }
            }
            boolean digitStarted = false;
            for (int l = maxdigits - 1; l >= 0; l--) {
                if (digits[l] != 0 || digitStarted) {
                    System.out.print(digits[l]);
                    digitStarted = true;
                }
            }
            System.out.println();
        }
    }
}