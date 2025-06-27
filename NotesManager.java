import java.io.*;
import java.util.*;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";
    private static List<String> notes = new ArrayList<>();

    public static void main(String[] args) {
        loadNotesFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== Notes Manager ======");
            System.out.println("1. View all notes");
            System.out.println("2. Add new note");
            System.out.println("3. Search notes");
            System.out.println("4. Save notes");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewNotes();
                case 2 -> addNote(scanner);
                case 3 -> searchNotes(scanner);
                case 4 -> saveNotesToFile();
                case 5 -> System.out.println("Exiting Notes Manager.");
                default -> System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);

        scanner.close();
    }

    private static void viewNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
        } else {
            System.out.println("\n--- Your Notes ---");
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();
        notes.add(note);
        System.out.println("Note added successfully.");
    }

    private static void searchNotes(Scanner scanner) {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (String note : notes) {
            if (note.toLowerCase().contains(keyword)) {
                System.out.println("- " + note);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching notes found.");
        }
    }

    private static void saveNotesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String note : notes) {
                writer.write(note);
                writer.newLine();
            }
            System.out.println("Notes saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving notes: " + e.getMessage());
        }
    }

    private static void loadNotesFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
            System.out.println("Loaded notes from " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error reading notes file: " + e.getMessage());
        }
    }
}
