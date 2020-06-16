package com.example.carl.infinitviewpager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.ImageDecoder;
import android.os.Build;
import android.os.Bundle;



import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.graphics.ImageDecoder.ImageInfo;
import java.util.ArrayList;
import java.util.List;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 图片轮播控件
    private ViewPager mViewPager;
    private TextView mTvPagerTitle;
    private LinearLayout mLineLayoutDot;
    private ImageCarousel imageCarousel;
    private List<View> dots;//小点

    // 图片数据，包括图片标题、图片链接、数据、点击要打开的网站（点击打开的网页或一些提示指令）
    private List<ImageInfo> imageInfoList;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //initEvent();
        imageStart();

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化控件
     */
    private void initView() {

        mViewPager = findViewById(R.id.viewPager);
        mTvPagerTitle = findViewById(R.id.tv_pager_title);
        mLineLayoutDot = findViewById(R.id.lineLayout_dot);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void imageStart() {
        //设置图片轮播
        int[] imgaeIds = new int[]{R.drawable.a3,R.drawable.a1, R.drawable.a2, R.drawable.a3,R.drawable.a1};

        String[] titles = new String[]{"a3","a1","a2","a3","a1"};//new String[imageInfoList.size()];
        List<ImageView> imageViewList = new ArrayList<>();

        for (int i = 0; i < imgaeIds.length; i++) {

            //titles[i] = imageInfoList.get(i).getTitle();

            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
            imageView.setImageResource(imgaeIds[i]);
            imageViewList.add(imageView);

        }

        dots = addDots(mLineLayoutDot, fromResToDrawable(this, R.drawable.ic_dot_focused), imageViewList.size());
        imageCarousel = new ImageCarousel(this, mViewPager, mTvPagerTitle, dots, 5000);
        imageCarousel.init(imageViewList, titles);
                //.startAutoPlay();
        imageCarousel.start();

    }


    /**
     * 动态添加一个点
     *
     * @param linearLayout 添加到LinearLayout布局
     * @param backgount    设置
     * @return 小点的Id
     */
    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private int addDot(final LinearLayout linearLayout, Drawable backgount) {
        final View dot = new View(this);
        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dotParams.width = 16;
        dotParams.height = 16;
        dotParams.setMargins(4, 0, 4, 0);
        dot.setLayoutParams(dotParams);
        dot.setBackground(backgount);
        dot.setId(View.generateViewId());
        ((Activity) this).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                linearLayout.addView(dot);
            }
        });

        return dot.getId();
    }


    /**
     * 资源图片转Drawable
     *
     * @param context 上下文
     * @param resId   资源ID
     * @return 返回Drawable图像
     */
    public static Drawable fromResToDrawable(Context context, int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

    /**
     * 添加多个轮播小点到横向线性布局
     *
     * @param linearLayout 线性横向布局
     * @param backgount    小点资源图标
     * @param number       数量
     * @return 返回小点View集合
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private List<View> addDots(final LinearLayout linearLayout, Drawable backgount, int number) {
        List<View> dots = new ArrayList<>();
        for (int i = 2; i < number; i++) {    // 注意这里的 i 从 2 开始，只画出5个点

            int dotId = addDot(linearLayout, backgount);
            dots.add(findViewById(dotId));

        }
        return dots;
    }


}
