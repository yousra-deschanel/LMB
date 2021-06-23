package com.example.launchmybusiness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TipsAdapter extends ArrayAdapter {
    Context AdapterContext;
    int AdapterResource;
    TipsClass [] AdapterTips;

    public TipsAdapter(@NonNull Context context, int resource, @NonNull TipsClass[] TipsclassData) {
        super(context, resource,  TipsclassData);
        AdapterContext=context;
        AdapterResource=resource;
        AdapterTips= TipsclassData;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        LayoutInflater rowInflater=LayoutInflater.from(AdapterContext);
        row=rowInflater.inflate(AdapterResource,parent,false);
        TextView nameJva=(TextView)row.findViewById(R.id.text_view1);
        ImageView imageJva=(ImageView)row.findViewById(R.id.image_view1);
        TipsClass TipsAll = AdapterTips[position];

        nameJva.setText(TipsAll.TipsName);
        // nasstaRdja3 id ta3 les images bach nmadou l setImageResource
        int ImageId = AdapterContext.getResources().getIdentifier(TipsAll.ImageName, "drawable",AdapterContext.getPackageName());
        imageJva.setImageResource(ImageId);

        return row;
    }
}

