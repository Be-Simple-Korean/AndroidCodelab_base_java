package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChargeActivity extends AppCompatActivity {

    private int mLevel = 0;
    private ImageView ivBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);

        ivBattery = findViewById(R.id.iv_battery);
    }

    /**
     * 감소 버튼 클릭
     *
     * @param view
     */
    public void decreaseBattery(View view) {
        if (mLevel == 0) {
            return;
        }
        mLevel--;
        setImageResource();
    }

    /**
     * 증가 버튼 클릭
     *
     * @param view
     */
    public void increaseBattery(View view) {
        if (mLevel == 6) return;
        mLevel++;
        setImageResource();
    }

    /**
     * 레벨에 따른 이미지 세팅
     */
    private void setImageResource() {
        switch (mLevel) {
            case 0:
                ivBattery.setImageResource(R.drawable.baseline_battery_0_bar_24);
                break;
            case 1:
                ivBattery.setImageResource(R.drawable.baseline_battery_1_bar_24);
                break;
            case 2:
                ivBattery.setImageResource(R.drawable.baseline_battery_2_bar_24);
                break;
            case 3:
                ivBattery.setImageResource(R.drawable.baseline_battery_3_bar_24);
                break;
            case 4:
                ivBattery.setImageResource(R.drawable.baseline_battery_4_bar_24);
                break;
            case 5:
                ivBattery.setImageResource(R.drawable.baseline_battery_5_bar_24);
                break;
            case 6:
                ivBattery.setImageResource(R.drawable.baseline_battery_6_bar_24);
                break;

        }
    }
}