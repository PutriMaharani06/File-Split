import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<String> queue = new LinkedList<>();

        System.out.print("Masukkan nama file (misal: file.txt): ");
        String fileName = scanner.nextLine();

        System.out.print("Masukkan jumlah baris per bagian: ");
        int linesPerPart = scanner.nextInt();
        scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            StringBuilder currentPart = new StringBuilder();
            int lineCount = 0;

            while ((line = br.readLine()) != null) {
                currentPart.append(line).append("\n");
                lineCount++;

                if (lineCount >= linesPerPart) {
                    queue.add(currentPart.toString());
                    currentPart.setLength(0); // Reset untuk bagian berikutnya
                    lineCount = 0; // Reset hitungan baris
                }
            }
            if (currentPart.length() > 0) {
                queue.add(currentPart.toString());
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }

        System.out.println("\nBagian-bagian dari file:");
        int partNumber = 1;
        while (!queue.isEmpty()) {
            System.out.println("Bagian " + partNumber + ":");
            System.out.println(queue.poll());
            partNumber++;
        }

        scanner.close();
    }
}








