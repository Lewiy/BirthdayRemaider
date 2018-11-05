package com.romanenko.lew.birthdayremaider.View.Fragments;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.romanenko.lew.birthdayremaider.DISystem.Components.DaggerMVPCompListCelebr;
import com.romanenko.lew.birthdayremaider.DISystem.Modules.MVPMListCelebration;
import com.romanenko.lew.birthdayremaider.ListCelebrationContract;
import com.romanenko.lew.birthdayremaider.Model.DTO.CelebrationVO;
import com.romanenko.lew.birthdayremaider.Model.ModelListCelebration;
import com.romanenko.lew.birthdayremaider.Presenter.PresenterListCelebration;
import com.romanenko.lew.birthdayremaider.R;
import com.romanenko.lew.birthdayremaider.View.Adapters.CelebrationAdapterList;
import com.romanenko.lew.birthdayremaider.View.Adapters.PaginationScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lev- on 21.03.2018.
 */

public class FragListCelebration extends android.support.v4.app.Fragment implements ListCelebrationContract.ViewListBirthday {

    @BindView(R.id.lv_main)
    RecyclerView recyclerViewMain;
   /* @BindView((R.id.lv_prog_bar))
    ProgressBar progressBar;*/

    private SearchView searchView;
    private CelebrationAdapterList celebrationAdapterList;


    public static final int REQUEST_ADD_REMAINDER = 1;
    // private static final int REQUEST_ANOTHER_ONE = 2;

    String name, surName, comment, type_celebr, date;


    // Index from which pagination should start (0 is 1st page in our case)
    private static final int PAGE_START = 0;
    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;
    // total no. of pages to load. Initial load is page 0, after which 2 more pages will load.
    private int TOTAL_PAGES = 3;
    // indicates the current page which Pagination is fetching.
    private int currentPage = PAGE_START;

    private LinearLayoutManager linearLayoutManager;

    @Inject
    ListCelebrationContract.PresenterListBirthday presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_birthday, null);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        // presenter = new PresenterListCelebration(this);
        DaggerMVPCompListCelebr.builder()
                .mVPMListCelebration(new MVPMListCelebration(this, new PresenterListCelebration(getContext())))
                .build()
                .inject(this);

        presenter.attachView(this);
        presenter.attachModel(new ModelListCelebration());

        presenter.viewIsReady();

        loadData();
        initList();
        return view;
    }

    private void initList() {
        CelebrationAdapterList.RecyclerViewClickListener listener = new CelebrationAdapterList.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position, CelebrationVO celebrationVO) {
                Fragment fragment = null;
                try {
                    fragment = FragEditCelebration.class.newInstance();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                Bundle bundle = new Bundle();
                bundle.putInt("idUser", (int) celebrationVO.getIdUser());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
        };

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);


        celebrationAdapterList = new CelebrationAdapterList(this.getContext(), listener);
        recyclerViewMain.setLayoutManager(linearLayoutManager);
        recyclerViewMain.setAdapter(celebrationAdapterList);
        recyclerViewMain.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMain.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        /*recyclerViewMain.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {

            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadData();
                isLoading = false;
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });*/
    }

    @OnClick(R.id.add_remind)
    public void onClickAddRemindBut() {
        openFragAddRemainder();
    }

    //TODO ArrayList Mock
    public void loadData() {
        presenter.pullListCelebration();
    }

    public void openFragAddRemainder() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_frame_main_activity, new FragAddReminder(), "FragAddReminder")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void loadListCelebration(List<CelebrationVO> items) {
        celebrationAdapterList.setItems(items);
    }


    @Override
    public void loadListCelebrationSearch(List<CelebrationVO> items) {
        celebrationAdapterList.clearItems();
        celebrationAdapterList.setItems(items);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.tool_bar_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.pullListCelebrationSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                presenter.pullListCelebrationSearch(query);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Log.i("item id ", item.getItemId() + "");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
