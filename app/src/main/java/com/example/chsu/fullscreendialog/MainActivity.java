package com.example.chsu.fullscreendialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button openDialog;
    Dialog dialog;
    CustomDialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rLayout;

        openDialog = findViewById(R.id.open);

        View view = getLayoutInflater().inflate(R.layout.full_screen, null);
        dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);

        rLayout = view.findViewById(R.id.rLayout);

        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog.setVisibility(View.INVISIBLE);
                showDialog();
                Toast.makeText(MainActivity.this, "Tap to close", Toast.LENGTH_SHORT).show();
            }
        });

        rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.dismiss();
                dialogFragment.dismiss();

            }
        });
    }

    public static class CustomDialogFragment extends DialogFragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            return inflater.inflate(R.layout.full_screen, container, false);
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            return dialog;
        }
    }

    public void showDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialogFragment = new CustomDialogFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);

        transaction.add(android.R.id.content, dialogFragment).addToBackStack(null).commit();
    }
}
