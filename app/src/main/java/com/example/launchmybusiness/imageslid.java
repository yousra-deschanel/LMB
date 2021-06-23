package com.example.launchmybusiness;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class imageslid extends AppCompatActivity {
//initialize variable
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<MainData>dataArrayList= new ArrayList<>();
    MainAdapter adapter;
    int page = 1 , limit= 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageslid);

        //assign variable
        nestedScrollView=findViewById(R.id.scroll_view);
        recyclerView=findViewById(R.id.recycler_view);
        progressBar=findViewById(R.id.progress_bar);

        //initialize adapter nbadal imageslid
        adapter=new MainAdapter(imageslid.this,dataArrayList);
        //set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //set adapter
        recyclerView.setAdapter(adapter);

        //create get data method
        getData(page,limit);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //check condition
                if (scrollY== v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    //when reach last item position
                    //increase page size
                    page++;
                    //show progress bar
                    progressBar.setVisibility(View.VISIBLE);
                    //call method
                    getData(page,limit);
                }
            }
        });
    }

    private void getData(int page, int limit) {
        //initialize retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://picsum.photos/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        //create main interface
        MainInterface mainInterface=retrofit.create(MainInterface.class);
        //initialize call
        Call<String> call=mainInterface.STRING_CALL(page,limit);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //check condition
                if(response.isSuccessful()&& response.body() !=null){
                    //when response is successful and nnot empty
                    //hide progress bar
                    progressBar.setVisibility(View.GONE);

                    try {
                        //initialize json array
                        JSONArray jsonArray = new JSONArray(response.body());
                        //parse json array
                        parseResult(jsonArray);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void parseResult(JSONArray jsonArray) {
        // use for loop
        for (int i=0; i<jsonArray.length(); i++){

            try {
                //initialize json object
                JSONObject object = jsonArray.getJSONObject(i);
                //initialize main data
                MainData data = new MainData();
                //set image
                data.setImage((object.getString("download_url")));
                //set name
                data.setName(object.getString("author"));
                //add data in array list
                dataArrayList.add(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //initialize adapter nbadal imageslid
            adapter= new MainAdapter(imageslid.this.dataArrayList);
            //set adapter
            recyclerView.setAdapter(adapter);
        }
    }
}
