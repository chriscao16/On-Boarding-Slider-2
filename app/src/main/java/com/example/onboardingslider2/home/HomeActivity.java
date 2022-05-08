package com.example.onboardingslider2.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.onboardingslider2.LotActivity;
import com.example.onboardingslider2.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    // Variables
    private HomeAdapter homeAdapter;
    private Button button_select;


    //Initialization: Inflating view on create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupHomeItems();

        button_select = findViewById(R.id.button_select);
        ViewPager2 homeViewPager = findViewById(R.id.homeViewPager);
        homeViewPager.setAdapter(homeAdapter);

//Callback interface for responding to changing state of the selected page. So in this instance we want to change an aspect of the page based on the current page of x displayed.
        homeViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });

        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LotActivity.class));
                finish();
            }
        });

    }

    //Sets the Title, Description, Image for each page of the application. These items are then updated to the home adapter.
    private void setupHomeItems() {
        List<HomeItem> homeItems = new ArrayList<>();

        HomeItem itemTask = new HomeItem();
        itemTask.setTitle("Current Lot Tasks");
        itemTask.setImage(R.drawable.task);

        HomeItem itemCamera = new HomeItem();
        itemCamera.setTitle("Camera");

        itemCamera.setImage(R.drawable.camera);

        HomeItem itemGallery = new HomeItem();
        itemGallery.setTitle("Gallery");

        itemGallery.setImage(R.drawable.gallery);

        HomeItem itemRepository = new HomeItem();
        itemRepository.setTitle("Repository");
        itemRepository.setImage(R.drawable.repository);

        homeItems.add(itemTask);
        homeItems.add(itemCamera);
        homeItems.add(itemGallery);
        homeItems.add(itemRepository);

        homeAdapter = new HomeAdapter(homeItems);
    }
}
