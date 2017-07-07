package com.sinjon.ticket.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sinjon.ticket.R;
import com.sinjon.ticket.pojo.Good;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongJian
 * @Date 2017/7/6
 * @Email songjian0x00@163.com
 * 商品列表适配器
 */
public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.GoodViewHolder> {
    private static final String TAG = "GoodAdapter";

    private List<Good> goodList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;

    private OnItemActionListener onItemActionListener;

    public GoodAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void addList(List<Good> goodList) {
        this.goodList = goodList;
        notifyDataSetChanged();
    }

    @Override
    public GoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_rcy_main, null);
        GoodViewHolder goodViewHolder = new GoodViewHolder(view);
        return goodViewHolder;
    }

    @Override
    public void onBindViewHolder(GoodViewHolder holder, final int position) {
        final Good good = goodList.get(position);
        Glide.with(context).load(good.getImageUrl()).into(holder.iv_pic).onLoadFailed(context.getResources().getDrawable(R.drawable.bg_load));
        holder.tv_title.setText(good.getTitle());
        holder.tv_price_current.setText(good.getPriceCurrent());
        holder.tv_price_old.setText(good.getPriceOld());
        holder.tv_discount.setText(good.getDiscount().substring(1,good.getDiscount().length()-1));
        if (onItemActionListener != null) {
            holder.ll_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemActionListener.onItemClickListener(good, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return goodList.size();
    }

    public interface OnItemActionListener {
        void onItemClickListener(Good good, int pos);
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }


    class GoodViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pic;
        private TextView tv_title, tv_price_current, tv_price_old,tv_discount;
        private LinearLayout ll_good;

        public GoodViewHolder(View itemView) {
            super(itemView);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_discount = (TextView) itemView.findViewById(R.id.tv_discount);
            tv_price_current = (TextView) itemView.findViewById(R.id.tv_price_current);
            tv_price_old = (TextView) itemView.findViewById(R.id.tv_price_old);
            tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            ll_good = (LinearLayout) itemView.findViewById(R.id.ll_good);
        }
    }
}
