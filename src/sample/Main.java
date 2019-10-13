package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    boolean w;
    boolean a;
    boolean s;
    boolean d;
    static PropReader reader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        reader = new PropReader(new File(this.getClass().getResource("tanks.properties").getFile()));
        int boxBoundaries = Integer.parseInt(reader.get("boxBoundaries"));
        GameMap map = new GameMap(Integer.parseInt(reader.get("mapxCells")), Integer.parseInt(reader.get("mapyCells")), boxBoundaries);
        Parent root = map.getPane();
        Scene scene = new Scene(root, boxBoundaries * map.getXCells(), boxBoundaries * map.getYCells());
        Tank tank = new Tank(8, TankColor.CYAN);

        if(reader.get("controlScheme").equalsIgnoreCase("snake")){
            new GameCycle(500, () -> {
                if(d && !a) tank.turn(Direction.RIGHT);
                else if(a && !d) tank.turn(Direction.LEFT);
                else if(w && !s) tank.turn(Direction.UP);
                else if(s && !w) tank.turn(Direction.DOWN);
                tank.move();

                w = false;
                a = false;
                s = false;
                d = false;
            });

            scene.setOnKeyPressed(e -> {
                if(e.getCode().getName().equals("D")){
                    d = true;
                }else if(e.getCode().getName().equals("A")){
                    a = true;
                }else if(e.getCode().getName().equals("W")){
                    w = true;
                }else if(e.getCode().getName().equals("S")){
                    s = true;
                }
            });
        }else{
            new GameCycle(500, () -> {
                if(d && !a) tank.turn(tank.getDirection().clockwiseNext());
                else if(a && !d) tank.turn(tank.getDirection().counterClockwiseNext());
                tank.move();

                a = false;
                d = false;
            });

            scene.setOnKeyPressed(e -> {
                if(e.getCode().getName().equals("D")){
                    d = true;
                }else if(e.getCode().getName().equals("A")){
                    a = true;
                }
            });
        }

        /*scene.setOnKeyReleased(e ->{
            if(e.getCode().getName().equals("D")){
                d = false;
            }else if(e.getCode().getName().equals("A")){
                a = false;
            }else if(e.getCode().getName().equals("W")){
                w = false;
            }else if(e.getCode().getName().equals("S")){
                s = false;
            }
        });*/

        map.spawnAtCell( 5,5, tank);

        primaryStage.setTitle("Definitely not War.IO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static PropReader getReader(){
        return reader;
    }
}