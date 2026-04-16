import java.io.File;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ChartDrawer {
    public static void draw(ChartData chartData, boolean useLogScale) {
        try {
            int count = chartData.xData.size();

            if (count != chartData.yData.size()) {
                throw new RuntimeException("X and Y data sets must be identical in length");
            }

            double[] xData = chartData.xData.stream().mapToDouble(Double::parseDouble).toArray();
            double[] yData = chartData.yData.stream().mapToDouble(Double::parseDouble).toArray();

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
                double xPercent = 0.0;
                double yPercent = 0.0;
                if (useLogScale) {
                    double logMinX = Math.log10(minX);
                    double logMaxX = Math.log10(maxX);
                    xPercent = (Math.log10(xData[i]) - logMinX) / (logMaxX - logMinX);

                    double logMinY = Math.log10(minY);
                    double logMaxY = Math.log10(maxY);
                    yPercent = (Math.log10(yData[i]) - logMinY) / (logMaxY - logMinY);
                } else {
                    xPercent = (xData[i] - minX) / (maxX - minX);
                    yPercent = (yData[i] - minY) / (maxY - minY);
                }

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
