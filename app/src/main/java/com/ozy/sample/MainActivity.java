package com.ozy.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ozy.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MarqueeView.ViewFactory<String> {

    private MarqueeView marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marqueeView = (MarqueeView) findViewById(R.id.marquee);

        List<String> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add("item==>>>" + i);
        }
        marqueeView.setNoticeList(items);
        marqueeView.setViewFactory(this);
        marqueeView.start();
    }

    @Override
    public View onCreateView(String o, int position) {

        View view  = LayoutInflater.from(this).inflate(R.layout.item_marquee,null,false);
        TextView tv = (TextView) view.findViewById(R.id.tv_title);
        tv.setText(o);

        return view;
    }
}
