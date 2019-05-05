import java.io.*;

public class Standard {

    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        String lines = br.readLine();
        String[] tokens = lines.trim().split("\\s+");

        int a = Integer.parseInt(tokens[0]);
        int b = Integer.parseInt(tokens[1]);

        System.out.println(a + b);

        long elapsed = System.nanoTime() - start;
        System.out.println(elapsed/1000000);
    }
}