package com.example.muhtamimnahid.lu_hakaton_admin;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditIdeaActivity extends AppCompatActivity {
    @BindView(R.id.edituser_edt)
    TextInputEditText edituseredt;
    @BindView(R.id.editdate_posted)
    TextInputEditText editdateposted;
    @BindView(R.id.edit_title)
    TextInputEditText edittitle;
    @BindView(R.id.image_urledit)
    TextInputEditText imageurledit;
    @BindView(R.id.Descriptionedt)
    TextInputEditText Descriptioned;
    @BindView(R.id.update)
    Button update;
    String Userid, Title, imageurl, id,Description,Dateposted;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Userid = getIntent().getExtras().getString("User_id");
        Title = getIntent().getExtras().getString("Title");
        imageurl = getIntent().getExtras().getString("image_url");
        Description = getIntent().getExtras().getString("Description");
        Dateposted = getIntent().getExtras().getString("Date_posted");
        id = getIntent().getExtras().getString("My_Posts");

        edituseredt.setText(Userid);
        editdateposted.setText(Dateposted);
        edittitle.setText(Title);
        imageurledit.setText(imageurl);
        Descriptioned.setText(Description);

    }

    public void update(View view) {
        String E_name, E_date, E_title,E_image,E_Des;
        E_name = edituseredt.getText().toString();
        E_date = editdateposted.getText().toString();
        E_title = edittitle.getText().toString();
        E_image = imageurledit.getText().toString();
        E_Des = Descriptioned.getText().toString();
        Posts Posts = new Posts(E_name, E_date, E_title,E_image,E_Des);
        Posts.setId(id);
        mDatabase.child("My_Posts").child(id).setValue(Posts);
        Toast.makeText(this, "data Edited Successfully^^", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}
