package com.example.homepage.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.homepage.Adapter.MenzImageAdapter;
import com.example.homepage.R;

import java.util.ArrayList;

public class ShowMenzImageRv extends AppCompatActivity {

    TextView back;
    private final String android_version_names[] = {
            "Shirt",
            "T-Shirt",
            "Blezzer",
            "Suit",
            "Shirt",
            "Loffer",
            "Snikers",
            "Loffers",
            "Jeans Pants",
            "Marshmallow"
    };
    private final String android_version_prices[] = {"500","600","300","400","250","500","600","300","400","250"
    };

    private final String android_image_urls[] = {
            "https://ae01.alicdn.com/kf/H93b944ed556f4a6fbe6ff417380ae48eJ/Mens-Dress-Shirts-Men-s-Solid-color-Slanted-Front-Double-breasted-Slim-and-Stylish-Long-Sleeve.jpg",
            "https://ae01.alicdn.com/kf/H036765afb25449f0a135c92a77615fd8Q/Fashion-Shirt-Men-Party-Mens-Dress-Shirts-Long-Sleeve-Camisa-Social-Masculina-Men-Black-White-Embroidery.jpg",
            "https://manofmany.com/wp-content/uploads/2017/11/Guide-mens-smart-casual-dress-code15.jpg",
            "https://ae01.alicdn.com/kf/HTB1FfzrOVzqK1RjSZFoq6zfcXXac/Fashion-Business-Plaid-Suits-Men-Size-3XL-Slim-Mens-Dress-Suit-New-Popular-Stand-Collar-Man.jpg",
            "https://m.media-amazon.com/images/I/81bjVMZUJBL.jpg",
            "https://www.dealmartbd.com/wp-content/uploads/2018/08/34707613_638521733159518_2177903372349210624_n-600x800.jpg",
            "https://i1.wp.com/mensaccessoriesbd.com/wp-content/uploads/2020/04/Men-Formal-Shoes-5.jpg?resize=600%2C600&ssl=1",
            "https://www.dealmartbd.com/wp-content/uploads/2018/08/34635617_638550283156663_4634247683496738816_n.jpg",
            "https://i.pinimg.com/originals/e4/6e/c6/e46ec608e5d04e63dc42e2d2f083a339.png",
            "https://img.topchinasupplier.com/file/upload/2020/04/19/Wholesale-Mens-Fashion-Sports-Jogger-Sweatpants-Hip-Hop-Streetwear-Harem-Pants-with-Pockets-2.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menz_image_rv);

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