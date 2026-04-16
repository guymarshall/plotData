import java.util.ArrayList;

public class ChartData {
    ArrayList<String> xData;
    ArrayList<String> yData;
    String xLabel;
    String yLabel;

    public ChartData(ArrayList<String> xData, ArrayList<String> yData, String xLabel, String yLabel) {
        this.xData = xData;
        this.yData = yData;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }
}
