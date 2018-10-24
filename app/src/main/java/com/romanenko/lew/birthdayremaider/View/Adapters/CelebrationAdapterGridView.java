package com.romanenko.lew.birthdayremaider.View.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.CelebrListNameDateFotoDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.DataCelebrationForListDTO;
import com.romanenko.lew.birthdayremaider.Model.DataLocalRepository.QueryObjects.PersonalPageAllInformation;
import com.romanenko.lew.birthdayremaider.R;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CelebrationAdapterGridView extends BaseAdapter {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<CelebrListNameDateFotoDTO> listPersons;

    public CelebrationAdapterGridView(Context mContext,List<CelebrListNameDateFotoDTO> listPersons) {
        this.mContext = mContext;
        this.layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        this.listPersons = listPersons;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //ImageButton imageButton;

        ViewHolder viewHolder;

        if (view == null) {
            // if it's not recycled, initialize some attributes
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.fragment_grid_view_item, viewGroup, false);
            viewHolder. imageView = (ImageView) view.findViewById(R.id.gridViewImagePerson);
            viewHolder.name = (TextView) view.findViewById(R.id.gridViewNamePerson);
            viewHolder.timeToCelebration = (TextView) view.findViewById(R.id.gridViewTimeToCelebrationPerson);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.name.setText(listPersons.get(i).firstName + " " + listPersons.get(i).lastName);
        viewHolder.timeToCelebration.setText(String.valueOf(countTimeToCelebration(listPersons.get(i).day,listPersons.get(i).month,listPersons.get(i).year)));
        if(listPersons.get(i).fotoPath != null)
        viewHolder.imageView.setImageURI(Uri.parse(new File(listPersons.get(i).fotoPath).toString()));

        // imageView.setImageResource(mThumbIds[position]);


        return view;
    }

    private long countTimeToCelebration(int day,int month, int year ){

        Date date = new Date();
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = day +" " + month + " "+ year;
        String inputString2 = myFormat.format(date);

        long diff = 0;
        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            diff = date2.getTime() - date1.getTime();
          //  System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 25;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView name;
        TextView timeToCelebration;
    }
}
