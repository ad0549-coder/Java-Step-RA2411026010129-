import java.util.Scanner;

public class WordLengthTable {
    public static int getStringLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
        }
        return count;
    }
    public static String[] customSplit(String text) {
        int length = getStringLength(text);
        int spaceCount = 0;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceCount++;
            }
        }

        int wordCount = spaceCount + 1;
        String[] words = new String[wordCount];

        int start = 0, wordIndex = 0;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                words[wordIndex++] = substringWithoutLength(text, start, i);
                start = i + 1;
            }
        }
        words[wordIndex] = substringWithoutLength(text, start, length);

        return words;
    }
    private static String substringWithoutLength(String text, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(text.charAt(i));
        }
        return sb.toString();
    }
    public static String[][] wordLengthTable(String[] words) {
        String[][] table = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            int len = getStringLength(words[i]);
            table[i][0] = words[i];                 
            table[i][1] = String.valueOf(len);      
        }
        return table;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Enter a sentence: ");
        String input = scanner.nextLine();
        String[] words = customSplit(input);
        String[][] table = wordLengthTable(words);
        System.out.println("\nWord\t\tLength");
        System.out.println("----------------------");
        for (int i = 0; i < table.length; i++) {
            String word = table[i][0];
            int len = Integer.parseInt(table[i][1]);
            System.out.println(word + "\t\t" + len);
        }

        scanner.close();
    }
}
