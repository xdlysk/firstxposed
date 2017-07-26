package com.xdlysk.firstxposed;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by sunkang on 2017/7/26.
 */

public class HookTest implements IXposedHookLoadPackage {
    private final static String TAG = "HookTest";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if(!loadPackageParam.packageName.equals("com.ss.android.article.news")){
            Log.d(TAG, "handleLoadPackage: " + loadPackageParam.packageName);
            return;
        }

        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                //super.afterHookedMethod(param);
                //XposedHelpers.findAndHookMethod("",param.)
                Log.d(TAG, "afterHookedMethod: "+ loadPackageParam.classLoader.toString());
            }
        });
    }
}
