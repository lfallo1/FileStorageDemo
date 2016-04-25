package com.lancefallon.filepersistancedemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.List;

public class GridFragment extends Fragment{

    public interface OnImageSelectedInterface{
        void onImageSelected(String absoluteFilePath);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_grid, container, false);

        OnImageSelectedInterface listener = (OnImageSelectedInterface) getActivity();
        List<File> files = FileUtilities.getInstance().getAllPng(getActivity());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        GridAdapter gridAdapter = new GridAdapter(files, listener);
        recyclerView.setAdapter(gridAdapter);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numColumns = (int)(dpWidth / 200);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), numColumns);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
