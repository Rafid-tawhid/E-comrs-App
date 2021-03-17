package com.example.homepage.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.homepage.R;

public class ShowProduct extends AppCompatActivity {
    ImageView imageView;
    RelativeLayout relativeLayout1,relativeLayout2,relativeLayout3,relativeLayout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        //back button
        imageView=findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShowProduct.this, MainActivity.class);
                startActivity(intent);
            }
        });

        relativeLayout1=findViewById(R.id.img1);
        relativeLayout2=findViewById(R.id.img2);
        relativeLayout3=findViewById(R.id.img3);
        relativeLayout4=findViewById(R.id.img4);

        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(ShowProduct.this);
                LayoutInflater factory = LayoutInflater.from(ShowProduct.this);
                final View view = factory.inflate(R.layout.singelproduct, null);
                alertadd.setView(view);
                alertadd.setPositiveButton("Add to Cart", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {

                        Toast.makeText(ShowProduct.this, "Product Added to your Cart", Toast.LENGTH_SHORT).show();
                    }
                });

                alertadd.show();




            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(ShowProduct.this);
                LayoutInflater factory = LayoutInflater.from(ShowProduct.this);
                final View view = factory.inflate(R.layout.singelproduct2, null);
                alertadd.setView(view);
                alertadd.setPositiveButton("Add to Cart", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {

                        Toast.makeText(ShowProduct.this, "Product Added to your Cart", Toast.LENGTH_SHORT).show();
                    }
                });

                alertadd.show();




            }
        });
    }
}