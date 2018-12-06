package com.romanenko.lew.birthdayremaider.View.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.romanenko.lew.birthdayremaider.Model.DTO.HomeCelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.R;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CelebrationAdapterGridView extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<HomeCelebrationVO> listPersons = new LinkedList<>();

    public CelebrationAdapterGridView(Context mContext) {
        this.mContext = mContext;
        this.layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        this.listPersons = listPersons;
    }

    public List<HomeCelebrationVO> getListPersons() {
        return listPersons;
    }


    @Override
    public int getCount() {
        return listPersons.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(HomeCelebrationVO homeCelebrationVO) {
        listPersons.add(homeCelebrationVO);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //ImageButton imageButton;

        ViewHolder viewHolder;

        if (view == null) {
            // if it's not recycled, initialize some attributes
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.fragment_grid_view_item, viewGroup, false);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.gridViewImagePerson);
            viewHolder.name = (TextView) view.findViewById(R.id.gridViewNamePerson);
            viewHolder.timeToCelebration = (TextView) view.findViewById(R.id.gridViewTimeToCelebrationPerson);
            viewHolder.imageViewSmall = (ImageView) view.findViewById(R.id.grid_view_image_person_litle);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.name.setText(listPersons.get(i).getFirstName() + " " + listPersons.get(i).getLastName());
        String days = String.valueOf(countTimeToCelebration(listPersons.get(i).getDay(), listPersons.get(i).getMonth(), listPersons.get(i).getYear()));

        viewHolder.timeToCelebration.setText(DaysYearsSignAdapter.adapterSignLeftDays(mContext,
                (int) countTimeToCelebration(listPersons.get(i).getDay(), listPersons.get(i).getMonth(), listPersons.get(i).getYear())));

        if (listPersons.get(i).getBitmap() != null) {
            // viewHolder.imageView.setImageURI(Uri.parse(new File(listPersons.get(i).fotoPath).toString()));
            viewHolder.imageView.setImageBitmap(listPersons.get(i).getBitmap());
            viewHolder.imageViewSmall.setVisibility(View.GONE);
        }

        return view;
    }

    private long countTimeToCelebration(int day, int month, int year) {
        LocalDate dateNow = new LocalDate();
        LocalDate celebrDate = new LocalDate(dateNow.getYear(), month, day);
        Days days = Days.daysBetween(dateNow, celebrDate);
        return days.getDays();
    }

    static class ViewHolder {
        ImageView imageView;
        ImageView imageViewSmall;
        TextView name;
        TextView timeToCelebration;
    }
}
