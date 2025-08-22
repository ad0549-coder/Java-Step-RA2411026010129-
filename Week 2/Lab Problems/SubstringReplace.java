import java.util.Scanner;

public class SubstringReplace {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String toFind = sc.nextLine();
        String toReplace = sc.nextLine();

        int[] positions = findOccurrences(text, toFind);
        String manualReplaceResult = manualReplace(text, toFind, toReplace, positions);
        String builtInReplaceResult = text.replace(toFind, toReplace);
        boolean isEqual = compareResults(manualReplaceResult, builtInReplaceResult);

        System.out.println(manualReplaceResult);
        System.out.println(builtInReplaceResult);
        System.out.println(isEqual);
        sc.close();
    }

    public static int[] findOccurrences(String text, String toFind) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(toFind, index)) != -1) {
            count++;
            index += toFind.length();
        }
        int[] positions = new int[count];
        int posIndex = 0;
        index = 0;
        while ((index = text.indexOf(toFind, index)) != -1) {
            positions[posIndex++] = index;
            index += toFind.length();
        }
        return positions;
    }

    public static String manualReplace(String text, String toFind, String toReplace, int[] positions) {
        StringBuilder result = new StringBuilder();
        int posIndex = 0;
        int i = 0;
        while (i < text.length()) {
            if (posIndex < positions.length && i == positions[posIndex]) {
                result.append(toReplace);
                i += toFind.length();
                posIndex++;
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean compareResults(String manual, String builtIn) {
        return manual.equals(builtIn);
    }
}
