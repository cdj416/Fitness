package com.hongyuan.fitness.util.audioconverter;

public interface ILoadCallback {
    
    void onSuccess();
    
    void onFailure(Exception error);
    
}