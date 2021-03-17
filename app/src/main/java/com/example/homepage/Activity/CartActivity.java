package com.example.homepage.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homepage.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class CartActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;
    public String nam,typ,pric,pPrice,image,details;
    TextView name,type,price,price2,price3,total,pplus,pminus,pcount,totalProduct;
    private int count=1;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        button=findViewById(R.id.chechoutbtn);
        name=findViewById(R.id.nm);
        type=findViewById(R.id.tp);
        price=findViewById(R.id.pp);
        pplus=findViewById(R.id.plus);
        pminus=findViewById(R.id.minus);
        pcount=findViewById(R.id.one);
        price2=findViewById(R.id.prc);
        total=findViewById(R.id.ttl);
        totalProduct=findViewById(R.id.prdttl);
        price3=findViewById(R.id.checkoutprice);
        imageView=findViewById(R.id.img);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);

        //default image

        imageView.setImageResource(R.drawable.shirt);



        //get product Info
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            image = bundle.getString("img");
//
             pPrice = bundle.getString("pp");
            String typ = bundle.getString("tp");
            String details = bundle.getString("dt");
            String nam = bundle.getString("nm");
//
            Picasso.get().load(image).fit().centerCrop().into(imageView);
//
            price2.setText("Price: "+pPrice+"$");
            price.setText("$"+pPrice);
            type.setText(typ);
            name.setText(nam);
            price3.setText(pPrice+"$");
//            pdetails.setText(details);






        }
        else
        {
            Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
        }






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent=new Intent(CartActivity.this, PaymentActivity.class);
                intent.putExtra("img",image);
                intent.putExtra("pp",total.getText());
                intent.putExtra("nm",nam);
                intent.putExtra("dt",details);
                intent.putExtra("tp",typ);



                startActivity(intent);

            }
        });




        //bottom

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_cart);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.bottom_nav_cart: {
                        Intent intent = new Intent(CartActivity.this, CartActivity.class);
                        startActivity(intent);
                        break;

                    }
                    case R.id.bottom_nav_product:


                        break;
                    case R.id.bottom_nav_home:
                        Intent intent3=new Intent(CartActivity.this, MainActivity.class);
                        startActivity(intent3);
                        break;

                }
                return false;
            }
        });

        //toolbar
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle=new
                ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.nav_men)
                {

                }
                return false;
            }
        });
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.bringToFront();




        ///counter

        total.setText(pPrice);
        pplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            String pls =pcount.getText().toString();
            Integer a = Integer.parseInt(pls)+1;
            pcount.setText(String.valueOf(a));
            totalProduct.setText("Product: "+a);


            price3.setText(String.valueOf(Integer.parseInt(pPrice)*a));

                //total price calculation
//           String str = pPrice.replace(pric.substring(pric.length()-1), "");
                String str=price3.getText().toString();
////
             Integer p =Integer.parseInt(str);
//
             Integer b=p+50;
             total.setText(String.valueOf(b)+"$");


            }
        });
        pminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String min =pcount.getText().toString();
                Integer a = Integer.parseInt(min)-1;
                pcount.setText(String.valueOf(a));
                totalProduct.setText("Product: "+a);
                price3.setText(String.valueOf(Integer.parseInt(pPrice)*a));



                //total price calculation
//           String str = pPrice.replace(pric.substring(pric.length()-1), "");
                String str=price3.getText().toString();
////
                Integer p =Integer.parseInt(str);
//
                 Integer b=p+50;
                total.setText(String.valueOf(b)+"$");
            }
        });



    }




}