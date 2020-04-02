package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class ButtonListAdapater { /*
    Context mContext;
    private LayoutInflater mInflater;
    List<ReponseImage> mDatas;

    public ButtonListAdapater(Context context, List<ReponseImage>rep) {
        mContext = context;
        mDatas = rep;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        "whatever you want to pass in such as images or textfiels or classes"
        data = mDatas.get(position);

        if (convertView == null) {
            "whatever layout you made, on click effect can be in layout on in code"
            convertView = mInflater.inflate(R.layout.data_list_item, null);

            holder = new ViewHolder();

            holder.tvButton = (TextView) convertView.findViewById(R.id.textViewButton);
            holder.lbButton = (ImageButton) convertView.findViewById(R.id.Button);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvButton.setText(data.getData());

        "With the button here it depends on what you want to do and how, since its perfectly fine to put an onclicklistener here or in the layout"
        holder.llButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OTherActivity.class);
                startActivity(intent);
            }
        });


        return convertView;
    }

    static class ViewHolder {
        TextView tvButton;
        ImageButton lbButton;
    }
*/
}