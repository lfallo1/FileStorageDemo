package com.lancefallon.filepersistancedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FullImageFragment extends Fragment {

    public static final String EXTRA_FULL_IMAGE_PATH = "EXTRA_FULL_IMAGE_PATH";
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full_image, container, false);
        imageView = (ImageView)view.findViewById(R.id.fragment_full_image_imageView);
        setImage();
        return view;
    }

    private void setImage() {
        String path = getArguments().getString(EXTRA_FULL_IMAGE_PATH);
        Bitmap bitmap = FileUtilities.getInstance().bitmapFromPath(path);
        imageView.setImageBitmap(bitmap);
    }
}
