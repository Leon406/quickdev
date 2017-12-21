package me.leon.libs.utils;

/**
 * Created by PC on 2017/11/8.
 */

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;

public class CrashHandler implements UncaughtExceptionHandler {
    private static CrashHandler instance;


    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    public void init(Context ctx) {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 核心方法，当程序crash 会回调此方法， Throwable中存放这错误日志
     */
    @Override
    public void uncaughtException(Thread arg0, Throwable arg1) {
        String logPath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            logPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "log";
            File file = new File(logPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                FileWriter fw = new FileWriter(logPath + File.separator + "err.log", true);
                fw.write(new Date() + "\n");
                StackTraceElement[] stackTrace = arg1.getStackTrace();
                fw.write(arg1.getMessage() + "\n");
                for (int i = 0; i < stackTrace.length; i++) {
                    fw.write("file:" + stackTrace[i].getFileName()
                            + " class:" + stackTrace[i].getClassName()
                            + " method:" + stackTrace[i].getMethodName()
                            + " line:" + stackTrace[i].getLineNumber() + "\n");
                    fw.flush();
                }
                fw.write("\n");
                fw.close();
            } catch (Exception e) {
                Log.e("crash handler", "load file failed...", e.getCause());
            }
            arg1.printStackTrace();
            android.os.Process.killProcess(android.os.Process.myPid());

        }
    }
}