package com.chutikarn.saksi_application.Interface;

import android.view.View;

public interface ItemclickListner {
    void onClick(View view, int position, boolean longclick);
    void onItemClick(View view, int position);
    void onItemClickListener(View v, int adapterPosition);
}
