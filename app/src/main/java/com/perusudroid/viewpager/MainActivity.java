package com.perusudroid.viewpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager viewPager;
    private Button btnFirst, btnSecond;
    private EditText etTxt;
    private TextView tvTxt;
    private ArrayList<String> fragInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setAssets();
    }


    private void bindViews() {
        fragInstance = new ArrayList<>();
        btnFirst = findViewById(R.id.btnFirst);
        btnSecond = findViewById(R.id.btnSecond);
        viewPager = findViewById(R.id.viewpager);
        etTxt = findViewById(R.id.etText);
        tvTxt = findViewById(R.id.tvTxt);
    }

    private void setAssets() {
        fragInstance.clear();
        setupViewPager(viewPager);
        tvTxt.setText(R.string.main_activity);
        btnFirst.setText(R.string.send_to_first_fragment);
        btnSecond.setText(R.string.send_to_second_fragment);
        btnFirst.setOnClickListener(this);
        btnSecond.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment(), "First");
        adapter.addFragment(new SecondFragment(), "Second");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFirst:
                if (etTxt.getText().toString().trim().length() > 0) {
                    if (fragInstance != null) {

                        for (String mInstance : fragInstance) {
                            Fragment fragment = getSupportFragmentManager().findFragmentByTag(mInstance);

                            if (fragment instanceof FirstFragment) {
                                FirstFragment mFirstFragment = (FirstFragment) getSupportFragmentManager().findFragmentByTag(String.valueOf(mInstance));
                                mFirstFragment.setText(etTxt.getText().toString().trim());
                                etTxt.setText("");
                                if (viewPager.getCurrentItem() != 0) {
                                    viewPager.setCurrentItem(0);
                                }

                            }
                        }

                    }

                }
                break;
            case R.id.btnSecond:

                if (etTxt.getText().toString().trim().length() > 0) {
                    if (fragInstance != null) {

                        for (String mInstance : fragInstance) {
                            Fragment fragment = getSupportFragmentManager().findFragmentByTag(mInstance);

                            if (fragment instanceof SecondFragment) {
                                SecondFragment mSecondFragment = (SecondFragment) getSupportFragmentManager().findFragmentByTag(String.valueOf(mInstance));
                                mSecondFragment.setText(etTxt.getText().toString().trim());
                                etTxt.setText("");
                                if (viewPager.getCurrentItem() != 1) {
                                    viewPager.setCurrentItem(1);
                                }
                            }
                        }

                    }
                    break;
                }


        }
    }

    public void setFragInstance(String instance) {
        fragInstance.add(instance);
    }


    public void firstFragData(String txtToForward, boolean doForward, int position) {

        if (position != -1) {
            viewPager.setCurrentItem(position);
        }

        if (doForward) {
            for (String mInstance : fragInstance) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(mInstance);

                if (fragment instanceof FirstFragment) {
                    FirstFragment mFirstFragment = (FirstFragment) getSupportFragmentManager().findFragmentByTag(String.valueOf(mInstance));
                    mFirstFragment.setText(txtToForward);
                }
            }
        } else {
            tvTxt.setText(txtToForward);
        }

    }

    public void secondFragData(String txtToForward, boolean doForward, int position) {

        if (position != -1) {
            viewPager.setCurrentItem(position);
        }

        if (doForward) {
            for (String mInstance : fragInstance) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(mInstance);

                if (fragment instanceof SecondFragment) {
                    SecondFragment mSecondFragment = (SecondFragment) getSupportFragmentManager().findFragmentByTag(String.valueOf(mInstance));
                    mSecondFragment.setText(txtToForward);
                }
            }
        } else {
            tvTxt.setText(txtToForward);
        }

    }
}
