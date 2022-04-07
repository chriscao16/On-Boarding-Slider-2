package com.example.onboardingslider2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Overview: The adapter is an object of a class that implements the Adapter interface. It acts as a link between a data set and an adapter view, an object of a class
//that extends the abstract AdapterView class. i.e the adapter view in this application would be viewpager_item.xml. Depending on the page of the ViewPager2,
//the adapter grabs specific indexed data from the model class i.e. ViewPagerItem and places them in to the corresponding part in the page layout i.e. viewpager_item


//Recycler view is designed to display long lists (or grids) of items. Say we want to display 100 items, the simple approach would be to create 100 views, one for each row and
//lay all of them out. However, that would be wasteful because maybe in the use case only 10 items could realistically fit on the screen. RecyclerView, instead creates only 10 views
//that are on the screen. As you scroll and the current instance changes, RecyclerView takes advantage of the fact that as you scroll and new rows come onto the screen old ones
//disappear. See onBindViewHolder() for extended application of RecyclerView().
public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private List<OnboardingItem> onboardingItems;

    public OnboardingAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

//  The onCreateViewHolder means on start up the application will go and grab the layout named "OnboardingItem" and inflate it into the adapterview, in this app
//  we use ViewPager2.

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding,parent,false)
        );
    }


//onBindViewHolder() is an extension of RecyclerView() in that instead of creating a new view for each new row when you scroll, an old view is RECYCLED and reused by binding new data
//to the RecyclerView(). What exactly happens in onBindViewHolder() is that initially there will be unused view holders that need to be filled with data we want to display. But as
//the user scrolls there will be view holders that were used for rows that went off screen. The old data in the offscreen view holders will then be replaced with new data.
    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder {

        //Variables
        private TextView textTitle;
        private TextView textDescription;
        private ImageView imageOnboarding;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            imageOnboarding = itemView.findViewById(R.id.imageOnboarding);
        }
        void setOnboardingData(OnboardingItem onboardingItem){
            textTitle.setText(onboardingItem.getTitle());
            textDescription.setText(onboardingItem.getDescription());
            imageOnboarding.setImageResource(onboardingItem.getImage());

        }

        }
    }
