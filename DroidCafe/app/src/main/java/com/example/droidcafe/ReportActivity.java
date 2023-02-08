package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    private CheckBox cbSoup;
    private CheckBox cbSprinkle;
    private CheckBox cbCrush;
    private CheckBox cbCherries;
    private CheckBox cbCookies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        cbSoup = findViewById(R.id.cb_soup);
        cbSprinkle = findViewById(R.id.cb_sprinkle);
        cbCrush = findViewById(R.id.cb_crush);
        cbCherries = findViewById(R.id.cb_cherries);
        cbCookies = findViewById(R.id.cb_cookies);

    }

    /**
     * 버튼 클릭 메소드
     *
     * @param view
     */
    public void showToast(View view) {
        Toast.makeText(this, "Toppings:" + getResult(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 결과 메시지 가져오기
     * @return
     */
    private String getResult(){
        String msg ="";
        if(cbSoup.isChecked()){
            msg+=getString(R.string.chocolate_soup)+" ";
        }
        if(cbSprinkle.isChecked()){
            msg+=getString(R.string.sprinkle)+" ";
        }
        if(cbCrush.isChecked()){
            msg+=getString(R.string.crushed_nuts)+" ";
        }
        if(cbCherries.isChecked()){
            msg+=getString(R.string.cherries)+" ";
        }
        if(cbCookies.isChecked()){
            msg+=getString(R.string.cookies);
        }
        return msg;
    }

}