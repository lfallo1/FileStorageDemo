package com.lancefallon.filepersistancedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ImageViewHolder> {

    private final List<File> mFiles;
    private final GridFragment.OnImageSelectedInterface mListener;

    public GridAdapter(List<File> files, GridFragment.OnImageSelectedInterface listener){
        mFiles = files;
        mListener = listener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.bindData(mFiles.get(position));
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImageView;
        private File mFile;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.grid_item_imageView);
            mImageView.setOnClickListener(this);
        }

        public void bindData(File file){
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            mImageView.setImageBitmap(bitmap);
            mFile = file;
        }

        @Override
        public void onClick(View v) {
            mListener.onImageSelected(mFile.getAbsolutePath());
        }
    }
}
