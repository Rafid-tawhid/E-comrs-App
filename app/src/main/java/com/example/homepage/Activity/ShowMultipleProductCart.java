package com.example.homepage.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homepage.Adapter.ImageAdapter2;
import com.example.homepage.Adapter.ImageAdapter3;
import com.example.homepage.DataTransferInterface;
import com.example.homepage.R;
import com.example.homepage.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowMultipleProductCart extends AppCompatActivity implements DataTransferInterface {
    RecyclerView recyclerView3;
    ImageAdapter3 imageAdapter2,imageAdapter3;
    DatabaseReference databaseReference;

    public static TextView chekOutPrice,charged,totalPrice;
    ImageView back;
    Button button;
    public int in;
    List<Upload> mUploadS;
    public String SpecialVal;
    SharedPreferences sharedpreferences;
  
    String nam,typ,image,pp,details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.multiplare_add_cart_show);

        recyclerView3=findViewById(R.id.cartRv);
        button=findViewById(R.id.chechoutbtn);
        chekOutPrice=findViewById(R.id.checkoutprice3);
        charged=findViewById(R.id.chargd);
        totalPrice=findViewById(R.id.ttl2);
        back=findViewById(R.id.back5);



        recyclerView3.setLayoutManager(new LinearLayoutManager(ShowMultipleProductCart.this));
        mUploadS =new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("cart");




        //get intent data from product details activity


        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
             image = bundle.getString("img");
            pp=bundle.getString("pp");
            typ=bundle.getString("tp");
            details=bundle.getString("dt");
            nam=bundle.getString("nm");


        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }





        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mUploadS.clear();
                for (DataSnapshot postSnap:snapshot.getChildren())
                {
                    Upload uploadS =postSnap.getValue(Upload.class);
                    uploadS.setKey(postSnap.getKey());
                    mUploadS.add(uploadS);


                }

                imageAdapter2=new ImageAdapter3(ShowMultipleProductCart.this, mUploadS);
                imageAdapter3=new ImageAdapter3(ShowMultipleProductCart.this, mUploadS);
                recyclerView3.setAdapter(imageAdapter2);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ShowMultipleProductCart.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowMultipleProductCart.this,PaymentActivity.class);


                intent.putExtra("orders",ImageAdapter3.s);

                intent.putExtra("img",image);

                if (ImageAdapter3.total==0)
                {
                    intent.putExtra("pp",pp);
                }
                else {
                    intent.putExtra("pp", String.valueOf(ImageAdapter3.total));

                }
                intent.putExtra("nm",nam);
                intent.putExtra("dt",details);
                intent.putExtra("tp",typ);


                startActivity(intent);

            }
        });

        //back to product page
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowMultipleProductCart.this,ShowDwnloadImageRvActivity.class);

                startActivity(intent);
            }
        });
    }


    //INTERFACE'S METHODS
    @Override
    public void onSetValues(String al) {

        SpecialVal=al;

    }
    @Override
    public void onBackPressed() {

        return;
    }
}