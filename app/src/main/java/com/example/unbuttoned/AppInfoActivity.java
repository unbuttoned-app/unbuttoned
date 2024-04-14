package com.example.unbuttoned;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AppInfoActivity extends AppCompatActivity {

    public static final String RESET_HSCORE = "RESET_HSCORE";
    public static final String HSCORE_VALUE = "HSCORE_VALUE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        final Button licenseButton = findViewById(R.id.licenseButton);
        final Button resetHighScore = findViewById(R.id.resetHighScore);
        final TextView score = findViewById(R.id.highScore);
        licenseButton.setOnClickListener(v -> startActivity(new Intent(this, LicenseActivity.class)));
        resetHighScore.setOnClickListener(v -> {
            final Intent data = new Intent();
            data.putExtra(RESET_HSCORE, true);
            setResult(Activity.RESULT_OK, data);
            finish();
        });


        final Bundle args = getIntent().getExtras();
        if (args != null) {
            final int hscore = args.getInt(HSCORE_VALUE);
            score.setText(String.valueOf(hscore));

        }
    }
}
