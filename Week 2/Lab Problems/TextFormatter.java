import java.util.ArrayList;
import java.util.Scanner;

public class TextFormatter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        int width = sc.nextInt();
        sc.nextLine();

        String[] words = splitIntoWords(text);

        ArrayList<String> leftJustified = justifyText(words, width);
        ArrayList<String> centered = centerAlign(leftJustified, width);

        long sbStart = System.nanoTime();
        StringBuilder sbTest = new StringBuilder();
        for (String line : leftJustified) sbTest.append(line).append("\n");
        long sbEnd = System.nanoTime();

        long concatStart = System.nanoTime();
        String concatTest = "";
        for (String line : leftJustified) concatTest += line + "\n";
        long concatEnd = System.nanoTime();

        displayText("Original Text", new ArrayList<>(java.util.Arrays.asList(text.split("\n"))));
        displayText("Left Justified Text", leftJustified);
        displayText("Center Aligned Text", centered);

        System.out.println("\nPerformance Comparison:");
        System.out.println("StringBuilder time (ns): " + (sbEnd - sbStart));
        System.out.println("String Concatenation time (ns): " + (concatEnd - concatStart));
        sc.close();
    }

    public static String[] splitIntoWords(String text) {
        ArrayList<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start != i) words.add(text.sub
