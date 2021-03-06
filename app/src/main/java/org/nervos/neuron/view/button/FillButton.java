package org.nervos.neuron.view.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import org.nervos.neuron.R;

/**
 * Created by BaojunCZ on 2018/8/30.
 */
public class FillButton extends LinearLayout {

    private AppCompatButton button;
    private OnClickListener listener = null;

    public FillButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CommonButton);
        LayoutInflater.from(context).inflate(R.layout.button_fill, this);
        button = findViewById(R.id.btn);
        String text = ta.getString(R.styleable.CommonButton_text);
        button.setText(text);
        boolean status = ta.getBoolean(R.styleable.CommonButton_status, true);
        setClickAble(status);
        button.setOnClickListener(view -> {
            if (listener != null)
                listener.onClick(this);
        });
    }

    public void setClickAble(boolean able) {
        if (able) {
            button.setBackgroundResource(R.drawable.button_blue_shape);
            button.setEnabled(true);
        } else {
            button.setBackgroundResource(R.drawable.button_gray_shape);
            button.setEnabled(false);
        }
    }

    public void setText(String text) {
        button.setText(text);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        listener = l;
    }
}
