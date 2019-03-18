package com.popcorn.time;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

// new since Glide v4
@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
    // leave empty for now
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setLogLevel(Log.VERBOSE);
    }
}
