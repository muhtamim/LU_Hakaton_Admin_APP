package com.example.muhtamimnahid.lu_hakaton_admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class IdeaAdapter extends ArrayAdapter<Posts> {
    Context context;
    private DatabaseReference mDatabase;

    public IdeaAdapter(Context context, ArrayList<Posts> products) {
        super(context, 0, products);
        this.context = context;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Posts Posts = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product_row, parent, false);
        }
        TextView Title = (TextView) convertView.findViewById(R.id.Title);
        TextView Date_posted = (TextView) convertView.findViewById(R.id.Dateposted);
        TextView Userid = (TextView) convertView.findViewById(R.id.User_id);
        TextView Descrip_tion = (TextView) convertView.findViewById(R.id.Description);
        TextView imageurl = (TextView) convertView.findViewById(R.id.image_url);
        ImageButton deleteitem = convertView.findViewById(R.id.delete_btn);
        ImageButton edititem = convertView.findViewById(R.id.edit_btn);

        Title.setText(Posts.getTitle());
        Descrip_tion.setText("Description: " + Posts.getDescription());
        Userid.setText("User id:" + Posts.getUser_id());
        imageurl.setText("image url:" + Posts.getImage_url());
        Date_posted.setText("Date posted: "+Posts.getDate_posted());
        edititem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editproductintent = new Intent(context, EditIdeaActivity.class);
                editproductintent.putExtra("Title", Posts.getTitle());
                editproductintent.putExtra("Description", Posts.getDescription());
                editproductintent.putExtra("User_id", Posts.getUser_id());
                editproductintent.putExtra("image_url", Posts.getImage_url());
                editproductintent.putExtra("Date_posted", Posts.getDate_posted());
                editproductintent.putExtra("Posts", Posts.getId());

                context.startActivity(editproductintent);
            }
        });
        deleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want to delete this product?");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mDatabase.child(Posts.getId()).removeValue();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
        return convertView;
    }
}
