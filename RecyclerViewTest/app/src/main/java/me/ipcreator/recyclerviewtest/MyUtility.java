package me.ipcreator.recyclerviewtest;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by user on 2/25/2017.
 */

public class MyUtility {

    public static void ShowInfoDialog(Context context, String title, String Message)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
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
}
