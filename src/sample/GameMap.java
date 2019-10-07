package sample;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class GameMap{
  private GridPane grid;
  private Pane pane;
  private int xCells;
  private int yCells;
  private int boxBoundaries;

  public GameMap (int xCells, int yCells, int boxBoundaries){
    this.boxBoundaries = boxBoundaries;
    this.xCells = xCells;
    this.yCells = yCells;

    grid = new GridPane();
    grid.setPrefWidth(xCells * boxBoundaries);
    grid.setPrefHeight(yCells * boxBoundaries);
    grid.setGridLinesVisible(Boolean.parseBoolean(Main.getReader().get("mapDrawGrid")));

    for(int i = 0; i < xCells; i++)
      grid.getColumnConstraints().add(new ColumnConstraints(boxBoundaries));
    for(int i = 0; i < yCells; i++)
      grid.getRowConstraints().add(new RowConstraints(boxBoundaries));

    pane = new Pane();
    pane.setPrefWidth(xCells * boxBoundaries);
    pane.setPrefHeight(yCells * boxBoundaries);
    pane.getChildren().add(grid);
  }

  public void spawnAtCell(int x, int y, Entity entity){
    if(entity instanceof Tank) spawnAtCell(x, y, ((Tank) entity).getTracksBelow());

    double entityWidth = entity.getBoundsInParent().getMaxX() - entity.getBoundsInParent().getMinX();
    double entityHeight = entity.getBoundsInParent().getMaxY() - entity.getBoundsInParent().getMinY();

    pane.getChildren().add(entity);
    entity.setScaleX(boxBoundaries/entityWidth);
    entity.setScaleY(boxBoundaries/entityHeight);
    entity.setX(x * boxBoundaries);
    entity.setY(y * boxBoundaries);
    entity.setMap(this);

    //this is necessary because scaling keeps the pivot in the middle of the former size, we have to change that
    entity.setTranslateX(- entityWidth/2 + (entity.getBoundsInParent().getMaxX() - entity.getBoundsInParent().getMinX())/2);
    entity.setTranslateY(- entityHeight/2 + (entity.getBoundsInParent().getMaxY() - entity.getBoundsInParent().getMinY())/2);
  }

  public Pane getPane(){
    return pane;
  }

  public int getXCells(){
    return xCells;
  }

  public int getYCells(){
    return yCells;
  }

  public int getBoxBoundaries(){
    return boxBoundaries;
  }
}
