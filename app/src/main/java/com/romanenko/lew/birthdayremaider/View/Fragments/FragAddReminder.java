package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.romanenko.lew.birthdayremaider.AddCelebrationContract;
import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPCompAddRemain;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMAddRemainder;
import com.romanenko.lew.birthdayremaider.Model.ModelAddRemainder;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterAddRemainder;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.validation.Validation;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class FragAddReminder extends android.support.v4.app.Fragment implements AddCelebrationContract.ViewAddRemainder {

    @BindView(R.id.frag_add_remainder_name)
    EditText name;
    @BindView(R.id.frag_add_remainder_sur_name)
    EditText surName;
    @BindView(R.id.frag_add_remainder_comment)
    EditText comment;
    @BindView(R.id.frag_add_remainder_date_text)
    TextView dateView;
    @BindView(R.id.frag_add_remainder_date)
    ImageView datePicture;
    @BindView(R.id.frag_add_remainder_spin_type)
    Spinner spinTypeCelebr;
    @BindView(R.id.frag_add_remainder_add_foto_view)
    ImageView contactPicture;
    @BindView(R.id.frag_add_remainder_add_foto)
    ImageButton addFotoButton;
    @BindView(R.id.toolbar_add_celebration)
    Toolbar mToolbar;

    private String pathPictureContact = null;

    private int yearOfAge, monthOfYear, dayOfMonth;
    private Boolean flagUpdateCelebr = false;

    private int userId = -1, dateId = -1, userIdForUpdate = -1;

    private int weightImageContact, heightImageContact;

    private Integer numberOfRows;

    @Inject
    public AddCelebrationContract.PresenterAddRemainder presenter;

    Calendar dateAndTime = Calendar.getInstance();
    String dateCelebrate;

    public static final String TAG_NAME = "name",
            TAG_SUR_NAME = "surname",
            TAG_COMMENT = "comment",
            TAG_TYPE_CELEBR = "typeCelebr",
            TAG_DATE = "date",
            TAG_PICTURE_CONTACT = "picture_contact",
            TAG_USERID = "userId",
            TAG_DATEID = "dateId",
            TAG_CELEBR_IMAGE = "celebrImageTmp";

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_add_reminder, null);


        DaggerMVPCompAddRemain.builder()
                .mVPMAddRemainder(new MVPMAddRemainder(this, new PresenterAddRemainder(getActivity())))
                .build()
                .inject(this);

        presenter.attachView(this);
        presenter.attachModel(new ModelAddRemainder());
        presenter.viewIsReady();


        if (init(view))
            actionForEditCelebr();
        else
            actionForAddCelebr();



        ViewTreeObserver vto = contactPicture.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                contactPicture.getViewTreeObserver().removeOnPreDrawListener(this);
                heightImageContact =  contactPicture.getMeasuredHeight();
                weightImageContact =  contactPicture.getMeasuredWidth();
                return true;
            }
        });


        return view;
    }

    private void actionForAddCelebr() {
        presenter.getNumberOfRows();
    }

    private void actionForEditCelebr() {
        if (userIdForUpdate != -1) {
            presenter.pullPersonalPage(userIdForUpdate);
            flagUpdateCelebr = true;
        }
    }

    private boolean init(View view) {
        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        ActionBar actionbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);

        actionbar.setTitle(R.string.nav_menu_item_add_celebration);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spin_type_celebr, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTypeCelebr.setAdapter(adapter);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userIdForUpdate = bundle.getInt("idUser", -1);
            if (userIdForUpdate != -1)
                return true;
            else
                return false;
        } else
            return false;
    }


    public int getNumberOfRows() {
        return numberOfRows;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.tool_bar_add_reminder, menu);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // mDrawerLayout.openDrawer(GravityCompat.START);
                //getActivity().onBackPressed();
                  getFragmentManager().popBackStack();
               // backClick();
                return true;
            case R.id.action_done:
                if (validateFragment()) {
                    if (flagUpdateCelebr == true) {
                        presenter.editCelebration();
                    } else
                        presenter.addRemainder();
                    backClick();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void backClick() {
        Fragment fragment = null;
        Class fragmentClass = FragNavDraw.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bundle bundle = new Bundle();
        bundle.putString(FragNavDraw.FRAGMENT_NAME, FragListCelebration.class.getName());

        fragment.setArguments(bundle);
        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame_main_activity, fragment)
                .commit();
    }


    @OnClick(R.id.frag_add_set_date)
    public void OnClickDatePicker() {
        new DatePickerDialog(getContext(), R.style.DialogTheme, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            setDateView(year, month + 1, day);
        }
    };

    private void setDateView(int year, int month, int day) {
        yearOfAge = year;
        monthOfYear = month;
        dayOfMonth = day;

        dateCelebrate = day + "/" + month + "/" + year;
       /* dateAndTime.set(Calendar.YEAR, year);
        dateAndTime.set(Calendar.MONTH, monthOfYear);
        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);*/
        dateView.setText(dateCelebrate);
        datePicture.setVisibility(View.GONE);
        dateView.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.frag_add_remainder_add_foto)
    public void OnClickAddFoto() {
        Crop.pickImage(this.getActivity(), this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {

            if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
                beginCrop(data.getData());
            } else if (requestCode == Crop.REQUEST_CROP) {
                handleCrop(resultCode, data);
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getActivity().getCacheDir(), TAG_CELEBR_IMAGE + numberOfRows));
        Crop.of(source, destination).withAspect(weightImageContact, heightImageContact).start(getActivity(), this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            pathPictureContact = getRealPathFromURI(Crop.getOutput(result));
            setPictureContact(pathPictureContact);
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
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

    private void setPictureContact(String pathPictureContact) {
        if (pathPictureContact != null) {
            File f = new File(pathPictureContact);
            Drawable d = Drawable.createFromPath(f.getAbsolutePath());
            contactPicture.setBackground(d);
            addFotoButton.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private boolean validateFragment() {

        boolean validation = false;
        validation = Validation.checkDateNotSet(getContext(), dateView) &

                Validation.checkEmptyField(getContext(), name) &
                Validation.checkOverflowText(getContext(), name) &

                Validation.checkOverflowText(getContext(), surName);

        if (validation == false) return false;

        validation = Validation.checkDateOutOfRange(getContext(), dateView);

        return validation;
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
    }

    @Override
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public String getSurname() {
        return surName.getText().toString();
    }

    @Override
    public int getYear() {
        return yearOfAge;
    }

    @Override
    public int getDay() {
        return dayOfMonth;
    }

    @Override
    public int getMonth() {
        return monthOfYear;
    }

    @Override
    public String getPathImage() {
        return pathPictureContact;
    }

    @Override
    public String getTypeCelebration() {
        return spinTypeCelebr.getSelectedItem().toString();
    }

    @Override
    public String getComment() {
        return comment.getText().toString();
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public int getDateId() {
        return dateId;
    }

    @Override
    public void setName(String name) {
        this.name.setText(name);
    }

    @Override
    public void setSurname(String surName) {
        this.surName.setText(surName);
    }

    @Override
    public void setDate(int year, int month, int day) {
        this.yearOfAge = year;
        this.dayOfMonth = day;
        this.monthOfYear = month;
        setDateView(year, month, day);
    }


    @Override
    public void setPathImage(String path) {
        this.pathPictureContact = path;
        setPictureContact(pathPictureContact);
    }

    @Override
    public void setTypeCelebration(String typeCelebration) {
        setSpinner(typeCelebration);

    }

    @Override
    public void setComment(String comment) {
        this.comment.setText(comment);
    }

    @Override
    public void setIdUser(int userId) {
        this.userId = userId;
    }

    @Override
    public void setIdDate(int dateId) {
        this.dateId = dateId;
    }

    @Override
    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    private void setSpinner(String typeCelebration) {
        for (int i = 0; i < spinTypeCelebr.getAdapter().getCount(); i++) {
            if (spinTypeCelebr.getAdapter().getItem(i).toString().contains(typeCelebration)) {
                spinTypeCelebr.setSelection(i);
            }
        }
    }

    @Override
    public void showView(String error) {

    }

}




