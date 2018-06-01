package org.nervos.neuron.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.TextView;

import org.nervos.neuron.R;

public class SimpleDialog extends Dialog {

    public static final int PASSWORD = 0;
    public static final int TEXT = 1;

    private TextView okBtn;
    private TextView cancelBtn;
    private TextView titleText;
    private AppCompatEditText messageEdit;
    private String titleStr;
    private String hintStr;
    private int type;

    private OnOkClickListener onOkClickListener;
    private OnCancelClickListener onCancelClickListener;

    public void setOnOkClickListener(OnOkClickListener onOkClickListener) {
        this.onOkClickListener = onOkClickListener;
    }

    public void setOnOkClickListener(String ok, OnOkClickListener onOkClickListener) {
        okBtn.setText(ok);
        setOnOkClickListener(onOkClickListener);
    }

    public void setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
    }

    public void setOnCancelClickListener(String cancel, OnOkClickListener onOkClickListener) {
        cancelBtn.setText(cancel);
        setOnOkClickListener(onOkClickListener);
    }

    public SimpleDialog(Context context) {
        super(context, R.style.DefaultDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_simple);

        initView();
        initListener();

    }

    private void initListener() {
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOkClickListener != null) {
                    onOkClickListener.onOkClick();
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelClickListener != null) {
                    onCancelClickListener.onCancelClick();
                }
            }
        });
    }


    private void initView() {
        okBtn = findViewById(R.id.dialog_ok);
        cancelBtn = findViewById(R.id.dialog_cancel);
        titleText = findViewById(R.id.dialog_title);
        messageEdit = findViewById(R.id.dialog_edit_message);
        if (type == PASSWORD) {
            messageEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        if (!TextUtils.isEmpty(titleStr)) {
            titleText.setText(titleStr);
        }
        if (!TextUtils.isEmpty(hintStr)) {
            messageEdit.setHint(hintStr);
        }
    }

    public void setTitle(String title) {
        titleStr = title;
    }

    public void setMessageHint(String hint) {
        hintStr = hint;
    }

    public String getMessage() {
        return messageEdit.getText().toString().trim();
    }

    public void setEditInputType(int type) {
        this.type = type;
    }

    public interface OnCancelClickListener {
        void onCancelClick();
    }

    public interface OnOkClickListener {
        void onOkClick();
    }

}