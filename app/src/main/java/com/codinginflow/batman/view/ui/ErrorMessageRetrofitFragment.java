package com.codinginflow.batman.view.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codinginflow.batman.R;
import com.codinginflow.batman.utils.HandelErrorTools;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.codinginflow.batman.utils.AppConstants.ERROR_BUNDLE;

public class ErrorMessageRetrofitFragment extends Fragment {


    @BindView(R.id.txt_state_connect)
    TextView txtStateConnect;

    @BindView(R.id.btn_retry)
    TextView btnRetry;

    public ErrorMessageRetrofitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String handleError = Objects.requireNonNull(getArguments()).getString(ERROR_BUNDLE, "");
        txtStateConnect.setText(handleError);
        btnRetry.setOnClickListener(view1 -> initNavFragment());
    }

    private void initNavFragment() {
        final NavController navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.my_nav_fragment);
        navController.navigateUp();
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            View view = inflater.inflate(R.layout.fragment_error_message_retrofit, container, false);
            initView(view);
            return view;
        } catch (Exception e) {
            HandelErrorTools.getInstance().setHandelError(e);
        }
        return null;
    }

    private void initView(View view) {
        ButterKnife.bind(this, view);
    }

}
