package com.xiayiye.customerview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import com.xiayiye.customerview.R;

import java.util.Random;

public class MyAnimView extends View {

    private Paint paint;
    private Context context;
    private float location = 0;
    private MyThread myThread;
    private float number = 0;
    private String paintText = "自定义View";
    private int paintColor = 0x66ff00ff;
    private float arcLeft;
    private float arcTop;
    private float arcRight;
    private float arcBottom;
    /**
     * 是否绘制的属性
     */
    private boolean isCanvas = true;

    public MyAnimView(Context context) {
        this(context, null);
    }

    public MyAnimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyAnimView);
        paintText = typedArray.getString(R.styleable.MyAnimView_paintText);
        paintColor = typedArray.getColor(R.styleable.MyAnimView_paintColor, Color.RED);
        arcLeft = typedArray.getFloat(R.styleable.MyAnimView_arcLeft, 0);
        arcTop = typedArray.getFloat(R.styleable.MyAnimView_arcTop, 200);
        arcRight = typedArray.getFloat(R.styleable.MyAnimView_arcRight, 1080);
        arcBottom = typedArray.getFloat(R.styleable.MyAnimView_arcBottom, 1280);
        isCanvas = typedArray.getBoolean(R.styleable.MyAnimView_isCanvas, true);
        //释放资源
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint = new Paint();
        paint.setTextSize(48);
        Random random = new Random();
//        paint.setColor(0x66ff00ff);
        paint.setColor(paintColor);
//        paint.setColor(Color.argb(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        canvas.drawText(paintText, location, 48, paint);
        canvas.drawCircle(150, 200, 100, paint);
        canvas.drawArc(new RectF(arcLeft, arcTop, arcRight, arcBottom), 0, number, true, paint);
        if (myThread == null) {
            myThread = new MyThread();
            myThread.start();
        }
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("打印线程", location + "");
                if (location > getWidth()) {
                    location = -paint.measureText(str);
                }
                location++;
                postInvalidate();
            }
        }) {
        }.start();*/
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (isCanvas) {
                    if (location > getWidth()) {
                        location = -paint.measureText(paintText);
                    }
                    location++;
                    if (number > 360) {
                        number = 0;
                    }
                    number++;
                    //根据属性是否绘制来判断是否继续绘制
                    postInvalidate();
                }
                SystemClock.sleep(10);
            }
        }
    }

    /**
     * 设置跑马灯文字的方法
     *
     * @param text 返回文字
     */
    public void setDrawText(String text) {
        paintText = text;
    }

    /**
     * 是否绘制的方法
     *
     * @param isCanvas 绘制
     */
    public void setCanvas(boolean isCanvas) {
        this.isCanvas = isCanvas;
    }
}
