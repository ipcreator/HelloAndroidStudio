package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by user on 2/25/2017.
 */

public class MyUtility {

    public static final int RELEASE = 0;
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;

    public static final int level = VERBOSE;

    public static void ShowInfoDialog(String title, String Message)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MyApplication.getContext());
        dialog.setTitle("title");
        dialog.setMessage("something important:  " + Message);
        dialog.setCancelable(false);

        dialog.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    public static void MyLog(String tag, String msg){

        if(level <= VERBOSE){
            Log.v(tag,msg);
        }else if(level <= DEBUG){
            Log.d(tag,msg);
        }else if(level <= INFO)
        {
            Log.i(tag,msg);
        }else if(level <= WARN)
        {
            Log.w(tag,msg);
        }else if(level <= ERROR)
        {
            Log.e(tag,msg);
        }
    }
}
