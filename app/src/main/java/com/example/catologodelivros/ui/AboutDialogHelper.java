package com.example.catologodelivros.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.catologodelivros.R;
import com.example.catologodelivros.util.AppConstants;
import com.example.catologodelivros.util.FormatUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Date;

public final class AboutDialogHelper {

    private AboutDialogHelper() {
    }

    public static void show(AppCompatActivity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_about, null, false);
        TextView dateText = view.findViewById(R.id.aboutDateTime);
        dateText.setText(activity.getString(R.string.about_generated_at,
                FormatUtils.formatDateTime(new Date())));

        view.findViewById(R.id.aboutEmailButton).setOnClickListener(v ->
                openIntent(activity, new Intent(Intent.ACTION_SENDTO,
                        Uri.parse("mailto:" + AppConstants.SUPPORT_EMAIL))));

        view.findViewById(R.id.aboutWhatsappButton).setOnClickListener(v ->
                openIntent(activity, new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://wa.me/" + AppConstants.SUPPORT_WHATSAPP
                                + "?text=" + Uri.encode(activity.getString(R.string.whatsapp_message))))));

        new MaterialAlertDialogBuilder(activity)
                .setView(view)
                .setPositiveButton(R.string.close, null)
                .show();
    }

    private static void openIntent(AppCompatActivity activity, Intent intent) {
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(activity, R.string.no_compatible_app, Toast.LENGTH_SHORT).show();
        }
    }
}
