package com.hello.cakeapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.hello.cakeapp.RealmDatabase.CakeData;
import java.io.IOException;

import io.realm.Realm;
public class GalleryPickUp extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 100;
    private ImageView iv;
    private EditText title, weight, type, cost ,detail;
    Button upload;
    byte[] byteArray;
    int nextId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_pick_up);
        iv = (ImageView) findViewById(R.id.iv);
        title=findViewById(R.id.title);
        weight=findViewById(R.id.weight);
        type=findViewById(R.id.type);
        cost=findViewById(R.id.cost);
        detail=findViewById(R.id.detail);
        Realm.init(this);

        Realm realm = Realm.getDefaultInstance();
        upload= findViewById(R.id.buttonupload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterDataToRealm();
            }
        });


    }
    private void enterDataToRealm() {
        Realm realm =Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                Number currentIdNum = realm.where(CakeData.class).max("id");

                if (currentIdNum == null) {
                    nextId = 1;

                } else {
                    nextId = currentIdNum.intValue() + 1;

                }
                CakeData data = new CakeData();
             //   data.setImage(Arrays.toString(byteArray));
                data.setTitle(title.toString());
                data.setWeight(weight.toString());
                data.setType(type.toString());
                data.setCost(cost.toString());
                data.setDetail(detail.toString());
                data.setId(nextId);
                realm.insertOrUpdate(data);
                Toast.makeText(getApplicationContext(),"Hurrah! Data uploaded to Database",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void pick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

       /** Bitmap bmp = (Bitmap) Objects.requireNonNull(intent.getExtras()).get("image");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteArray = stream.toByteArray();**/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        iv.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

}
