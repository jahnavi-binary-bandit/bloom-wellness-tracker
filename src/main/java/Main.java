import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PeriodLogDAO dao = new PeriodLogDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Women's Wellness Tracker ===");
        System.out.println("1. Log today's entry");
        System.out.println("2. View past logs");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("User ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.println("Mood: 1. Happy  2. Sad  3. Anxious  4. Irritable  5. Calm  6. Tired");
            System.out.print("Choose: ");
            int moodChoice = scanner.nextInt();
            scanner.nextLine();
            String[] moods = {"Happy", "Sad", "Anxious", "Irritable", "Calm", "Tired"};
            String mood = moods[moodChoice - 1];

            System.out.println("Energy level: 1. High  2. Medium  3. Low");
            System.out.print("Choose: ");
            int energy = scanner.nextInt();
            scanner.nextLine();
            String energyLevel = energy == 1 ? "High" : energy == 2 ? "Medium" : "Low";

            System.out.println("Symptoms (comma separated): Cramps, Bloating, Headache, Fatigue, Acne, None");
            System.out.print("Enter: ");
            String symptoms = scanner.nextLine();

            System.out.print("Notes: ");
            String notes = scanner.nextLine();

            System.out.print("Water intake (glasses): ");
            int water = scanner.nextInt();

            System.out.print("Sleep hours: ");
            float sleep = scanner.nextFloat();
            scanner.nextLine();

            System.out.print("Stress level (1-10): ");
            int stress = scanner.nextInt();
            scanner.nextLine();

            dao.insertLog(userId, date, mood, energyLevel, symptoms, notes, water, sleep, stress);

        } else if (choice == 2) {
            System.out.print("User ID: ");
            int userId = scanner.nextInt();
            scanner.nextLine();

            var logs = dao.getAllLogs(userId);
            if (logs.isEmpty()) {
                System.out.println("No logs found.");
            } else {
                for (var log : logs) {
                    System.out.println("---");
                    System.out.println("Date: " + log.getLogDate());
                    System.out.println("Mood: " + log.getMood());
                    System.out.println("Energy: " + log.getEnergyLevel());
                    System.out.println("Symptoms: " + log.getSymptoms());
                    System.out.println("Notes: " + log.getNotes());
                    System.out.println("Water: " + log.getWaterIntake() + " glasses");
                    System.out.println("Sleep: " + log.getSleepHours() + " hours");
                    System.out.println("Stress: " + log.getStressLevel() + "/10");
                    System.out.println("Encouragement: " + log.getEncouragement());
                }
            }
        }
        scanner.close();
    }
}