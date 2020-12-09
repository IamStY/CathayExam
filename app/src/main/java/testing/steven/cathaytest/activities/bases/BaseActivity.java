package testing.steven.cathaytest.activities.bases;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Locale;

import testing.steven.cathaytest.R;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition( R.anim.right_in, R.anim.left_out);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.left_in, R.anim.right_out);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition( R.anim.left_in, R.anim.right_out);
    }


}
