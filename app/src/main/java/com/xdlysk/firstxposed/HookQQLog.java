package com.xdlysk.firstxposed;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Jeqee on 2017/7/28.
 */

public class HookQQLog implements IXposedHookLoadPackage {
    private final static String TAG = "HookQQLog";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if(!loadPackageParam.packageName.equals("com.tencent.mobileqq")){
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
                XposedHelpers.findAndHookMethod("com.tencent.qphone.base.util.QLog", loadPackageParam.classLoader, "isColorLevel", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(true);
                    }
                });
            }
        });
    }
}
