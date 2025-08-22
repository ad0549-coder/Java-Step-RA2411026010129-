import java.util.Scanner;

public class CaseConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        String upperManual = toUpperCaseManual(text);
        String lowerManual = toLowerCaseManual(text);
        String titleManual = toTitleCaseManual(text);

        String upperBuiltIn = text.toUpperCase();
        String lowerBuiltIn = text.toLowerCase();

        boolean upperCompare = compareResults(upperManual, upperBuiltIn);
        boolean lowerCompare = compareResults(lowerManual, lowerBuiltIn);

        System.out.printf("%-15s %-20s %-20s %-10s\n", "Conversion", "Manual Result", "Built-in Result", "Match");
        System.out.printf("%-15s %-20s %-20s %-10s\n", "Uppercase", upperManual, upperBuiltIn, upperCompare);
        System.out.printf("%-15s %-20s %-20s %-10s\n", "Lowercase", lowerManual, lowerBuiltIn, lowerCompare);
        System.out.printf("%-15s %-20s\n", "Title Case", titleManual);

        sc.close();
    }

    public static char toUpperChar(char ch) {
        if (ch >= 97 && ch <= 122) return (char)(ch - 32);
        return ch;
    }

    public static char toLowerChar(char ch) {
        if (ch >= 65 && ch <= 90) return (char)(ch + 32);
        return ch;
    }

    public static String toUpperCaseManual(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) sb.append(toUpperChar(text.charAt(i)));
        return sb.toString();
    }

    public static String toLowerCaseManual(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) sb.append(toLowerChar(text.charAt(i)));
        return sb.toString();
    }

    public static String toTitleCaseManual(String text) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                sb.append(ch);
                newWord = true;
            } else {
                if (newWord) sb.append(toUpperChar(ch));
                else sb.append(toLowerChar(ch));
                newWord = false;
            }
        }
        return sb.toString();
    }

    public static boolean compareResults(String manual, String builtIn) {
        return manual.equals(builtIn);
    }
}
