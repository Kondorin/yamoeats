package com.example.yamoeats;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ProductListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Product> mProductList;

    // Constructor

    public ProductListAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_product_list,null);
        TextView tvName = v.findViewById(R.id.tv_name);
        TextView tvPrice = v.findViewById(R.id.tv_price);
        TextView tvDescription = v.findViewById(R.id.tv_description);
        TextView tvCelular = v.findViewById(R.id.tv_celular);

        tvName.setText("Direccion: " + mProductList.get(position).getName());
        tvPrice.setText("Sabor: "+ mProductList.get(position).getPrice());
        tvDescription.setText("Usuario: " + mProductList.get(position).getDescription());
        tvCelular.setText("Celular: " + mProductList.get(position).getCelular());
        // TextView tvNopedido = (TextView)v.findViewById(R.id.tv_nopedido);
        //TextView tvDireccion = (TextView)v.findViewById(R.id.tv_direccion);
        //TextView tvNombrepizza = (TextView)v.findViewById(R.id.tv_nombrepizza);
        //TextView tvUsuario = (TextView)v.findViewById(R.id.tv_usuario);
        //TextView tvCelular = (TextView)v.findViewById(R.id.tv_celular);
        //set text for textview
        //tvNopedido.setText(mProductList.get(position).getNopedido());
        //tvDireccion.setText(mProductList.get(position).getDireccion());
        //tvNombrepizza.setText(mProductList.get(position).getSabor());
        //tvUsuario.setText(mProductList.get(position).getUsuario());
        //tvCelular.setText(mProductList.get(position).getCelular());

        v.setTag(mProductList.get(position).getId());
        return v;
    }
}

