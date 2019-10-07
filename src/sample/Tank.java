package sample;

import javafx.scene.image.Image;

public class Tank extends Entity{
  private Track tracksBelow;
  private TankColor color;
  private GameMap map;
  private Direction dir = Direction.UP;
  private Direction lastDir = Direction.UP;

  public Tank(int type, TankColor color){
    this.color = color;
    tracksBelow = new Track(null, null, color);
    Image img = TextureReader.getImageFromTexture(this.getClass().getResource("images/Tanks_" + color.toString() + ".png").toString(), type);
    setImage(img);
  }

  public void addY(double value){
    int curGridY = getGridY();
    super.setY(getY() + value);
    tracksBelow.setY(tracksBelow.getY() + value);

    if(curGridY != getGridY()){
      if(dir == Direction.UP) map.spawnAtCell(getGridX(), getGridY() + 1, new Track(lastDir.opposite(), dir, color));
      else if(dir == Direction.DOWN) map.spawnAtCell(getGridX(), getGridY() - 1, new Track(lastDir.opposite(), dir, color));
    }
  }

  public void addX(double value){
    int curGridX = getGridX();
    super.setX(getX() + value);
    tracksBelow.setX(tracksBelow.getX() + value);
    if(curGridX != getGridX()){
      if(dir == Direction.RIGHT) map.spawnAtCell(getGridX() - 1, getGridY(), new Track(lastDir.opposite(), dir, color));
      else if(dir == Direction.LEFT) map.spawnAtCell(getGridX() + 1, getGridY(), new Track(lastDir.opposite(), dir, color));
    }
  }

  /**
   *
   * @param newDir Direction the Tank moved to relative to current direction; only valid arguments are LEFT/RIGHT
   * @throws IllegalArgumentException when a faulty direction is given
   */
  public void turn(Direction newDir){
    if(newDir != dir.opposite()){
      lastDir = dir;

      dir = newDir;

      super.setRotate(newDir.toAngle());
    }
  }

  public void move(){
    switch(dir){
      case UP:
        addY(0 - map.getBoxBoundaries());
        tracksBelow.setRotate(0);
        break;
      case DOWN:
        addY(map.getBoxBoundaries());
        tracksBelow.setRotate(180);
        break;
      case RIGHT:
        addX(map.getBoxBoundaries());
        tracksBelow.setRotate(90);
        break;
      case LEFT:
        addX(0 - map.getBoxBoundaries());
        tracksBelow.setRotate(270);
        break;
    }
    lastDir = dir;
  }

  @Override
  public void setMap(GameMap map){
    this.map = map;
    super.setMap(map);
  }

  public Track getTracksBelow(){
    return tracksBelow;
  }
}