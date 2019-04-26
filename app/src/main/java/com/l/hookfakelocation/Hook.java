package com.l.hookfakelocation;

import android.view.WindowManager;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.rong.xposed.fakelocation")) {
            final Class<?> fxServiceClass = lpparam.classLoader.loadClass("com.rong.xposed.fakelocation.service.f.FxService");
            XposedHelpers.findAndHookMethod(fxServiceClass, "m", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Field jField = fxServiceClass.getDeclaredField("j");
                    jField.setAccessible(true);
                    WindowManager.LayoutParams jLayoutParam = (WindowManager.LayoutParams) jField.get(param.thisObject);
                    if (jLayoutParam == null) {
                        jLayoutParam = new WindowManager.LayoutParams();
                        jLayoutParam.type = 2003;
                        jLayoutParam.format = 1;
                        jLayoutParam.flags = 8;
                        jLayoutParam.gravity = 8388659;
                        jLayoutParam.width = -2;
                        jLayoutParam.height = -2;
                        jField.set(param.thisObject, jLayoutParam);
                    }
                    super.beforeHookedMethod(param);
                }
            });
            XposedHelpers.findAndHookMethod(fxServiceClass, "l", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Field hField = fxServiceClass.getDeclaredField("h");
                    hField.setAccessible(true);
                    WindowManager.LayoutParams hLayoutParam = (WindowManager.LayoutParams) hField.get(param.thisObject);
                    if (hLayoutParam == null) {
                        hLayoutParam = new WindowManager.LayoutParams();
                        hLayoutParam.type = 2003;
                        hLayoutParam.format = 1;
                        hLayoutParam.flags = 8;
                        hLayoutParam.gravity = 8388659;
                        hLayoutParam.width = -2;
                        hLayoutParam.height = -2;
                        hField.set(param.thisObject, hLayoutParam);
                    }
                    super.beforeHookedMethod(param);
                }
            });
        }
    }
}
