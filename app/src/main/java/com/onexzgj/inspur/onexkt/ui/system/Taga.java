package com.onexzgj.inspur.onexkt.ui.system;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.onexzgj.inspur.onexkt.R;
import com.onexzgj.inspur.onexkt.model.SystemTree;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * des：
 * author：onexzgj
 * time：2020-03-09
 */
public class Taga extends TagAdapter {

    private final List<SystemTree> mDatas;

    public Taga(List<SystemTree> datas) {
        super(datas);
        mDatas = datas;
    }

    @Override
    public View getView(FlowLayout parent, int position, Object o) {

        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, null, false);
        textView.setText(((SystemTree) o).getName());

        return textView;
    }
}
