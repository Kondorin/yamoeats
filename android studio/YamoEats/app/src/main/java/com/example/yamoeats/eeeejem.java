package com.example.yamoeats;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class eeeejem {

    private Context mContext;
    private List<Product> mProductList;


    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_product_list,null);
        TextView tvName = v.findViewById(R.id.tv_name);
        TextView tvPrice = v.findViewById(R.id.tv_price);
        TextView tvDescription = v.findViewById(R.id.tv_description);
        TextView tvCelular = v.findViewById(R.id.tv_celular);

        return v;
    }
}
