package tremblay412.com.mysukan.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tremblay412.com.mysukan.R;

/**
 * Created by Akarin on 9/30/2017.
 */

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().setTitle("Info");
    }
}
