package src;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    public static void main(String[] args) {
        JFileChooser j = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
        j.setFileFilter(filter);

        int selectedFile = j.showOpenDialog(null);

        if (selectedFile == JFileChooser.APPROVE_OPTION) {
            try {
                File filePath = j.getSelectedFile();
                BufferedImage image = ImageIO.read(new File(filePath.getAbsolutePath()));

                BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),
                        BufferedImage.TYPE_INT_RGB);
                newImage.createGraphics().drawImage(image, 0, 0, null);

                Path parentPath = filePath.toPath();
                Path fileName = parentPath.resolveSibling(filePath.getName().replace("png", "jpg"));

                ImageIO.write(newImage, "jpg", fileName.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
