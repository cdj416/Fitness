package com.hongyuan.fitness.custom_view.camera_view;

import android.graphics.Bitmap;

import java.io.File;

public interface CameraStateListener {

    void onCameraOpend();
    void onPreviewStart();
    void onPreviewStop();
    void onShutter();
    void onCupture(Bitmap bitmap);
    void onCut(File file);
    void onCameraClosed();
}
