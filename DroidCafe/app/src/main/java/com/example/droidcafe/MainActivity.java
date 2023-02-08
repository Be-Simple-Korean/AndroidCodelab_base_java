package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 토스트 메시지 띄우기
     *
     * @param message
     */
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * 이미지 뷰 클릭 메소드
     *
     * @param view
     */
    public void onImageClick(View view) {
        String msg = "";
        switch (view.getId()) {
            case R.id.iv_donut:
                msg = getString(R.string.donut_order_message);
                break;
            case R.id.iv_icecream:
                msg = getString(R.string.ice_cream_order_message);
                break;
            case R.id.iv_froyo:
                msg = getString(R.string.froyo_order_message);
                break;
        }
        displayToast(msg);
    }

    /**
     * 플로팅 액션 버튼 클릭 메소드
     *
     * @param view
     */
    public void goNext(View view) {
        startActivity(new Intent(this, OrderActivity.class));
    }
}