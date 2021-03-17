package com.example.homepage.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.Activity.productDetailsActivity;
import com.example.homepage.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenzImageAdapter extends RecyclerView.Adapter<MenzImageAdapter.ViewHolder> {
    private ArrayList<com.example.homepage.MenzImageModelClass> android_versions;
    private Context context;

    public MenzImageAdapter(Context context, ArrayList<com.example.homepage.MenzImageModelClass> android_versions) {
        this.context = context;
        this.android_versions = android_versions;

    }

    @Override
    public MenzImageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singelproduct, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.name.setText(android_versions.get(i).getAndroid_version_name());
        viewHolder.price.setText(String.valueOf(android_versions.get(i).getAndroid_image_price()));
        Picasso.get().load(android_versions.get(i).getAndroid_image_url()).fit().centerCrop().into(viewHolder.img_android);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= viewHolder.getAdapterPosition();

                //show product details on dialog
                final Dialog dialog = new Dialog(v.getContext());
                // Include dialog.xml file
                dialog.setContentView(R.layout.singelproduct2);
                // Set dialog title
                dialog.setTitle(android_versions.get(position).getAndroid_version_name());

                TextView text = (TextView) dialog.findViewById(R.id.price3);
                text.setText(android_versions.get(position).getAndroid_image_price());
                TextView text2 = (TextView) dialog.findViewById(R.id.pdetails);
                text2.setText(android_versions.get(position).getAndroid_image_details());
                TextView text3 = (TextView) dialog.findViewById(R.id.type3);
                text3.setText(android_versions.get(position).getAndroid_image_type());
                ImageView image = (ImageView) dialog.findViewById(R.id.showImg);
                Picasso.get().load(android_versions.get(position).getAndroid_image_url()).fit().centerCrop().into(image);




                dialog.show();

                TextView shopButton=dialog.findViewById(R.id.addCart);
                shopButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



//
                        //send object and position to details page

                        Intent intent=new Intent(v.getContext(), productDetailsActivity.class);

                        dialog.dismiss();
                        intent.putExtra("img",android_versions.get(position).getAndroid_image_url());
                        intent.putExtra("pp",android_versions.get(position).getAndroid_image_price());
                        intent.putExtra("nm",android_versions.get(position).getAndroid_version_name());
                        intent.putExtra("dt",android_versions.get(position).getAndroid_image_details());
                        intent.putExtra("tp",android_versions.get(position).getAndroid_image_type());

                        v.getContext().startActivity(intent);



                    }
                });


            }
        });

    }

    @Override
    public int getItemCount() {
        return android_versions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price;
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            name = (TextView)view.findViewById(R.id.type2);
            price = (TextView)view.findViewById(R.id.price2);
            img_android = (ImageView)view.findViewById(R.id.image1);
        }
    }
}
