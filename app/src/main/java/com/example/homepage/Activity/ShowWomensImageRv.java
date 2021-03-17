package com.example.homepage.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.homepage.Adapter.MenzImageAdapter;
import com.example.homepage.R;

import java.util.ArrayList;

public class ShowWomensImageRv extends AppCompatActivity {
    TextView back;
    private final String android_version_names[] = {
            "Tops",
            "Skirt",
            "Shirt",
            "Makeup",
            "Foundation",
            "Lipstic",
            "Sari",
            "Tops",
            "Sari",
            "Pants"
    };
    private final String android_version_prices[] = {"1500","1600","1300","1400","1250","1500","1600","1300","4100","1250"
    };

    private final String android_image_urls[] = {
            "https://i.pinimg.com/originals/8c/31/b1/8c31b1963cb961920719d553651e3518.jpg",
            "https://i.pinimg.com/originals/13/76/0d/13760d2614ce7eea69de0a2b5d00935e.jpg",
            "https://www.pack-rabbit.com/wp-content/uploads/2019/11/PROD_T-SHIRT_Own-Tracks-Women-Black-Frost.jpg",
            "https://cdn3.vectorstock.com/i/1000x1000/24/07/makeup-products-for-women-colored-hand-drawn-vector-16402407.jpg",
            "https://i.pinimg.com/originals/99/10/61/991061cdbe5afd36c4b5405953cba192.jpg",
            "https://images-na.ssl-images-amazon.com/images/I/71E%2BPcLlvaL._AC_UY445_.jpg",
            "https://img.faballey.com/Images/Product/TOP04795Z/d3.jpg",
            "https://merchshop.in/wp-content/uploads/2019/04/Apna-Time-Aayega-Women-Tshirt-Black-1.jpg",
            "https://hashrail.sgp1.cdn.digitaloceanspaces.com/CND/Product/large/CD494_1.jpg",
            "https://pyxis.nymag.com/v1/imgs/4ef/216/85290e24e72bfe22157114dd6bf9e7a306-200313-03.rsquare.w600.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_womens_image_rv);
        back=findViewById(R.id.back3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initViews();
    }
    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList androidVersions = prepareData();
        MenzImageAdapter adapter = new MenzImageAdapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList prepareData(){

        ArrayList android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            com.example.homepage.MenzImageModelClass menzImageModelClass = new com.example.homepage.MenzImageModelClass();
            menzImageModelClass.setAndroid_version_name(android_version_names[i]);
            menzImageModelClass.setAndroid_image_price(android_version_prices[i]);
            menzImageModelClass.setAndroid_image_url(android_image_urls[i]);
            android_version.add(menzImageModelClass);
        }
        return android_version;
    }
}