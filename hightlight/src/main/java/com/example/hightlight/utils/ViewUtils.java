package com.example.hightlight.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewUtils {
    private static final String FRAGMENT_CON = "NoSaveStateFrameLayout";


    public static Rect getLocationInView(View parent, View child) {// 在视图中  获取位置
        if (parent == null || child == null) {
            throw new IllegalArgumentException("子布局和父布局不能为空 ");//抛出“非法参数”异常
        }
        View decorView = null;
        Context context = child.getContext();
        if (context instanceof Activity) {
            decorView = ((Activity) context).getWindow().getDecorView();
        }
        Rect result = new Rect();
        Rect tmpRect = new Rect();

        View tmp = child;//指针
        if (child == parent) {
            child.getHitRect(result);
        }

        while (tmp != decorView && tmp != parent) {
            tmp.getHitRect(tmpRect);
            if (!tmp.getClass().equals(FRAGMENT_CON)) {
                result.left += tmpRect.left;
                result.top += tmpRect.top;
            }
            tmp = (View) tmp.getParent();
            if (tmp != null && tmp.getParent() != null && (tmp.getParent() instanceof ViewPager)) {
                tmp = (View) tmp.getParent();
            }
        }

        result.right = result.left + child.getMeasuredWidth();
        result.bottom = result.top + child.getMeasuredHeight();
        return result;
    }
}
