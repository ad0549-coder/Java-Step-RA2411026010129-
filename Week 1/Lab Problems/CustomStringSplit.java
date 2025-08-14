import java.util.Scanner;

public class CustomStringSplit {
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
        int[] spaceIndexes = new int[spaceCount];
        int indexCounter = 0;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') {
                spaceIndexes[indexCounter++] = i;
            }
        }
        String[] words = new String[wordCount];
        int start = 0;
        int wordIndex = 0;

        for (int i = 0; i < spaceCount; i++) {
            int end = spaceIndexes[i];
            words[wordIndex++] = substringWithoutLength(text, start, end);
            start = end + 1;
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
    public static boolean compareArrays(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a text: ");
        String input = scanner.nextLine();
        String[] customWords = customSplit(input);
        String[] builtinWords = input.split(" ");
        boolean areEqual = compareArrays(customWords, builtinWords);
        System.out.println("\nCustom Split Result:");
        for (String word : customWords) {
            System.out.println(word);
        }
        System.out.println("\nBuilt-in Split Result:");
        for (String word : builtinWords) {
            System.out.println(word);
        }
        System.out.println("\nArrays are equal: " + areEqual);
        scanner.close();
    }
}

