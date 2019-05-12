package com.ngopidevteam.pranadana.metime.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ngopidevteam.pranadana.metime.Model.ModelData;
import com.ngopidevteam.pranadana.metime.R;
import com.ngopidevteam.pranadana.metime.fragment.HistoryWH;

import java.util.List;

public class AdapterDataWH extends RecyclerView.Adapter<AdapterDataWH.HolderData> {

    private List<ModelData> mItems;
    private HistoryWH context;

    public AdapterDataWH(HistoryWH context, List<ModelData> items){
        this.context = context;
        this.mItems = items;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_row, viewGroup, false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holderData, int position) {
        ModelData md = mItems.get(position);
        holderData.textTanggal.setText(md.getTanggal());
        holderData.textMulai.setText(md.getJamMulai());
        holderData.textSelesai.setText(md.getJamSelesai());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView textTanggal, textMulai, textSelesai;


        public HolderData(@NonNull View itemView) {
            super(itemView);

            textTanggal = itemView.findViewById(R.id.iniTanggal);
            textMulai = itemView.findViewById(R.id.iniJamMulai);
            textSelesai = itemView.findViewById(R.id.iniJamSelesai);
        }
    }
}
