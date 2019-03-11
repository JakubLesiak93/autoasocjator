import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Im {

    private int[] pic;

    Im(int pN) {
        try {
            pic = new int[2500];
            File input_file = new File("przyklady\\" + Integer.toString(pN) + ".png");
            BufferedImage image = ImageIO.read(input_file);
            int[] rgbArray = image.getRGB(0,0,image.getWidth(),image.getHeight(),null,0,image.getWidth());
            int p;
            int pixel;

            Color color;
            int r;
            int g;
            int b;

            for (int i = 0 ; i < image.getWidth() ; i++){
                for (int j = 0 ; j < image.getHeight() ; j++){
                    p = j * image.getWidth() + i;
                    pixel = rgbArray[ p ];
                    color = new Color(pixel);
                    r = color.getRed();
                    g = color.getGreen();
                    b = color.getBlue();
                    if ( r <= 128 && g <= 128 && b <= 128 ){
                        pic[p] = 1;
                    }
                    else {
                        pic[p] = -1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    int[] getPic() {
        return pic;
    }
}