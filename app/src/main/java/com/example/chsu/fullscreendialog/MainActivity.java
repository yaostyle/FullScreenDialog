package com.example.chsu.fullscreendialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    static Button openDialog;
    static CustomDialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDialog = findViewById(R.id.open);

        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openDialog.setVisibility(View.INVISIBLE);
                showDialog();
            }
        });

        View dialogView = getLayoutInflater().inflate(R.layout.full_screen, null);

        Button closeDialog = dialogView.findViewById(R.id.close);

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        "Close button click...",
                        Toast.LENGTH_SHORT).show();
                dialogFragment.dismissAllowingStateLoss();
            }
        });
    }

    public static class CustomDialogFragment extends DialogFragment {
        public CustomDialogFragment() {
            //Empty
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View dialogView = inflater.inflate(R.layout.full_screen, container, false);

            Button closeDialog = dialogView.findViewById(R.id.close);

            closeDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   dialogFragment.dismissAllowingStateLoss();
                }
            });

            return dialogView;
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
