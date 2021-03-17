package com.example.homepage.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homepage.Adapter.ImageAdapter3;
import com.example.homepage.OrderDetails;
import com.example.homepage.OrderListModelClass;
import com.example.homepage.R;
import com.example.homepage.Upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class PaymentActivity extends AppCompatActivity {


    Button button;
    public String nam,typ,pric,pPrice,image2,details;
    EditText name,phone,adderess,mail,city;
    public static TextView backBtn,demomo;
    StorageReference storageReference;
    public String demo;
    final  int ProductNo=1;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        button=findViewById(R.id.order);
        demomo=findViewById(R.id.demo);
        databaseReference= FirebaseDatabase.getInstance().getReference("orders");

        //get rb values
//        RadioGroup rg = (RadioGroup) findViewById(R.id.paymentrg1);
//        final String value =
//                ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
//                        .getText().toString();
//
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//            }
//        });

        //get edit text values
          name=findViewById(R.id.paymentName);
          phone=findViewById(R.id.paymentProduct);
          city=findViewById(R.id.paymentCity);
          adderess=findViewById(R.id.paymentAdds);
          mail=findViewById(R.id.paymentMail);
          backBtn=findViewById(R.id.back4);






          databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {

              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String n= name.getText().toString();
               String c= city.getText().toString();
               String a= adderess.getText().toString();
               String num= phone.getText().toString();
               String m= mail.getText().toString();



               //totall all orders from cart


                //GET VALUES FROM CART

                Bundle bundle=getIntent().getExtras();
                if(bundle!=null) {
                     image2 = bundle.getString("img");
//
                     demo=bundle.getString("orders");

                    pPrice = bundle.getString("pp");
//                   typ = bundle.getString("tp");
                    details = bundle.getString("dt");






                }
                else
                {
                    Toast.makeText(PaymentActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                nam = ImageAdapter3.s;


                //store all value to firebase

                OrderListModelClass orderDetails=new OrderListModelClass(String.valueOf(ProductNo+1),nam,pPrice,image2,n,c,a,num,m);
                String uploadId=databaseReference.push().getKey();
               boolean b= databaseReference.child(uploadId).setValue(orderDetails).isComplete();











            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentActivity.this,ShowMultipleProductCart.class);
                startActivity(intent);
            }
        });
    }
}