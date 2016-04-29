package cn.studyjams.s1.sj38.dupengcan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private TabPageIndicator pageIndicator;
    private Toolbar titlebar;
    private SharedPreferences exitPreference;
    private static final String EXITTAG = "isCheckeExit";
    private static final String SHAREPREFERENCEUSER = "dupengcan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        exitPreference = getSharedPreferences(SHAREPREFERENCEUSER, Context.MODE_PRIVATE);
    }

    /**
     * Initialize each view
     */
    private void initView() {
        titlebar = (Toolbar) findViewById(R.id.toolbar);
        titlebar.setTitle(getResources().getString(R.string.app_title));
        titlebar.setTitleTextColor(getColor(R.color.white));
        titlebar.inflateMenu(R.menu.menu_main);
        titlebar.setOnMenuItemClickListener(onMenuItemClick);

        viewPager = (ViewPager) findViewById(R.id.view_page);
        viewPager.setAdapter(new ContentPage(getSupportFragmentManager()));

        pageIndicator = (TabPageIndicator) findViewById(R.id.page_indicator);
        pageIndicator.setBackgroundColor(getColor(R.color.colorPrimaryDark));
        pageIndicator.setViewPager(viewPager);
    }


    /**
     * Toolbar.OnMenuItemClickListener
     */
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.about:
                    startActivity(new Intent(MainActivity.this, IntroduceActivity.class));
                    break;
            }
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        Log.d("dpc", "exitPreference.getBoolean=" + exitPreference.getBoolean(EXITTAG, false));
        if (exitPreference.getBoolean(EXITTAG, false)) {
            super.onBackPressed();
        } else {
            AlertDialog.Builder exitDialog = new AlertDialog.Builder(this);
            exitDialog.setTitle(getString(R.string.exit_title));
            exitDialog.setMessage(getString(R.string.exit_message));
            final CheckBox checkBox = new CheckBox(this);
            checkBox.setText(getString(R.string.never_show));
            exitDialog.setView(checkBox);
            exitDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    exitPreference = getSharedPreferences(SHAREPREFERENCEUSER, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = exitPreference.edit();
                    if (checkBox.isChecked()) {
                        editor.putBoolean(EXITTAG, true);
                        editor.commit();
                    }
                    finish();
                }
            });
            exitDialog.setNegativeButton(getString(R.string.cancle), null);
            exitDialog.show();
        }
    }


    class ContentPage extends FragmentPagerAdapter {
        private String[] TAB_TITLE = {getString(R.string.culture), getString(R.string.sights),
                getString(R.string.food), getString(R.string.persons), getString(R.string.education)};

        public ContentPage(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ContentFrament fragment = new ContentFrament();
            Bundle bundle = new Bundle();
            bundle.putInt("IndexFrament", position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TAB_TITLE[position % TAB_TITLE.length];
        }

        @Override
        public int getCount() {
            return TAB_TITLE.length;
        }
    }
}
