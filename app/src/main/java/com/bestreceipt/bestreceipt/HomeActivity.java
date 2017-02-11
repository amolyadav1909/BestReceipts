package com.bestreceipt.bestreceipt;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.Arrays;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc4 on 08-02-2017.
 */

public class HomeActivity extends AppCompatActivity {
   @BindView(R.id.toolbar) Toolbar toolbar;
    RecyclerView recyclerView;
    Context context;
    ArrayList<Integer> alImage;
    RecyclerView.Adapter recyclerView_Adapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    String[] numbers = {
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_home_icon);
        alImage = new ArrayList<>(Arrays.asList(R.drawable.house, R.drawable.electronics, R.drawable.cart, R.drawable.car, R.drawable.kis, R.drawable.tshirt,R.drawable.entertainment,R.drawable.health,R.drawable.other));

//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(new FaceAdapter(this));
        //Change 2 to your choice because here 2 is the number of Grid layout Columns in each row.
        recyclerViewLayoutManager = new GridLayoutManager(context, 3);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerView_Adapter = new RecyclerViewAdapter(context,alImage);

        recyclerView.setAdapter(recyclerView_Adapter);
    }

}
