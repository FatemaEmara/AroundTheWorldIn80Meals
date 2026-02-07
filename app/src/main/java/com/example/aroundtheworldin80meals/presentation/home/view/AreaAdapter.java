package com.example.aroundtheworldin80meals.presentation.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aroundtheworldin80meals.R;
import com.example.aroundtheworldin80meals.data.meal.model.Area;
import com.example.aroundtheworldin80meals.utils.FlagUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {

    private List<Area> areas = new ArrayList<>();
    private OnAreaClickListener listener;

    public AreaAdapter(OnAreaClickListener listener) {
        this.listener = listener;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.area_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Area area = areas.get(position);
        holder.bind(area);
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView areaName;
        ImageView flag;

        ViewHolder(View itemView) {
            super(itemView);
            areaName = itemView.findViewById(R.id.tvAreaName);
            flag = itemView.findViewById(R.id.ivFlag);
        }

        void bind(Area area) {


            String flagUrl = FlagUtils.getFlagUrl(area.getName());
            if (flagUrl != null && !flagUrl.isEmpty() &&
                    !flagUrl.equals("https://www.themealdb.com/images/icons/flags/big/128/unknown.png")) {
                Glide.with(itemView)
                        .load(flagUrl)
                        .into(flag);
                areaName.setText(area.getName());
            }


            itemView.setOnClickListener(v ->
                    listener.onAreaClick(area.getName() != null ? area.getName() : "")
            );

        }
    }
}

