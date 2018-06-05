package com.romanenko.lew.birthdayremaider.View.Adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.romanenko.lew.birthdayremaider.Model.POJO.PListBirthdayItem;
import com.romanenko.lew.birthdayremaider.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.Inflater;

public class BirthdayAdapterList extends RecyclerView.Adapter<BirthdayAdapterList.BirthdayViewHolder> {
    Context context;
    List<PListBirthdayItem> listBirthdayItems;
    LayoutInflater lInflater;


    public BirthdayAdapterList(Context context, List<PListBirthdayItem> listBirthdayItems) {
        this.context = context;
        this.listBirthdayItems = listBirthdayItems;
     /*   lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);*/
    }

    @Override
    public BirthdayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_birthday_item, parent, false);

        return new BirthdayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BirthdayViewHolder holder, int position) {
        holder.bind(listBirthdayItems.get(position));
    }

    @Override
    public int getItemCount() {
        return listBirthdayItems.size();
    }

    public void setItems(Collection<PListBirthdayItem> Birthdays) {
        listBirthdayItems.addAll(Birthdays);
        notifyDataSetChanged();
    }

    public void clearItems() {
        listBirthdayItems.clear();
        notifyDataSetChanged();
    }

    class BirthdayViewHolder extends RecyclerView.ViewHolder {

        private ImageView birthdayListImageView;
        private TextView birthdayListTextView;

        public void bind(PListBirthdayItem pListBirthdayItem) {
            birthdayListTextView.setText(pListBirthdayItem.getMainText());
        }

        public BirthdayViewHolder(View itemView) {
            super(itemView);

            birthdayListImageView = itemView.findViewById(R.id.birthday_list_image_view);
            birthdayListTextView = itemView.findViewById(R.id.birthday_list_text_view);
        }
    }
}
