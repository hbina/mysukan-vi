package masco.com.mysukan_vi.activities;

import android.os.Bundle;

import masco.com.mysukan_vi.R;

/**
 * Created by Akarin on 9/29/2017.
 */

public class SponsorsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        getSupportActionBar().setTitle("MySukan VI Sponsors");
    }
}