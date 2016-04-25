package com.lancefallon.filepersistancedemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtilities {

    private static FileUtilities instance;

    private FileUtilities(){ }

    public static FileUtilities getInstance(){
        if(instance == null){
            instance = new FileUtilities();
        }
        return instance;
    }

    public List<File> getAllPng(Context context){
        File fileDir = context.getFilesDir();
        File[] files = fileDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getAbsolutePath().endsWith(".png");
            }
        });

        return Arrays.asList(files);
    }

    public void saveFile(Context context, String filename) {
        File fileToWrite = new File(context.getFilesDir(), filename);
        if(fileToWrite.exists()){
            Toast.makeText(context, "File already exists...", Toast.LENGTH_LONG).show();
            return;
        }
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(filename);
            OutputStream outputStream = new FileOutputStream(fileToWrite);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            Toast.makeText(context, "File copied successfully...", Toast.LENGTH_LONG).show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public Bitmap bitmapFromPath(String path){
        return BitmapFactory.decodeFile(path);
    }

    private Bitmap bitmapFromInputStream(InputStream inputStream) {
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

}
