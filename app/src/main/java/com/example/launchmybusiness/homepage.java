package com.example.launchmybusiness;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


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
        TipsClass tips5= new TipsClass("tips5","Tips for Desicion Making" );
        TipsClass tips6= new TipsClass("tips6","Tips To Improve Efficiency" );
        TipsClass tips7= new TipsClass("tips7","Tips for Time Management" );
        TipsClass tips8= new TipsClass("tips8","Trust your Instincts" );
        TipsClass tips9= new TipsClass("tips9","Entrepreneurial Needs" );
        TipsClass [] tips={tips1,tips2,tips3,tips4,tips5,tips6,tips7,tips8,tips9};
        int XMlFILE=R.layout.list_tips;

        TipsAdapter adapter =new TipsAdapter(homepage.this,XMlFILE,tips);
        tipslistview.setAdapter(adapter);
        //evenemnt to go to the next activity
        tipslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent=new Intent(view.getContext(),SmallBusiness.class);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent=new Intent(view.getContext(),Growth.class);
                    startActivity(intent);
                }
                if(position==2){
                    Intent intent=new Intent(view.getContext(),DosDont.class);
                    startActivity(intent);
                }
            }
        });

        spec=host.newTabSpec("listguid");
        spec.setIndicator("Guids");
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



    }
    //menu
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.ajouter_menu,menu );
        return super.onCreateOptionsMenu(menu);
    }
    //pour choisir qu'elle menu on va travailler avec.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.ajouter){

            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}


