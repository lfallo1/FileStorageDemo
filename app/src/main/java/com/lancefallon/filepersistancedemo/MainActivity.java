package com.lancefallon.filepersistancedemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GridFragment.OnImageSelectedInterface{

    private static final String GRID_FRAGMENT = "GRID_FRAGMENT";
    private static final String FRAGMENT_FULL_IMAGE = "FRAGMENT_FULL_IMAGE";
    private View saveFilesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveFilesButton = findViewById(R.id.savePhotoButton);
        saveFilesButton.setOnClickListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        GridFragment savedGridFragment = (GridFragment) fragmentManager.findFragmentByTag(GRID_FRAGMENT);
        if(savedGridFragment == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeholder, new GridFragment(), GRID_FRAGMENT);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == saveFilesButton){
            FileUtilities fileUtilities = FileUtilities.getInstance();
            String[] files;
            try {
                files = MainActivity.this.getAssets().list("");
                for(int i = 0; i < files.length; i++){
                    if(files[i].endsWith(".png")){
                        fileUtilities.saveFile(this, files[i]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onImageSelected(String absoluteFilePath) {
        Bundle bundle = new Bundle();
        bundle.putString(FullImageFragment.EXTRA_FULL_IMAGE_PATH, absoluteFilePath);
        Fragment fullImageFragment = new FullImageFragment();
        fullImageFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder, fullImageFragment, FRAGMENT_FULL_IMAGE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
