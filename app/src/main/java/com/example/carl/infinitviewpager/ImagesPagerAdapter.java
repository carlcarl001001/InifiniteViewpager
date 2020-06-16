package com.example.carl.infinitviewpager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ImagesPagerAdapter extends PagerAdapter {
    private List<ImageView> mImageViewList;
    private ViewPager viewPager;
    private Context context;

    private ImageView mImageView;

    public ImagesPagerAdapter(List<ImageView> simpleDraweeViewList, ViewPager viewPager, Context context) {
        this.mImageViewList = simpleDraweeViewList;
        this.viewPager = viewPager;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mImageViewList.size();
    }

    //删除指定位置的页面；适配器负责从view容器中删除view，然而它只保证在finishUpdate(ViewGroup)返回时才完成。
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        log("into destroyItem");
        // 把ImageView从ViewPager中移除掉
        viewPager.removeView(mImageViewList.get(position));
        //super.destroyItem(container, position, object);
    }

    //是否获取缓存
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    //实例化Item
    //在指定的位置创建页面；适配器负责添加view到这个容器中，然而它只保证在finishUpdate(ViewGroup)返回时才完成。
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        log("into instantiateItem");
        mImageView = mImageViewList.get(position);
        viewPager.addView(mImageView);
        return mImageView;
    }

//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }

    //无论是创建view添加到容器中  还是 销毁view 都是在此方法结束之后执行的
    @Override
    public void finishUpdate(ViewGroup container) {
        log("into finishUpdate");
        super.finishUpdate(container);

        int position = viewPager.getCurrentItem();

        if (position == 0) {
            position = mImageViewList.size() - 2;
            viewPager.setCurrentItem(position,false);
        } else if (position == mImageViewList.size() - 1) {
            position = 1;
            viewPager.setCurrentItem(position,false);
        }
    }

    private void log(String str){
        Log.i("chen",str);
    }

/*    private int mChildCount = 0;
    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }
    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            Log.e("image","getItemPosition");
            return POSITION_NONE;
        }
        return super.getItemPosition(object);*/


}
