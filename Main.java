import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class Main {
    public static void main(String[] args) {
        String filename = "data.csv";
        boolean useLogScale = false;

        try {
            ChartData chartData = CsvReader.readDataFromCSV(filename);

            ChartDrawer.draw(chartData, useLogScale);
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            System.exit(1);
        } catch (AccessDeniedException e) {
            System.out.println("Failed to read file: incorrect permission");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}