package com.example.homepage.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homepage.Adapter.adapterphone;
import com.example.homepage.R;
import com.example.homepage.Adapter.samileradapterProduct4;
import com.example.homepage.Upload;
import com.example.homepage.samilerproductHelperClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class productDetailsActivity extends AppCompatActivity implements adapterphone.ListItemClickListener {
    RecyclerView similer_product_recyc;
    RecyclerView.Adapter adapter;
    TextView count;
    ImageView imageView,imageView2,cart,oldCart;
    Button button;

   public String a;
    FloatingActionButton floatingActionButton;


     String nam,typ,image,pp,details;
    TextView name,type,price,pdetails;
    StorageReference storageReference;
    private FirebaseStorage mStorage;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        imageView=findViewById(R.id.back2);
        imageView2=findViewById(R.id.i1);
        oldCart=findViewById(R.id.oldCarte);
        cart=findViewById(R.id.cartId);
        count=findViewById(R.id.productCount);
        floatingActionButton=findViewById(R.id.fab);

        name=findViewById(R.id.pName);
        type=findViewById(R.id.pType);
        price=findViewById(R.id.pp);
        pdetails=findViewById(R.id.pDetails);

        similer_product_recyc = findViewById(R.id.samiler_product_recycler);
        button = findViewById(R.id.addcartbtn);
        similerProductRecycler();

        mStorage=FirebaseStorage.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference("cart");
        databaseReference= FirebaseDatabase.getInstance().getReference("cart");



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count.setText(String.valueOf(snapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




//        }


        //default image
        imageView2.setImageResource(R.drawable.shirt);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(productDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        oldCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(productDetailsActivity.this, CartActivity.class);
                        intent.putExtra("img",image);
                        intent.putExtra("pp",pp);
                        intent.putExtra("nm",nam);
                        intent.putExtra("dt",details);
                        intent.putExtra("tp",typ);

                startActivity(intent);
            }
        });

        //get percelable object from database

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
             image=bundle.getString("img");
             pp=bundle.getString("pp");
             typ=bundle.getString("tp");
             details=bundle.getString("dt");
             nam=bundle.getString("nm");

            Picasso.get().load(image).fit().centerCrop().into(imageView2);

            price.setText(pp);
            name.setText(nam);
            type.setText(typ);
            pdetails.setText(details);




        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //send product info to cart


                floatingActionButton.setVisibility(View.VISIBLE);

                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent=new Intent(productDetailsActivity.this, ShowDwnloadImageRvActivity.class);
//                        intent.putExtra("img",image);
//                        intent.putExtra("pp",pp);
//                        intent.putExtra("nm",nam);
//                        intent.putExtra("dt",details);
//                        intent.putExtra("tp",typ);
                        startActivity(intent);

                    }
                });

                Upload upload3=new Upload(nam,typ,details,pp,image,"key",1);
                String uploadId=databaseReference.push().getKey();
                databaseReference.child(uploadId).setValue(upload3);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        a=String.valueOf(snapshot.getChildrenCount());
                       count.setText(String.valueOf(snapshot.getChildrenCount()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });












            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(productDetailsActivity.this,ShowMultipleProductCart.class);

                intent.putExtra("img",image);
                intent.putExtra("pp",pp);
                intent.putExtra("nm",nam);
                intent.putExtra("dt",details);
                intent.putExtra("tp",typ);


                startActivity(intent);





            }
        });





    }

    private void similerProductRecycler() {
        similer_product_recyc.setHasFixedSize(true);
        similer_product_recyc.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<samilerproductHelperClass> samilerproductHelperClasses = new ArrayList<>();
        samilerproductHelperClasses.add(new samilerproductHelperClass( R.drawable.laptop));
        samilerproductHelperClasses.add(new samilerproductHelperClass( R.drawable.lava));
        samilerproductHelperClasses.add(new samilerproductHelperClass( R.drawable.samsung));
        samilerproductHelperClasses.add(new samilerproductHelperClass( R.drawable.redmi));
        samilerproductHelperClasses.add(new samilerproductHelperClass( R.drawable.lava));
        adapter = new samileradapterProduct4(samilerproductHelperClasses,this::onphoneListClick);
        similer_product_recyc.setAdapter(adapter);


    }


    //recyclerview click
    public void onphoneListClick(int clickedItemIndex) {


        Intent mIntent;
        switch (clickedItemIndex){
            case 0: //first item in Recycler view

                break;
            case 1: //first item in Recycler view
                mIntent  = new Intent(productDetailsActivity.this, ShowDwnloadImageRvActivity.class);
                startActivity(mIntent);
                break;
            case 2: //first item in Recycler view
                mIntent  = new Intent(productDetailsActivity.this, ShowDwnloadImageRvActivity.class);
                startActivity(mIntent);
                break;
            case 3: //first item in Recycler view
                mIntent  = new Intent(productDetailsActivity.this, ShowDwnloadImageRvActivity.class);
                startActivity(mIntent);
                break;



        }




    }





}