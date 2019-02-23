package com.yonicohen.myeasyride.tools;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import java.lang.ref.WeakReference;

/**
 * Config the application.
 */
public class App extends Application {

    private static WeakReference<Context> mContext;

    /**
     * config what happen when the application started
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = new WeakReference<Context>(this);
    }

    /**
     * get the context
     * @return the context
     */
    public static Context getContext(){ return mContext.get(); }

    /**
     * get reference to app resources
     * @return reference to the app resources
     */
    public static Resources getAppResources() { return getContext().getResources(); }
}
