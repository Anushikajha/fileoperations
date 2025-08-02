
import java.io.*;
import java.util.*;

public class FileOperations {

    // File path (You can change this as needed)
    private static final String FILE_NAME = "sample.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== FILE OPERATIONS DEMO ===");
        System.out.println("1. Write to file");
        System.out.println("2. Read from file");
        System.out.println("3. Modify file");
        System.out.print("Choose an option (1/2/3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                writeToFile(scanner);
                break;
            case 2:
                readFromFile();
                break;
            case 3:
                modifyFile(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    // Write content to file (overwrite)
    private static void writeToFile(Scanner scanner) {
        System.out.println("\nEnter content to write (type 'end' to finish):");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("end"))
                    break;
                writer.write(line);
                writer.newLine();
            }
            System.out.println(" File written successfully.");
        } catch (IOException e) {
            System.out.println(" Error writing to file: " + e.getMessage());
        }
    }

    // Read and display the content of the file
    private static void readFromFile() {
        System.out.println("\n=== File Content ===");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println(" File read complete.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Modify the content of the file: find and replace
    private static void modifyFile(Scanner scanner) {
        System.out.print("\nEnter word to find: ");
        String toFind = scanner.nextLine();
        System.out.print("Enter replacement word: ");
        String replacement = scanner.nextLine();

        try {
            // Read entire content
            File file = new File(FILE_NAME);
            StringBuilder content = new StringBuilder();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                content.append(fileScanner.nextLine().replaceAll(toFind, replacement)).append("\n");
            }
            fileScanner.close();

            // Write modified content back to file
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            writer.write(content.toString());
            writer.close();

            System.out.println(" File modified successfully.");
        } catch (IOException e) {
            System.out.println(" Error modifying file: " + e.getMessage());
        }
    }
}
