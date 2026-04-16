import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Main {
    public static int addPadding(int input) {
        return (int) (input * 1.2);
    }

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

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Chart Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ImageIcon icon = new ImageIcon("output.png");
            int imageWidth = icon.getIconWidth();
            int imageHeight = icon.getIconHeight();

            JLabel label = new JLabel(icon);

            label.setHorizontalAlignment(JLabel.CENTER);

            frame.getContentPane().add(new JScrollPane(label), BorderLayout.CENTER);

            frame.setSize(addPadding(imageWidth), addPadding(imageHeight));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}