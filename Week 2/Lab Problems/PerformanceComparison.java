import java.util.Scanner;

public class PerformanceComparison {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int iterations = sc.nextInt();

        Result stringResult = testStringConcat(iterations);
        Result builderResult = testStringBuilder(iterations);
        Result bufferResult = testStringBuffer(iterations);

        displayResults(stringResult, builderResult, bufferResult);
        sc.close();
    }

    static class Result {
        String method;
        long timeTaken;
        int length;
        Result(String method, long timeTaken, int length) {
            this.method = method;
            this.timeTaken = timeTaken;
            this.length = length;
        }
    }

    public static Result testStringConcat(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) s = s + "a";
        long end = System.currentTimeMillis();
        return new Result("String + Concatenation", end - start, s.length());
    }

    public static Result testStringBuilder(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("a");
        long end = System.currentTimeMillis();
        return new Result("StringBuilder.append", end - start, sb.length());
    }

    public static Result testStringBuffer(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("a");
        long end = System.currentTimeMillis();
        return new Result("StringBuffer.append", end - start, sb.length());
    }

    public static void displayResults(Result... results) {
        System.out.printf("%-25s %-15s %-15s\n", "Method Used", "Time (ms)", "Length");
        for (Result r : results) {
            System.out.printf("%-25s %-15d %-15d\n", r.method, r.timeTaken, r.length);
        }
    }
}
