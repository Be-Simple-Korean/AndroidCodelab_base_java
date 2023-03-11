package com.example.notischeduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int JOB_ID = 0;
    private JobScheduler mScheduler;
    private Switch mDeviceIdleSwitch;
    private Switch mDeviceChargingSwitch;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 뷰 설정
     */
    private void initView() {
        mDeviceIdleSwitch = findViewById(R.id.idleSwitch);
        mDeviceChargingSwitch = findViewById(R.id.chargingSwitch);
        final TextView seekBarProgress = findViewById(R.id.seekBarProgress);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i > 0) {
                    seekBarProgress.setText(i + " s");
                } else {
                    seekBarProgress.setText("Not Set");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void scheduleJob(View view) {
        RadioGroup networkOptions = findViewById(R.id.rg_network);
        int selectNetworkId = networkOptions.getCheckedRadioButtonId(); // 체크한 라디오 버튼 id get
        int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE; // default
        switch (selectNetworkId) {
            case R.id.rb_no_network:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.rb_any_network:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.rb_wifi_network:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE); // 객체 생성
        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName()); // 식별자
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(selectedNetworkOption) //제약조건 전달
                .setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked())
                .setRequiresCharging(mDeviceChargingSwitch.isChecked());
        int seekBarInteger = mSeekBar.getProgress();
        boolean seekBarSet = seekBarInteger > 0;
        if (seekBarSet) {
            builder.setOverrideDeadline(seekBarInteger * 1000); // 밀리초 단위 -> 초단위 , 제약조건을 무시하고 해당 시간안에 실행시킴
        }
        boolean constraintSet = selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE || mDeviceChargingSwitch.isChecked() || mDeviceIdleSwitch.isChecked() || seekBarSet;
        if (constraintSet) {
            JobInfo myJobInfo = builder.build();
            mScheduler.schedule(myJobInfo);
            Toast.makeText(this, "Job Scheduled, job will run when the constraints are met.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please set at least one constraint", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelJobs(View view) {
        if (mScheduler != null) {
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}