package android.neworin.com.passwordviewdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.subhrajyoti.passwordview.PasswordView;

public class MainActivity extends AppCompatActivity {

    private PasswordView passwordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordView = (PasswordView) findViewById(R.id.passwordView);
        passwordView.setEyeTint(Color.RED);
        passwordView.useStrikeThrough(true);
    }
}
