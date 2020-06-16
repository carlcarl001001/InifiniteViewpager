# InifiniteViewpager

![image](https://github.com/carlcarl001001/InifiniteViewpager/blob/master/result.gif)

![image](https://github.com/carlcarl001001/InifiniteViewpager/blob/master/1.png)

![image](https://github.com/carlcarl001001/InifiniteViewpager/blob/master/2.png)

keypoint:

        int[] imgaeIds = new int[]{R.drawable.a3,R.drawable.a1, R.drawable.a2, R.drawable.a3,R.drawable.a1};

        String[] titles = new String[]{"a3","a1","a2","a3","a1"};//new String[imageInfoList.size()];
        
        ...
        
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
