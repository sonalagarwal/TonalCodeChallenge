package com.tonal.test.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.tonal.test.R;
import com.tonal.test.data.remote.APIService;
import com.tonal.test.data.repository.WeatherRepository;
import com.tonal.test.databinding.WeatherBinding;
import com.tonal.test.view.adapter.WeatherAdapter;
import com.tonal.test.view.viewmodel.WeatherListViewModel;

/**
 * Created by sonal on 4/13/18.
 */

public class WeatherFragment extends Fragment {

    private WeatherBinding binding;
    private WeatherRepository repository;
    private WeatherAdapter adapter;
    private WeatherListViewModel viewModel;
    private EditText weatherSearch;
    private Button searchButton;
    private String location;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_layout, container, false);
        weatherSearch = binding.editText.findViewById(R.id.editText);
        searchButton = binding.searchButton.findViewById(R.id.search_button);

        setViewModel();
        setAdapter();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = weatherSearch.getText().toString();
                if (location != null || !location.equals("")) {

                    //close keyboard
                    closeKeyboard(getActivity(), weatherSearch.getWindowToken());

                    //start searching for result for given zipcode
                    viewModel.getLatestWeatherData(location);
                }
            }
        });

        return binding.getRoot();
    }

    public void closeKeyboard(Context c, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

    private void setViewModel() {
        repository = new WeatherRepository(APIService.Creator.create());

        viewModel = new WeatherListViewModel(repository, getActivity().getApplicationContext());
        binding.setViewModel(viewModel);
    }

    private void setAdapter() {
        adapter = new WeatherAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
    }
}
