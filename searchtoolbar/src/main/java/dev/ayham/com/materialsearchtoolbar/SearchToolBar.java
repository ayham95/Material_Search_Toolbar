package dev.ayham.com.materialsearchtoolbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import static dev.ayham.com.materialsearchtoolbar.UIUtils.circleReveal;


public class SearchToolBar {

    private Toolbar searchToolbar;
    private Activity activity;
    private StateListener stateListener;
    private int posFromRight = 0;

    public void setStateListener(StateListener stateListener) {
        this.stateListener = stateListener;
    }

    private void setBackgroundColor(int resColor){
        getSearchToolbar().setBackgroundColor(resColor);
    }

    public SearchToolBar(final Activity activity) {
        this.activity = activity;
        searchToolbar = (Toolbar) activity.findViewById(R.id.searchtoolbar);
        searchToolbar.inflateMenu(R.menu.menu_search);
        initSearchView();


        getSearchView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i("fuck", "onFocusChange: " + hasFocus);
                if (!hasFocus && stateListener != null) {
                    stateListener.onFocusLost();
                }
            }
        });

    }


    public Toolbar getSearchToolbar() {
        return searchToolbar;
    }

    public void collapse() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circleReveal(activity, R.id.searchtoolbar, getPosFromRight(), false);
        } else {
            searchToolbar.setVisibility(View.GONE);
        }

    }

    public void expand() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circleReveal(activity, R.id.searchtoolbar, getPosFromRight(), true);
        } else {
            searchToolbar.setVisibility(View.VISIBLE);
        }
        getSearchToolbar().getMenu().findItem(R.id.action_filter_search).expandActionView();
    }


    public boolean isPresent() {
        return searchToolbar.getVisibility() == View.VISIBLE;
    }

    public void setSearchListener(final SearchListener searchListener) {

        getSearchView().setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                timer.cancel();
                getSearchView().clearFocus();
                searchListener.submit(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                searchListener.instantSearch(newText);
                            }
                        });

                    }
                }, 300);

                return true;
            }

        });
    }

    private void initSearchView() {
        final SearchView searchView = getSearchView();

        searchView.setSubmitButtonEnabled(false);

        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_close_gray_24dp);

        final EditText txtSearch = ((EditText) searchView.findViewById(R.id.search_src_text));
        txtSearch.setHint("Search..");
        txtSearch.setHintTextColor(Color.DKGRAY);


        MenuItemCompat.setOnActionExpandListener(searchToolbar.getMenu().findItem(R.id.action_filter_search),
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        if (stateListener != null) {
                            stateListener.onExpand();
                        }
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        collapse();
                        if (stateListener != null) {
                            stateListener.onCollapse();
                        }

                        return true;
                    }
                });

    }

    private SearchView getSearchView() {
        return (SearchView) getSearchToolbar().getMenu().findItem(R.id.action_filter_search).getActionView();
    }


    public String getQuery() {

        return ((EditText) searchToolbar.getMenu().findItem(R.id.search_src_text)).getText().toString();

    }

    public int getPosFromRight() {
        return posFromRight;
    }

    public void setPosFromRight(int posFromRight) {
        this.posFromRight = posFromRight;
    }

    public interface SearchListener {
        void submit(String query);

        void instantSearch(String query);
    }

    public interface StateListener {
        void onExpand();

        void onCollapse();

        void onFocusLost();
    }


}
