package com.example.cardwithcolor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cardwithcolor.data.Sports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Sports> mSportsData = new ArrayList<>();

    private SportsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mSportsData = (ArrayList<Sports>) savedInstanceState.getSerializable("sportsData");
            initRecyclerView();
        } else {
            initRecyclerView();
            initBannerData();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("sportsData", mSportsData);
    }

    private void initBannerData() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.sports_images);
        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);
        mSportsData.clear();
        for (int i = 0; i < sportsList.length; i++) {
            mSportsData.add(new Sports(sportsList[i], sportsInfo[i],
                    typedArray.getResourceId(i, 0)));
        }
        typedArray.recycle();
        mAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        int gridCount = getResources().getInteger(R.integer.grid_column_count);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridCount));
        int swipeDirs = 0;
        if(gridCount < 2){
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        mAdapter = new SportsAdapter(this, mSportsData);
        mRecyclerView.setAdapter(mAdapter);
        //RecyclerView?????? ???????????? ???????????? ???????????? ????????? ????????????
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(//????????? ??? ??????
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                                ItemTouchHelper.DOWN | ItemTouchHelper.UP,swipeDirs //????????????
                        ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        int from = viewHolder.getAdapterPosition();
                        int to = target.getAdapterPosition();
                        Log.e("data", "from --> " + from + " / to --> " + to);
                        Collections.swap(mSportsData, from, to);
                        mAdapter.notifyItemMoved(from, to);
                        return true;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        mSportsData.remove(viewHolder.getAdapterPosition());
                        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                });
        helper.attachToRecyclerView(mRecyclerView);
    }

    public void resetSports(View view) {
        initBannerData();
    }
}