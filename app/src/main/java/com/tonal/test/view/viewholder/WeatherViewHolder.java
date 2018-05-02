package com.tonal.test.view.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.tonal.test.databinding.ItemBinding;
import com.tonal.test.view.viewmodel.WeatherItemViewModel;

/**
 * Created by sonal on 4/13/18.
 */

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    private ItemBinding binding;


    public WeatherViewHolder(ItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setItemViewModel(@NonNull WeatherItemViewModel itemViewModel) {
        binding.setItemViewModel(itemViewModel);
        binding.executePendingBindings();
    }
}
