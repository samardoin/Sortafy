package sorts; 

import sortafy.Sortafy;
import tools.ToolBox;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import jukebox.Playable;

public class GnomeSort implements Sort, Playable{ 
    public GnomeSort(GraphicsContext graphicscontext){
        this.ctx = graphicscontext;
        this.speed = (long) Math.pow(10, 9)/100;
    }
 //Playable=====================================================================
    private String status = "OFF";
    private GraphicsContext ctx;
    private long speed;
    private AnimationTimer animationTimer;
    
    @Override
    public void start() {//Jukebox calls start
        //init
        this.status = "ON";
        this.list = new int[200];
        for (int i = 0; i < list.length; i++) list[i] = i;
        randomizeList();
        draw();
		
        //setup animation
		
        animationTimer = new AnimationTimer() {
            long prevUpdate = 0;
            @Override public void handle(long now) {
                long elapsedNanoSeconds = now - prevUpdate;
                if (elapsedNanoSeconds >= (speed)) {
                    step();
                    draw();
                    prevUpdate=now;
                    if (status.equalsIgnoreCase("ON")==false) {
                        if (status.equalsIgnoreCase("STOPPING")) {
                            GnomeSort.this.stop();
                        }
                    }
                }		
            }
        };
        animationTimer.start();
    }
    
    @Override
    public void stop() {
        //reset
        cmp1 = -1;
        cmp2 = -1;

        animationTimer.stop();
        this.status = "OFF";
        System.out.println("GnomeSort OFF");
    }
	
    @Override
    public void pause() {
            // TODO Auto-generated method stub
    }
        
    @Override public String getStatus() {return status;}
    @Override public void setSpeed(long speed) {this.speed = speed;}
    @Override public long getSpeed() {return this.speed;}

//Sort==========================================================================
    private int[] list;
    @Override
    public void setList() {
            // TODO Auto-generated method stub
    }

    @Override
    public void getList() {
            // TODO Auto-generated method stub
    }
    
    int step_index = 0;
    @Override
    public void step() {
        if (step_index == 0) step_index++;

        if (step_index >= list.length) {
            this.status="STOPPING";
            System.out.println("GnomeSort STOPPING");
            return;
        }
        //else

        if (list[step_index-1] > list[step_index]) {
            int temp = list[step_index];
            list[step_index] = list[step_index-1];
            list[step_index-1]=temp;
            cmp1 = -1;
            cmp2 = -1;
            match1 = step_index;
            match2 = step_index-1;
            step_index--;
        }
        else {
            cmp1 = step_index;
            cmp2 = step_index-1;
            match1 = -1;
            match2 = -1;
            step_index++;
        }
    }
    
    @Override
    public void isSorted() {
            // TODO Auto-generated method stub
    }

    private void randomizeList() {
        int maxRand = list.length*list.length*list.length;
        int minRand = list.length*list.length;
        int rand = ToolBox.getRanInt(minRand, maxRand);

        for (int i = 0; i < rand; i++) {
            //swap
            int l1 = ToolBox.getRanInt(0, this.list.length -1);
            int l2 = ToolBox.getRanInt(0, this.list.length -1);
            if (l1 == l2) continue;
            //else
            int temp = list[l1];
            list[l1]=list[l2];
            list[l2]=temp;
        }
    }
    
//Self==========================================================================
    int cmp1 = -1;
    int cmp2 = -1;
    int match1 = -1;
    int match2 = -1;
	
    public void draw() {
        double space = 0.5;
        double blockWidth = Sortafy.CANVAS_WIDTH/list.length - space;
        double blockHeightCorrector = Sortafy.CANVAS_HEIGHT/ToolBox.arrayMax(list);

        double x = 0.0;
        double y = Sortafy.CANVAS_HEIGHT;

        ctx.setFill(Sortafy.bg_color);
        ctx.fillRect(0, 0, Sortafy.CANVAS_WIDTH, Sortafy.CANVAS_HEIGHT);

        for (int i = 0 ; i < list.length;i++) {
            ctx.setFill(Color.BLACK);
            if (i == cmp1) ctx.setFill(Color.BLUE);
            else if (i == cmp2) ctx.setFill(Color.AQUA);
            else if (i == match1 || i == match2) ctx.setFill(Color.YELLOW);

            double h = (double)list[i]*blockHeightCorrector;
            ctx.fillRect(x, y-h, blockWidth, h);
            x+=blockWidth+space;
        }

    }
    
} 