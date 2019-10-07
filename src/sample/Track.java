package sample;

import javafx.scene.image.Image;

public class Track extends Entity{
  private GameMap map;

  public Track(Direction start, Direction end, TankColor color){
    int type = -1;

    if(start == null || end == null) type = 4;
    else if(start == Direction.DOWN && end == Direction.RIGHT || start == Direction.RIGHT && end == Direction.DOWN) type = 0;
    else if(start == Direction.LEFT && end == Direction.RIGHT) type = 1;
    else if(start == Direction.LEFT && end == Direction.DOWN || start == Direction.DOWN && end == Direction.LEFT) type = 2;
    else if(start == Direction.DOWN && end == Direction.UP) type = 3;
    else if(start == Direction.UP && end == Direction.DOWN) type = 5;
    else if(start == Direction.RIGHT && end == Direction.UP || start == Direction.UP && end == Direction.RIGHT) type = 6;
    else if(start == Direction.RIGHT && end == Direction.LEFT) type = 7;
    else if(start == Direction.UP && end == Direction.LEFT || start == Direction.LEFT && end == Direction.UP) type = 8;
    else throw new IllegalArgumentException("Directions did not fit!");

    Image img = TextureReader.getImageFromTexture(this.getClass().getResource("images/Tracks_" + color.toString() + ".png").toString(), type);
    setImage(img);
  }

  @Override
  public void setMap(GameMap map){
    this.map = map;
    super.setMap(map);
  }
}
