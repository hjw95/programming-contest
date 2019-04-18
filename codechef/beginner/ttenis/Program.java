package codechef.beginner.ttenis;

import java.io.*;

class Program {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();
        int t = Integer.parseInt(lines);

        boolean[] win = new boolean[t];
        for (int i = 0; i < t; i++) {
            String line = br.readLine();
            int winCount = 0;
            int loseCount = 0;
            for (char c : line.toCharArray()) {
                if (c == '1') {
                    winCount += 1;
                } else {
                    loseCount += 1;
                }

                if (winCount >= 11 && winCount - loseCount > 1) {
                    win[i] = true;
                    break;
                }

                if (loseCount >= 11 && loseCount - winCount > 1) {
                    win[i] = false;
                    break;
                }
            }
        }
        for (int i = 0; i < t; i++) {
            if (win[i]) {
                System.out.println("WIN");
            } else {
                System.out.println("LOSE");
            }
        }

    }
}