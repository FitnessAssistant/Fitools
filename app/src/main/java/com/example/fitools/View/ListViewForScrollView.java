package com.example.fitools.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/*
  * 本类是为了解决如下问题：
  *
  * Home标签页需要整体实现长页面，因此需要用到ScrollView
  * 用ScrollView的话Home标签页的效果文件，layout_home.xml
  * 该效果文件有用到用ListView来实现动态商品的代码
  * 而ScrollView和ListView的滚动效果冲突，无法正常显示
  * 故此类为自定义可适应ScrollView的ListView的自定义类
  * 通过自定义一个类继承自ListView，通过重写其onMeasure方法，达到对ScrollView适配的效果
  * 三个构造方法完全不用动，只要重写onMeasure方法。
  *
  * 产生的新问题：
  * 默认显示的首项是ListView，需要手动把ScrollView滚动至最顶端
  * 解决方法见HomeFragment.java文件
  */

public class ListViewForScrollView extends ListView {
    public ListViewForScrollView(Context context) {
        super(context);
    }

    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewForScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
      * 重写该方法，达到使ListView适应ScrollView的效果
      */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
