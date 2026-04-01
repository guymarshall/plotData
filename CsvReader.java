import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {
    public static ChartData readDataFromCSV(String filePath) throws IOException {
        ArrayList<String> xData = new ArrayList<>();
        ArrayList<String> yData = new ArrayList<>();
        String xLabel = "";
        String yLabel = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            if ((line = bufferedReader.readLine()) != null) {
                String[] headers = line.split(",");
                xLabel = headers[0].trim();
                yLabel = headers[1].trim();
            }

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2) {
                    xData.add(values[0].trim());
                    yData.add(values[1].trim());
                }
            }
        }

        return new ChartData(xData, yData, xLabel, yLabel);
    }
}
