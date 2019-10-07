package sample;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class TextureReader{
  public static Image getImageFromTexture(String filePath, int number){
    Image img = new Image(filePath);
    PixelReader reader = img.getPixelReader();

    int width = (int) img.getWidth()/3;
    int height = (int) img.getHeight()/3;

    WritableImage cutOut = new WritableImage(reader, number%3 * width, number/3 * height, width, height);
    return cutOut;
  }
}