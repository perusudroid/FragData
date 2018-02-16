package com.perusudroid.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by perusu on 16/2/18.
 */

public class SecondFragment extends Fragment {

    private TextView tvFirst;
    private Button btnFirst, btnSecond;
    private EditText etTvFirst;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setFragInstance(getTag());
        }
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        setAssets();
    }

    private void bindViews(View view) {
        tvFirst = view.findViewById(R.id.tvTxt);
        etTvFirst = view.findViewById(R.id.etText);
        btnFirst = view.findViewById(R.id.btnFirst);
        btnSecond = view.findViewById(R.id.btnSecond);
    }

    private void setAssets() {
        tvFirst.setText(R.string.secong_frag);
        btnFirst.setText(R.string.send_to_first_frag);
        btnSecond.setText(R.string.send_to_activity);
        btnFirst.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (etTvFirst.getText().toString().trim().length() > 0) {
                            ((MainActivity) getActivity()).firstFragData(etTvFirst.getText().toString().trim(), true, 0);
                            etTvFirst.setText("");
                        }
                    }
                }
        );

        btnSecond.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (etTvFirst.getText().toString().trim().length() > 0) {
                            ((MainActivity) getActivity()).firstFragData(etTvFirst.getText().toString().trim(), false, -1);
                            etTvFirst.setText("");
                        }
                    }
                }
        );
    }



    public void setText(String txt) {
        tvFirst.setText(txt);
    }
}
