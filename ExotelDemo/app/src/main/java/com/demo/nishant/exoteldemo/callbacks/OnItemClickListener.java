package com.demo.nishant.exoteldemo.callbacks;

import android.view.View;

/**
 * Created by Shilpa.
 */

public interface OnItemClickListener<T> {

    void onItemClick(T t, View view, int position);
}
