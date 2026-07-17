package com.example.catologodelivros.ui.support;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.catologodelivros.R;
import com.example.catologodelivros.ui.BaseActivity;
import com.example.catologodelivros.util.AppConstants;

public class SupportActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        setupToolbar(R.id.supportToolbar, true, R.string.support_title);

        findViewById(R.id.emailButton).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + AppConstants.SUPPORT_EMAIL));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.support_email_subject));
            openIntent(intent);
        });

        findViewById(R.id.whatsappButton).setOnClickListener(v -> {
            String url = "https://wa.me/" + AppConstants.SUPPORT_WHATSAPP
                    + "?text=" + Uri.encode(getString(R.string.whatsapp_message));
            openIntent(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });
    }

    private void openIntent(Intent intent) {
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, R.string.no_compatible_app, Toast.LENGTH_SHORT).show();
        }
    }
}
