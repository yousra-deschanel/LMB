package com.example.launchmybusiness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class homepage extends AppCompatActivity {
 TabHost host;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        host=findViewById(R.id.tabhost1);
        host.setup();


        //creation des onglets
        TabHost.TabSpec spec=host.newTabSpec("text");
        spec.setIndicator("Business Tips");
        spec.setContent(R.id.tab1);
        //narbathoum host m3a spec
        host.addTab(spec);

        //Déclaration de list view + n3amar tableau
        ListView tipslistview=findViewById(R.id.listviewXML);
        //n3amar tableau

        TipsClass tips1= new TipsClass("tips1","Tips for Small Business" );
        TipsClass tips2= new TipsClass("tips2","Tips for Growth" );
        TipsClass tips3= new TipsClass("tips3","Dos and Don'ts" );
        TipsClass tips4= new TipsClass("tips4","Selection of Business Partner" );
        TipsClass tips5= new TipsClass("tips5","Tips for Decision Making" );
        TipsClass tips6= new TipsClass("tips6","Tips To Improve Efficiency" );
        TipsClass tips7= new TipsClass("tips7","Tips for Time Management" );
        TipsClass tips8= new TipsClass("tips8","Trust your Instincts" );
        TipsClass tips9= new TipsClass("tips9","Entrepreneurial Needs" );
        TipsClass [] tips={tips1,tips2,tips3,tips4,tips5,tips6,tips7,tips8,tips9};
        int XMlFILE=R.layout.list_tips;

        TipsAdapter adapter =new TipsAdapter(homepage.this,XMlFILE,tips);
        tipslistview.setAdapter(adapter);

        spec=host.newTabSpec("listguid");
        spec.setIndicator("Guides");
        spec.setContent(R.id.tab2);

        //narbathoum host m3a spec
        host.addTab(spec);

        Button guid = findViewById(R.id.btn);
        guid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this, userlist.class);
                startActivity(intent);
            }
        });




        //tab Profil
        spec=host.newTabSpec("prof");
        spec.setIndicator("profile");
        spec.setContent(R.id.tab3);
        //narbathoum host m3a spec
        host.addTab(spec);



    }

}


