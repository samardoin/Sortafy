package sortafy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sorts.GnomeSort;

public class Sortafy extends Application{
    public static final double  WINDOW_WIDTH  = 600, 
                                WINDOW_HEIGHT = 500, 
                                CANVAS_WIDTH  = WINDOW_WIDTH  - 50, 
                                CANVAS_HEIGHT = WINDOW_HEIGHT - 50;
    public static Color bg_color = Color.RED;

    private Canvas canvas;
    private GraphicsContext ctx;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        BorderPane borderPane = new BorderPane();

        canvas = new Canvas(CANVAS_WIDTH,CANVAS_HEIGHT);
        ctx = canvas.getGraphicsContext2D();

        borderPane.setCenter(canvas);

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
        //primaryStage.setResizable(false);

        ctx.setFill(bg_color);
        ctx.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        primaryStage.show();

        GnomeSort is = new GnomeSort(this.ctx);
        is.start();
    }
}
