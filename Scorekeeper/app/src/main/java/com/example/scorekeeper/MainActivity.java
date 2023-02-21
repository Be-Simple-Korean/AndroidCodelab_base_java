package com.example.scorekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String STATE_SCORE_1 = "Team 1 Score";
    private final String STATE_SCORE_2 = "Team 2 Score";
    private TextView mScoreTop;
    private TextView mScoreBottom;
    private int mScore1 = 0;
    private int mScore2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("test", "oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreTop = findViewById(R.id.tv_score_top);
        mScoreBottom = findViewById(R.id.tv_score_bottom);

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            mScoreTop.setText(String.valueOf(mScore1));
            mScoreBottom.setText(String.valueOf(mScore2));
        }
    }

    /**
     * 메뉴 셍성
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    /**
     * 메뉴 항목 클릭
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode(); //setDefaultNightMode()로 설정한 모드 리턴
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate(); // 테마 변경이 적용 되도록 요청
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putInt(STATE_SCORE_1, Integer.parseInt(mScoreTop.getText().toString()));
        outState.putInt(STATE_SCORE_2, Integer.parseInt(mScoreBottom.getText().toString()));
        super.onSaveInstanceState(outState, outPersistentState);

    }

    /**
     * 감소 버튼 클릭 리스너
     *
     * @param view
     */
    public void decreaseScore(View view) {
        switch (view.getId()) {
            case R.id.ib_decrease_top:
                mScore1--;
                mScoreTop.setText(String.valueOf(mScore1));
                break;
            case R.id.ib_decrease_bottom:
                mScore2--;
                mScoreBottom.setText(String.valueOf(mScore2));
                break;
        }
    }

    /**
     * 스코어 증가
     *
     * @param view
     */
    public void increaseScore(View view) {
        switch (view.getId()) {
            case R.id.ib_increase_top:
                mScore1++;
                mScoreTop.setText(String.valueOf(mScore1));
                break;
            case R.id.ib_increase_bottom:
                mScore2++;
                mScoreBottom.setText(String.valueOf(mScore2));
                break;
        }
    }

    /**
     * 버튼 클릭
     * @param view
     */
    public void goCharge(View view) {
        startActivity(new Intent(this,ChargeActivity.class));
    }
}