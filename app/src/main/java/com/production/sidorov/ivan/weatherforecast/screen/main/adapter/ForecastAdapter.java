package com.production.sidorov.ivan.weatherforecast.screen.main.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.production.sidorov.ivan.weatherforecast.R;
import com.production.sidorov.ivan.weatherforecast.data.model.Weather;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Иван on 11.07.2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter <ForecastAdapter.WeatherHolder> {

    private List <Weather> mWeathers;
    private Context mContext;
    private ListItemClickListener mOnItemClickListener;
    private Resources mResources;


    public interface ListItemClickListener{
        void onListItemClicked(int itemIndex);
    }

    public ForecastAdapter(Context mContext, @NonNull ListItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.mOnItemClickListener = onItemClickListener;
        this.mWeathers = new ArrayList<>();
        this.mResources = mContext.getResources();
    }

    public void setWeatherToAdapter(List<Weather> weathers){
        mWeathers.clear();
        mWeathers.addAll(weathers);
        notifyDataSetChanged();
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.forecast_list_item,parent,false);

        return new WeatherHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {

            holder.cityNameTextView.setText(mWeathers.get(position).getName());
            holder.temperatureTextView.setText(mResources.getString(R.string.format_temperature
                    ,mWeathers.get(position).getMain().getTemp()));
            holder.humidityTextView.setText(mResources.getString(R.string.format_humidity
                    ,mWeathers.get(position).getMain().getHumidity()));
            holder.pressureTextView.setText(mResources.getString(R.string.format_pressure
                    ,mWeathers.get(position).getMain().getPressure()));
            holder.windSpeedTextView.setText(mResources.getString(R.string.format_wind_speed
                    ,mWeathers.get(position).getWind().getSpeed()));

    }

    @Override
    public int getItemCount() {
        if(mWeathers == null) return 0;
        else return mWeathers.size();
    }

    class WeatherHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.city_name)
        TextView cityNameTextView;

        @BindView(R.id.temperature_value)
        TextView temperatureTextView;

        @BindView(R.id.humidity_value)
        TextView humidityTextView;

        @BindView(R.id.pressure_value)
        TextView pressureTextView;

        @BindView(R.id.wind_speed_value)
        TextView windSpeedTextView;

        WeatherHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnItemClickListener.onListItemClicked(clickedPosition);
        }
    }


}
