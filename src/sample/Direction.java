package sample;

public enum Direction{
  UP, DOWN, LEFT, RIGHT;

  public Direction opposite(){
    if(this == Direction.UP) return Direction.DOWN;
    else if(this == Direction.DOWN) return Direction.UP;
    else if(this == Direction.RIGHT) return Direction.LEFT;
    else return Direction.RIGHT;
  }

  public Direction clockwiseNext(){
    if(this == Direction.UP) return Direction.RIGHT;
    else if(this == Direction.RIGHT) return Direction.DOWN;
    else if(this == Direction.DOWN) return Direction.LEFT;
    else return Direction.UP;
  }

  public Direction counterClockwiseNext(){
    if(this == Direction.UP) return Direction.LEFT;
    else if(this == Direction.LEFT) return Direction.DOWN;
    else if(this == Direction.DOWN) return Direction.RIGHT;
    else return Direction.UP;
  }

  public double toAngle(){
    if(this == Direction.UP) return 0;
    else if(this == Direction.LEFT) return 270;
    else if(this == Direction.DOWN) return 180;
    else return 90;
  }
}