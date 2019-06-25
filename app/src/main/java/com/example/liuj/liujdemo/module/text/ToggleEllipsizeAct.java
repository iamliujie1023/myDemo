package com.example.liuj.liujdemo.module.text;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.liuj.R;
import com.example.liuj.liujdemo.base.BaseActivity;

/**
 * @author liuj
 * @time 19-6-24
 */
public class ToggleEllipsizeAct extends BaseActivity {

    String mContent =
            "Weex 渲染引擎与 DSL 语法层是分开的，Weex 并不强依赖任何特定的前端框架。目前 Vue.js 和 Rax 这两个前端框架被广泛应用于 Weex 页面开发，同时 Weex 也对这两个前端框架提供了最完善的支持。Weex 的另一个主要目标是跟进流行的 Web 开发技术并将其和原生开发的技术结合，实现开发效率和运行性能的高度统一。在开发阶段，一个 Weex 页面就像开发普通网页一样；在运行时，Weex 页面又充分利用了各种操作系统的原生组件和能力。";
    boolean mIsExpand = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView textView = new TextView(this);
        Util.toggleEllipsize(this, textView, 2, mContent, "查看全部", R.color.blue, mIsExpand);
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsExpand) {
                    mIsExpand = false;
                    textView.setMaxLines(2);// 收起
                } else {
                    mIsExpand = true;
                    textView.setMaxLines(Integer.MAX_VALUE);// 展开
                }
                Util.toggleEllipsize(v.getContext(), textView, 2, mContent, "查看全部", R.color.blue, mIsExpand);
            }
        });
    }
}
