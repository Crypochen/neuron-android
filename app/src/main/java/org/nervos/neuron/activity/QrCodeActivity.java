package org.nervos.neuron.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import org.nervos.neuron.R;
import org.nervos.neuron.fragment.CaptureFragment;
import org.nervos.neuron.util.QRUtils.CodeUtils;
import org.nervos.neuron.util.QRUtils.QRResultCheck;

public class QrCodeActivity extends NBaseActivity {

    private CaptureFragment captureFragment;
    public static String SHOW_RIGHT = "showRight";


    @Override
    protected int getContentLayout() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.capture_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.capture_container, captureFragment).commit();
        captureFragment.setRight(getIntent().getBooleanExtra(SHOW_RIGHT, true));
    }

    @Override
    protected void initAction() {

    }

    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            bundle.putInt(CodeUtils.STRING_TYPE, QRResultCheck.check(result));
            resultIntent.putExtras(bundle);
            mActivity.setResult(RESULT_OK, resultIntent);
            mActivity.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            mActivity.setResult(RESULT_OK, resultIntent);
            mActivity.finish();
        }
    };

}
