package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Activities.MainActivity;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class FragAddReminder extends android.support.v4.app.DialogFragment {

    @BindView(R.id.frag_add_remainder_name)
    EditText Name;
    @BindView(R.id.frag_add_remainder_sur_name)
    EditText SurName;
    @BindView(R.id.frag_add_remainder_comment)
    EditText Comment;
    @BindView(R.id.frag_add_remainder_date_text)
    TextView dateView;
    @BindView(R.id.frag_add_remainder_date)
    ImageView datePicture;
    @BindView(R.id.frag_add_remainder_spin_type)
    Spinner spinTypeCelebr;
    @BindView(R.id.frag_add_remainder_add_foto_view)
    ImageView contactPicture;

    public static final int PICK_IMAGE = 1;
    private  String pathPictureContact = null;

    private URI uri;

    Calendar dateAndTime = Calendar.getInstance();
    String dateCelebrate;

    public static final String TAG_NAME = "name",
            TAG_SUR_NAME = "surname",
            TAG_COMMENT = "comment",
            TAG_TYPE_CELEBR = "typeCelebr",
            TAG_DATE = "date",
            TAG_PICTURE_CONTACT = "picture_contact";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_remainder, null);

        init(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent();
                        // intent.putExtra(TAG_WEIGHT_SELECTED, mNpWeight.getValue());
                        intent = loadDataIntent(intent);

                        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);


                    }
                });
        // getDialog().getWindow().setLayout(100, 100);
        return builder.create();
    }

    public void init(View view) {
        ButterKnife.bind(this, view);
        //dateView.setVisibility(View.GONE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spin_type_celebr, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTypeCelebr.setAdapter(adapter);

    }

    private Intent loadDataIntent(Intent intent) {
        intent.putExtra(TAG_NAME, Name.getText().toString());
        intent.putExtra(TAG_SUR_NAME, SurName.getText().toString());
        intent.putExtra(TAG_COMMENT, Comment.getText().toString());
        intent.putExtra(TAG_TYPE_CELEBR, spinTypeCelebr.getSelectedItem().toString());
        intent.putExtra(TAG_DATE, dateCelebrate);
        intent.putExtra(TAG_PICTURE_CONTACT, pathPictureContact );
        return intent;
    }

    @OnClick(R.id.frag_add_set_date)
    public void OnClickDatePicker() {
        new DatePickerDialog(getContext(), d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            dateCelebrate = dayOfMonth + "/" + ++monthOfYear + "/" + year;
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateView.setText(dateCelebrate);
            datePicture.setVisibility(View.GONE);
            dateView.setVisibility(View.VISIBLE);

        }
    };

    @OnClick(R.id.frag_add_remainder_add_foto)
    public void OnClickAddFoto(){
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == PICK_IMAGE) {
                    // Get the url from data
                    //uri = data.getParcelableExtra("file");
                    Uri contentURI = data.getData();

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                        //String path = saveImage(bitmap);
                       // Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    //contactPicture.setImageBitmap(bitmap);
                 //   contactPicture.setImageURI( contentURI);
                  //  contactPicture.setBackground(contentURI);
                     pathPictureContact = getRealPathFromURI(contentURI);
                    File f = new File(pathPictureContact);
                    Drawable d = Drawable.createFromPath(f.getAbsolutePath());
                    contactPicture.setBackground(d);
                } else {
                    final Uri uri_data = data.getData();
                    // Get the path from the Uri
                    final String path = getRealPathFromURI(uri_data);
                    if (path != null) {
                        File f = new File(path);
                        //uri = Uri.fromFile(f);
                    }
                    // Set the image in
                   // ImageView((ImageView) findViewById(R.id.imgView)).setImageURI(selectedImageUri);


                }
                //datePicture.setImageURI(  android.net.Uri.parse(uri.toString()));
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }


    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
