import java.util.*;

public class EmailAnalyzer {
    static class EmailInfo {
        String email, username, domain, domainName, extension;
        boolean isValid;
        EmailInfo(String email, String username, String domain, String domainName, String extension, boolean isValid) {
            this.email = email;
            this.username = username;
            this.domain = domain;
            this.domainName = domainName;
            this.extension = extension;
            this.isValid = isValid;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<EmailInfo> emails = new ArrayList<>();
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String email = sc.nextLine();
            boolean valid = validateEmail(email);
            if (valid) {
                emails.add(extractEmailParts(email, true));
            } else {
                emails.add(new EmailInfo(email, "", "", "", "", false));
            }
        }

        displayEmails(emails);
        analyzeStats(emails);
        sc.close();
    }

    public static boolean validateEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex == -1 || atIndex != email.lastIndexOf('@')) return false;
        if (atIndex == 0 || atIndex == email.length() - 1) return false;
        int dotIndex = email.indexOf('.', atIndex);
        if (dotIndex == -1 || dotIndex == atIndex + 1 || dotIndex == email.length() - 1) return false;
        return true;
    }

    public static EmailInfo extractEmailParts(String email, boolean valid) {
        if (!valid) return new EmailInfo(email, "", "", "", "", false);
        int atIndex = email.indexOf('@');
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);
        int dotIndex = domain.lastIndexOf('.');
        String domainName = domain.substring(0, dotIndex);
        String extension = domain.substring(dotIndex + 1);
        return new EmailInfo(email, username, domain, domainName, extension, true);
    }

    public static void displayEmails(List<EmailInfo> emails) {
        System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s\n", "Email", "Username", "Domain", "Domain Name", "Extension", "Valid");
        for (EmailInfo e : emails) {
            System.out.printf("%-30s %-15s %-20s %-15s %-10s %-10s\n",
                    e.email,
                    e.username,
                    e.domain,
                    e.domainName,
                    e.extension,
                    e.isValid ? "Valid" : "Invalid");
        }
    }

    public static void analyzeStats(List<EmailInfo> emails) {
        int validCount = 0, invalidCount = 0, totalUsernameLength = 0;
        Map<String, Integer> domainFrequency = new HashMap<>();
        for (EmailInfo e : emails) {
            if (e.isValid) {
                validCount++;
                totalUsernameLength += e.username.length();
                domainFrequency.put(e.domain, domainFrequency.getOrDefault(e.domain, 0) + 1);
            } else {
                invalidCount++;
            }
        }
        String mostCommonDomain = "";
        int maxFreq = 0;
        for (Map.Entry<String, Integer> entry : domainFrequency.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostCommonDomain = entry.getKey();
            }
        }
        double avgUsernameLength = validCount == 0 ? 0 : (double) totalUsernameLength / validCount;

        System.out.println("\nSummary:");
        System.out.println("Total Valid Emails: " + validCount);
        System.out.println("Total Invalid Emails: " + invalidCount);
        System.out.println("Most Common Domain: " + (mostCommonDomain.isEmpty() ? "N/A" : mostCommonDomain));
        System.out.printf("Average Username Length: %.2f\n", avgUsernameLength);
    }
}
