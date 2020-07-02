package com.example.zhouganglibrary.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by zhou_gang on 2019/4/16.
 */

public class GifSufaceView extends SurfaceView implements SurfaceHolder.Callback {
    private  Context mContext;
    // 监听
    private SurfaceHolder holder;
    // 影片类
    private Movie movie;
    // 输入流
    private InputStream is = null;
    // 缩放
    private float zoom = 1f;
    // 图片路径
    private String path;
    // 判断是否网络读取
    private boolean isNet = false;

    // 逐步播放
    private Handler handler = new Handler();
    private Runnable run = new Runnable() {

        @Override
        public void run() {
            // 不断绘制
            Canvas canvas = holder.lockCanvas();
            // 绘制的时候进行缩放比例,不影响下次绘图操作
            canvas.save();
            canvas.scale(zoom, zoom);
            movie.draw(canvas, 0, 0);
            canvas.restore();
            holder.unlockCanvasAndPost(canvas);
            // 开始绘制
            movie.setTime((int) (System.currentTimeMillis() % movie.duration()));
            handler.removeCallbacks(run);
            // 下次还用这个线程
            handler.postDelayed(run, 30);
        }
    };

    // 构造方法
    public GifSufaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            // 判断读取方法
            if (isNet) {
                is = new URL(path).openConnection().getInputStream();
            } else {
                // 本地读取文件
                is = getContext().getAssets().open(path);
            }
            // 读取流
            movie = Movie.decodeStream(is);
            // 设置SurfaceView的宽高
            int width = movie.width();
            int height = movie.height();
//            int width = SizeUtils.dp2px();
//            int height = movie.height();
            setMeasuredDimension((int) (width * zoom), (int) (height * zoom));
            // 播放gif的帧动画
            handler.post(run);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    // 初始化完成
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 读取影片流

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // SurfaceView被销毁时结束线程
        handler.removeCallbacks(run);
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setNet(boolean isNet) {
        this.isNet = isNet;
    }
}
