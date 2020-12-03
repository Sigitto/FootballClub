package com.example.sigit11rpl01pas;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class AdapterMatch extends RecyclerView.Adapter<AdapterMatch.MyViewHolder> {
    private ArrayList<ModelMatch> dataList;
    private Callback callback;
    View viewku;
    int posku;

    interface Callback{
        void onClick(int position);

        void test();
    }

    public AdapterMatch(ArrayList<ModelMatch> dataList, Callback callback){
        this.dataList = dataList;
        this.callback = callback;
        Log.d("makanan", "MahasiswaAdapter: "+dataList.size()+"");
    }
    @NonNull
    @Override
    public AdapterMatch.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adaptermatch, parent, false);
        return new AdapterMatch.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMatch.MyViewHolder holder, int position) {
        holder.txtLeague.setText(dataList.get(position).getStrLeague());
        holder.txtHome.setText(dataList.get(position).getStrHomeTeam());
        holder.txtAway.setText(dataList.get(position).getStrAwayTeam());
//        Log.d("makananku", "onBindViewHolder: "+dataList.get(position).getStrTeamBadge());
//        Glide.with(holder.itemView)
//                .load(dataList.get(position).getStrTeamBadge())
//                .override(Target.SIZE_ORIGINAL)
//                .apply(new RequestOptions().override(600,400))
//                .placeholder(R.mipmap.ic_launcher)
//                .into(holder.ivprofile);
    }



    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLeague, txtHome, txtAway;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewku=itemView;
            card = (CardView) itemView.findViewById(R.id.cardku);
            txtLeague = (TextView) itemView.findViewById(R.id.strLeague);
            txtHome = (TextView) itemView.findViewById(R.id.home);
            txtAway = (TextView) itemView.findViewById(R.id.away);
        }
    }
}
