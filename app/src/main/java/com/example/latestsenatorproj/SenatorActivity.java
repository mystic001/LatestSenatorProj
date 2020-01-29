package com.example.latestsenatorproj;


import androidx.fragment.app.Fragment;

public class SenatorActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SenatorFragment.newInstance();
    }
}
