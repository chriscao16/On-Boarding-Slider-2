package com.example.onboardingslider2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

// Variables
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;
    private MaterialButton letsGetStartedButton;
    private Animation animation;

//Initialization: Inflating view on create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// Dynamic widgets that exist on the main layout
        layoutOnboardingIndicators = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);
        letsGetStartedButton = findViewById(R.id.letsGetStartedButton);


        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

// Initializes onboarding indicators to always start on first tab
        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

//Callback interface for responding to changing state of the selected page. So in this instance we want to change an aspect of the page based on the current page of x displayed.

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

//if the "Next" button is pressed (@id/buttonOnboardingAction), then add a count to the onboardingViewPager currentItem
        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem() + 1< onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else {}
            }
        });
//if the "LETS GET STARTED" button is pressed (@id/LetsGetStartedButton), then start the new activity class "HomActivity".

        letsGetStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }else {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }
            }
        });
    }

//Sets the Title, Description, Image for each page of the application. These items are then updated to the onboarding adapter.
    private void setupOnboardingItems() {
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem itemTask = new OnboardingItem();
        itemTask.setTitle("Current Lot Tasks");
        itemTask.setDescription("Displays the highest priority tasks for the current layer in the Presidents part build up." +
                " Additionally, there are tutorial links to guide you through every step");
        itemTask.setImage(R.drawable.task);

        OnboardingItem itemCamera = new OnboardingItem();
        itemCamera.setTitle("Camera");
        itemCamera.setDescription("Use camera feature to either document images of part defects, labview issues, etc." +
                " These images can then be uploaded to the SPI network for engineering review.");
        itemCamera.setImage(R.drawable.camera);

        OnboardingItem itemGallery = new OnboardingItem();
        itemGallery.setTitle("Gallery");
        itemGallery.setDescription("Use gallery feature to review images of part defects, labview issues, etc before uploading to SPI network for engineering review");
        itemGallery.setImage(R.drawable.gallery);

        OnboardingItem itemRepository = new OnboardingItem();
        itemRepository.setTitle("Repository");
        itemRepository.setDescription("Displays and logs errors that occur while application is active. These logs can then be uploaded to the SPI network for engineering" +
                "review and degbugging.");
        itemRepository.setImage(R.drawable.repository);

        onboardingItems.add(itemTask);
        onboardingItems.add(itemCamera);
        onboardingItems.add(itemGallery);
        onboardingItems.add(itemRepository);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

// This is the method that builds the empty indicators based on the number of pages present in the onboardingAdapter.
    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
               ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
                    ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }

// This is the method that places the highlighted indicator based on the current page in the array of empty indicators.
    private void setCurrentOnboardingIndicator(int index) {
        int childCount = layoutOnboardingIndicators.getChildCount();
        for(int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnboardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }

// Conditionals for when elements of the page are supposed to appear/disappear.
        if(index == onboardingAdapter.getItemCount() - 1) {
            buttonOnboardingAction.setVisibility(View.INVISIBLE);
            letsGetStartedButton.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bottom_anim);
            letsGetStartedButton.setAnimation(animation);

        } else {
            buttonOnboardingAction.setText("Next");
            buttonOnboardingAction.setVisibility(View.VISIBLE);
            letsGetStartedButton.setVisibility(View.INVISIBLE);
        }
    }
}

// this is a test for reverting a commit (test)