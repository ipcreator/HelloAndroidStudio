package me.ipcreator.recyclerviewtest;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.jar.Manifest;

public class CameraActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private ImageView photo;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button takePhoto = (Button) findViewById(R.id.take_photo);
        Button choosePhoto = (Button) findViewById(R.id.choose_photo);

        photo = (ImageView) findViewById(R.id.photo);

        takePhoto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                File outputImage = new File(getExternalCacheDir(),"output_image.jpg");

                if(outputImage.exists())
                {
                    outputImage.delete();
                }

                if(Build.VERSION.SDK_INT >=24 ){
                    imageUri = FileProvider.getUriForFile(CameraActivity.this,"me.ipcreator.recyclerviewtest.fileprovider",outputImage);
                }else
                {
                    imageUri = Uri.fromFile(outputImage);
                }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });

        choosePhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(CameraActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CameraActivity.this,
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                } else {

                    openAlbum();
                }
            }
        });
}

    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults){

        switch(requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(this,"You denied the permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK)
                {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        photo.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK)
                {
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                }
            default:
                break;
        }
    }

    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();

        if(DocumentsContract.isDocumentUri(this,uri)){

            String docID = DocumentsContract.getDocumentId(uri);

            if("com.android.providers.media.documents".equals(uri.getAuthority())){

                String id = docID.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" +id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);

            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){

                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docID));
                imagePath = getImagePath(contentUri,null);

            }

        }else if("content".equalsIgnoreCase(uri.getScheme())){

            imagePath = getImagePath(uri,null);

        }else if("file".equalsIgnoreCase(uri.getScheme())){

            imagePath = uri.getPath();

        }

        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data)
    {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection){
        String path = null;

        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            photo.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,CameraActivity.class);
        intent.putExtra("NAME",data1);
        intent.putExtra("IMG",data2);
        context.startActivity(intent);
    }
}
