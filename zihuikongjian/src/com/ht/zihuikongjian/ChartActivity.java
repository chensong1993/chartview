package com.ht.zihuikongjian;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import com.ht.zihuikongjian.widget.ChartView;

/**
 * Created by annuo on 2015/5/25.
 */
public class ChartActivity extends Activity {

    private SeekBar seekBar;
    private ChartView chartView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        chartView = (ChartView) findViewById(R.id.chart);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int oldData = progress * 1000;
                int newData = oldData + 5000;
                int[] oldArray = new int[7];
                int[] newArray = new int[7];
                for(int i = 0;i<7;i++){
                    oldArray[i] = oldData;
                    newArray[i] = newData;
                }
                chartView.setLastYearData(oldArray);
                chartView.setThisYearData(newArray);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}