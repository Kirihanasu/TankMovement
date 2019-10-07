package sample;

import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView{
  private GameMap map;

  public void setMap(GameMap map){
    this.map = map;
  }

  public int getGridX(){
    return (int) getX() / map.getBoxBoundaries();
  }

  public int getGridY(){
    return (int) getY() / map.getBoxBoundaries();
  }
}
