package su.app.album;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TouchHandler implements View.OnTouchListener{
    MainActivity a;
    int i=0;

    TouchHandler(MainActivity a){
        this.a=a;
    }
    float x1 = 0.0f;
    float y1 = 0.0f;
    float x2 = 0.0f;
    float y2 = 0.0f;
    float x3 = 0.0f;
    float y3 = 0.0f;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int action = motionEvent.getAction();
        if(action == MotionEvent.ACTION_DOWN){

                x1=motionEvent.getX();
                y1=motionEvent.getY();
                Log.i("Touch DOWN(x,y) :","("+x1+","+y1+")");
        }else if(action == MotionEvent.ACTION_UP){
            x2=motionEvent.getX();
            y2=motionEvent.getY();
            Log.i("Touch UP(x,y) :","("+x2+","+y2+")");

            float valueX=x1-x2;
                if(valueX<0) {
                    a.iv.setBackgroundResource(a.IMGS[i--]);
                    if(i<0) i=a.IMGS.length-1;
                }else if(x1==x2){

                }else {
                    a.iv.setBackgroundResource(a.IMGS[i++]);
                    if(i>=a.IMGS.length) i=0;
                }
        }else if (action == MotionEvent.ACTION_MOVE){
            x3=motionEvent.getX();
            y3=motionEvent.getY();
            Log.i("Touch MOVE(x,y) :","("+x3+","+y3+")");
        }else{
            
        }
        return true;
    }
}

