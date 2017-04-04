package com.example.korisnik.rouraltourism.activity.home_activity;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.korisnik.rouraltourism.R;
import com.example.korisnik.rouraltourism.model.data_model.BasicLocationData;
import com.example.korisnik.rouraltourism.model.data_model.Location;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Korisnik on 3.4.2017..
 */

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.Holder> {

    public static String IMAGE_URL = "http://slavonijaturizam.eu/cms/photo/";

    private List<Location> locationList = new ArrayList<>();
    private RecyclerListener listener;
    private Uri imageUri;
    float ratings = 0f;
    float ratings1 = 0f;
    float ratings2 = 0f;


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_subcategory, parent ,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        BasicLocationData basicData = locationList.get(position).getMeta().getBasic();
        imageUri = Uri.parse(IMAGE_URL + locationList.get(position).getId());
        holder.locationImage.setImageURI(imageUri);
            ratings = locationList.get(position - 1).getRatings();
            ratings1 = locationList.get(position).getRatings();
            ratings2 = locationList.get(position + 1).getRatings();


        if (basicData.getWebLocation().equals("") || basicData.getWebLocation().isEmpty()) {
            holder.setLlWeb(holder.llWeb);
        }
        if (basicData.getPhoneLocation().isEmpty() || basicData.getPhoneLocation().equals("")) {
            holder.setLlSorucePhone(holder.llSorucePhone);
        }
        if (basicData.getMailLocation().equals("") || basicData.getMailLocation().isEmpty()) {
            holder.setLlSoruceMail(holder.llSoruceMail);
        }
        holder.tvTitle.setText(locationList.get(position).getTranslations().getTranslationOne().getTitle());
        holder.tvCity.setText(basicData.getCity());
        holder.tvAddress.setText(basicData.getAddress());
        holder.tvSoruceTelephone.setText(basicData.getPhoneLocation());
        holder.tvSourceMail.setText(basicData.getMailLocation());
        holder.tvSourceWeb.setText(basicData.getWebLocation());
        if(ratings < 2f && ratings >= 1f || ratings1 < 2f && ratings1 >= 1f || ratings2 < 2f && ratings2 >= 1f){
            setOneStar(holder);
        }
        else if(ratings < 3f && ratings >= 2f || ratings1 < 3f && ratings1 >= 2f || ratings2 < 3f && ratings2 >= 2f){
            setTwoStars(holder);
        }
        else if(ratings < 4f && ratings >= 3f || ratings1 < 4f && ratings1 >= 3f || ratings2 < 4f && ratings2 >= 3f){
            setThreeStars(holder);
        }
        else if(ratings < 5f && ratings >= 4f || ratings1 < 5f && ratings1 >= 4f || ratings2 < 5f && ratings2 >= 4f){
            setFourStars(holder);
        }
        else if(ratings < 6f && ratings >= 5f || ratings1 < 6f && ratings1 >= 5f || ratings2 < 6f && ratings2 >= 5f){
            setFiveStars(holder);
        }
        else
            setZeroStars(holder);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public void setLocationData(List<Location> locations){
        if(locations != null && !locations.isEmpty()){
            this.locationList.clear();
            this.locationList.addAll(locations);
            notifyDataSetChanged();
        }
    }

    private void setOneStar(Holder holder){
        holder.ivStarOne.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarTwo.setImageResource(R.mipmap.subcategory_star_white_big);
        holder.ivStarThree.setImageResource(R.mipmap.subcategory_star_white_big);
        holder.ivStarFour.setImageResource(R.mipmap.subcategory_star_white_big);
        holder.ivStarFive.setImageResource(R.mipmap.subcategory_star_white_big);
    }

    private void setTwoStars(Holder holder){
        holder.ivStarOne.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarTwo.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarThree.setImageResource(R.mipmap.subcategory_star_white_big);
        holder.ivStarFour.setImageResource(R.mipmap.subcategory_star_white_big);
        holder.ivStarFive.setImageResource(R.mipmap.subcategory_star_white_big);
    }

    private void setThreeStars(Holder holder){
        holder.ivStarOne.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarTwo.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarThree.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarFour.setImageResource(R.mipmap.subcategory_star_white_big);
        holder.ivStarFive.setImageResource(R.mipmap.subcategory_star_white_big);
    }

    private void setFourStars(Holder holder){
        holder.ivStarOne.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarTwo.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarThree.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarFour.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarFive.setImageResource(R.mipmap.subcategory_star_white_big);
    }

    private void setFiveStars(Holder holder){
        holder.ivStarOne.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarTwo.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarThree.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarFour.setImageResource(R.mipmap.subcategory_star_brawn);
        holder.ivStarFive.setImageResource(R.mipmap.subcategory_star_brawn);
    }

    private void setZeroStars(Holder holder){
        holder.ivStarOne.setImageResource(R.mipmap.subcategory_star_white_big);
        holder.ivStarTwo.setImageResource(R.mipmap.subcategory_star_white_big);
        holder.ivStarThree.setImageResource(R.mipmap.subcategory_star_white_big);
        holder.ivStarFour.setImageResource(R.mipmap.subcategory_star_white_big);
        holder.ivStarFive.setImageResource(R.mipmap.subcategory_star_white_big);
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_city)
        TextView tvCity;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_source_mail)
        TextView tvSourceMail;
        @BindView(R.id.tv_source_web)
        TextView tvSourceWeb;
        @BindView(R.id.tv_source_telephone)
        TextView tvSoruceTelephone;

        /*@BindView(R.id.iv_location)
        ImageView ivlocation;*/
        @BindView(R.id.iv_star_one)
        ImageView ivStarOne;
        @BindView(R.id.iv_star_two)
        ImageView ivStarTwo;
        @BindView(R.id.iv_star_three)
        ImageView ivStarThree;
        @BindView(R.id.iv_star_four)
        ImageView ivStarFour;
        @BindView(R.id.iv_star_five)
        ImageView ivStarFive;

        @BindView(R.id.ll_source_mail)
        LinearLayout llSoruceMail;
        @BindView(R.id.ll_source_phone)
        LinearLayout llSorucePhone;
        @BindView(R.id.ll_web)
        LinearLayout llWeb;

        @BindView(R.id.sdvImage)
        SimpleDraweeView locationImage;

        private void setLlSorucePhone(LinearLayout llSorucePhone) {
            llSorucePhone.setVisibility(View.GONE);
            this.llSorucePhone = llSorucePhone;
        }

        private void setLlSoruceMail(LinearLayout llSoruceMail) {
            llSoruceMail.setVisibility(View.GONE);
            this.llSoruceMail = llSoruceMail;
        }

        private void setLlWeb(LinearLayout llWeb) {
            llWeb.setVisibility(View.GONE);
            this.llWeb = llWeb;
        }

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            if(listener != null)
                listener.onRecyclerClick(locationList.get(getAdapterPosition()));
        }
    }

    public void setListener(RecyclerListener listener) {
        this.listener = listener;
    }
}
