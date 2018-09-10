package com.romanenko.lew.birthdayremaider.View.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.R;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class BirthdayAdapterList extends RecyclerView.Adapter<BirthdayAdapterList.BirthdayViewHolder> {
    Context context;
    List<CelebrationVO>listBirthdayItems;
    LayoutInflater lInflater;


    public BirthdayAdapterList(Context context, List<CelebrationVO> listBirthdayItems) {
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

    public void setItems(Collection<CelebrationVO> Birthdays) {
        listBirthdayItems.addAll(Birthdays);
        notifyDataSetChanged();
    }

    public void clearItems() {
        listBirthdayItems.clear();
        notifyDataSetChanged();
    }

    class BirthdayViewHolder extends RecyclerView.ViewHolder {

        private ImageView birthdayListImageView;
        private TextView name_surname;
        private TextView date;
        private TextView type_celebration;
        private TextView years_old;

        public void bind(CelebrationVO celebrationVO) {
            //birthdayListImageView.setText(listCelebrationItem.getMainText());


            name_surname.setText(celebrationVO.getFirstName()+"  "+ celebrationVO.getLastName());
            date.setText(celebrationVO.getDate());

            if(celebrationVO.getFotoPath() != null){
                File f = new File(celebrationVO.getFotoPath());
                Drawable d = Drawable.createFromPath(f.getAbsolutePath());
                birthdayListImageView.setBackground(d);
            }

          //  type_celebration.setText(celebrationVO.);
           // years_old.setText(celebrationVO.getMainText());
        }

        public BirthdayViewHolder(View itemView) {
            super(itemView);

            birthdayListImageView = itemView.findViewById(R.id.birthday_list_image_view);
            name_surname = itemView.findViewById(R.id.name_surname);
            date = itemView.findViewById(R.id.date);
            type_celebration = itemView.findViewById(R.id.type_celebration);
            years_old = itemView.findViewById(R.id.years_old);
        }
    }
}
