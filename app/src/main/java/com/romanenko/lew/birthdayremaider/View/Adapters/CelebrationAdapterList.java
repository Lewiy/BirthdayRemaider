package com.romanenko.lew.birthdayremaider.View.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class CelebrationAdapterList extends RecyclerView.Adapter<CelebrationAdapterList.BirthdayViewHolder> {
    Context context;
    List<CelebrationVO> listBirthdayItems = new ArrayList<>();
    LayoutInflater lInflater;
    private RecyclerViewClickListener mListener;


    public CelebrationAdapterList(Context context, RecyclerViewClickListener listener) {
        this.context = context;
        this.mListener = listener;
    }

    public List<CelebrationVO> getListBirthdayItems() {
        return listBirthdayItems;
    }

    @Override
    public BirthdayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_birthday_item, parent, false);

        return new BirthdayViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(BirthdayViewHolder holder, int position) {
        holder.bind(listBirthdayItems.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return listBirthdayItems.size();
    }

    public void setItems(Collection<CelebrationVO> Birthdays) {
        listBirthdayItems.addAll(Birthdays);
        notifyDataSetChanged();

    }

    public void setItem(CelebrationVO celebrationVOS) {
        listBirthdayItems.add(celebrationVOS);
        notifyDataSetChanged();

    }

    public void clearItems() {
        listBirthdayItems.clear();
        notifyDataSetChanged();
    }

    public CelebrationVO getItem(int position) {
        return listBirthdayItems.get(position);
    }

    class BirthdayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView birthdayListImageView;
        private TextView name_surname;
        private TextView type_celebration;
        private TextView years_old;
        private RecyclerViewClickListener recyclerViewClickListener;


        public void bind(CelebrationVO celebrationVO, RecyclerViewClickListener recyclerViewClickListener) {

            this.recyclerViewClickListener = recyclerViewClickListener;
            name_surname.setText(celebrationVO.getFirstName() + "  " + celebrationVO.getLastName());
            type_celebration.setText(celebrationVO.getTypeCelebration());
            years_old.setText(DaysYearsSignAdapter.adapterSignLeftYears(context, countYearCelebr(celebrationVO.getYear())));

            if (celebrationVO.getImage() != null) {
                birthdayListImageView.setImageBitmap(celebrationVO.getImage());

            } else {
                birthdayListImageView.setImageResource(R.drawable.ic_person_black_24dp);
            }

        }

        public BirthdayViewHolder(View itemView, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);
            this.recyclerViewClickListener = recyclerViewClickListener;
            birthdayListImageView = itemView.findViewById(R.id.birthday_list_image_view);
            name_surname = itemView.findViewById(R.id.name_surname);
            type_celebration = itemView.findViewById(R.id.type_celebration);
            years_old = itemView.findViewById(R.id.years_old);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            recyclerViewClickListener.onClick(view, getAdapterPosition(), getItem(getAdapterPosition()));
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position, CelebrationVO celebrationVO);
    }


    private int countYearCelebr(int year) {

        return (Calendar.getInstance().get(Calendar.YEAR) - year);

    }

}
