package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class GameCycle{
  Timeline timeline;

  public GameCycle(int durationMillis, Runnable runnable){
    timeline = new Timeline(new KeyFrame(Duration.millis(durationMillis), new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        runnable.run();
      }
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  public void pause(){
    timeline.pause();
  }

  public void resume(){
    timeline.play();
  }
}