package com.dongxl.webpanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dongxl.animwebp.view.AnimatedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    AnimatedImageView imageview;
    @BindView(R.id.bottom1)
    Button bottom1;
    @BindView(R.id.bottom2)
    Button bottom2;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.bottom3)
    Button bottom3;
    @BindView(R.id.bottom4)
    Button bottom4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        imageview.setAutoPlay(true);//set资源后是否自动播放
    }

    @OnClick({R.id.bottom1, R.id.bottom2, R.id.bottom3, R.id.bottom4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottom1:
                //播放1次
                playOnly();
                break;
            case R.id.bottom2:
                //无限循环
                playInf();
                break;
            case R.id.bottom3:
                //按次播放
                try {
                    String numStr = edittext.getText().toString();
                    int count = Integer.parseInt(numStr);
                    playFinite(count);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bottom4:
                //默认播放
                if (imageview.isRunning()) {
                    imageview.stopAnimation();
                    imageview.animatedDestroy();
                    imageview.setImageDrawable(null);
                }
                imageview.setLoopDefault();
                imageview.setImageResource(R.mipmap.icon_live_chongaizhi_anim);
                if (!imageview.isAutoPlay()) {
                    imageview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            imageview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            imageview.startAnimation();
                        }
                    });
                }
                break;
        }
    }

    /**
     * 播放1次
     */
    private void playOnly() {
        if (imageview.isRunning()) {
            imageview.stopAnimation();
            imageview.animatedDestroy();
            imageview.setImageDrawable(null);
        }
//        imageview.setLoopCount(1);
//        imageview.setLoopFinite();
        imageview.setOnFinishedListener(new AnimatedImageView.OnFinishedListener() {
            @Override
            public void onFinished() {
                Toast.makeText(MainActivity.this, "播放结束", Toast.LENGTH_SHORT).show();
            }
        });
        imageview.setImageResource(R.mipmap.icon_master_following);
        if (!imageview.isAutoPlay()) {
            imageview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    imageview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    imageview.startAnimation();
                }
            });
        }
    }

    /**
     * 无限循环
     */
    private void playInf() {
        if (imageview.isRunning()) {
            imageview.stopAnimation();
            imageview.animatedDestroy();
            imageview.setImageDrawable(null);
        }
        imageview.setLoopInf();
        imageview.setOnFinishedListener(new AnimatedImageView.OnFinishedListener() {
            @Override
            public void onFinished() {
                Toast.makeText(MainActivity.this, "播放结束", Toast.LENGTH_SHORT).show();
            }
        });
        imageview.setImageResource(R.mipmap.icon_master_follow);
        if (!imageview.isAutoPlay()) {
            imageview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    imageview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    imageview.startAnimation();
                }
            });
        }
    }

    /**
     * 按次播放
     *
     * @param count
     */
    private void playFinite(int count) {
        if (imageview.isRunning()) {
            imageview.stopAnimation();
            imageview.animatedDestroy();
            imageview.setImageDrawable(null);
        }
        imageview.setLoopCount(count);
        imageview.setLoopFinite();
        imageview.setOnFinishedListener(new AnimatedImageView.OnFinishedListener() {
            @Override
            public void onFinished() {
                Toast.makeText(MainActivity.this, "播放结束", Toast.LENGTH_SHORT).show();
            }
        });
        imageview.setImageResource(R.mipmap.icon_video_send_gift);
        if (!imageview.isAutoPlay()) {
            imageview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    imageview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    imageview.startAnimation();
                }
            });
        }
    }


}
