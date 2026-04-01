import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ChartDrawer {
    public static void draw(ChartData chartData) {
        try {
            ArrayList<String> xDataStr = chartData.xData;
            ArrayList<String> yDataStr = chartData.yData;

            int count = xDataStr.size();

            if (count != yDataStr.size()) {
                throw new RuntimeException("X and Y data sets must be identical in length");
            }

            double[] xData = new double[count];
            double[] yData = new double[count];

            for (int i = 0; i < count; i++) {
                xData[i] = Double.parseDouble(xDataStr.get(i));
                yData[i] = Double.parseDouble(yDataStr.get(i));
            }

            double minX = xData[0], maxX = xData[0];
            double minY = yData[0], maxY = yData[0];

            for (int i = 1; i < count; i++) {
                minX = Math.min(minX, xData[i]);
                maxX = Math.max(maxX, xData[i]);
                minY = Math.min(minY, yData[i]);
                maxY = Math.max(maxY, yData[i]);
            }

            int width = 800;
            int height = 600;
            int padding = 50;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();

            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);

            graphics.setColor(Color.BLACK);
            graphics.drawLine(padding, height - padding, width - padding, height - padding);
            graphics.drawLine(padding, padding, padding, height - padding);

            graphics.setColor(Color.RED);

            for (int i = 0; i < count; i++) {
                double xPercent = (xData[i] - minX) / (maxX - minX);
                double yPercent = (yData[i] - minY) / (maxY - minY);

                int x = (int) (padding + xPercent * (width - 2 * padding));
                int y = (int) (height - padding - yPercent * (height - 2 * padding));

                graphics.fillOval(x - 3, y - 3, 6, 6);
            }

            graphics.setColor(Color.BLACK);
            graphics.drawString(chartData.xLabel, width / 2, height - 10);
            graphics.drawString(chartData.yLabel, 10, height / 2);

            graphics.dispose();

            ImageIO.write(image, "png", new File("output.png"));

            System.out.println("Chart saved as output.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
