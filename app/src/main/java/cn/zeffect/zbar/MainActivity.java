package cn.zeffect.zbar;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;

import cn.zeffect.qr_zbar.CaptureActivity;

public class MainActivity extends AppCompatActivity {
    Button zbar, zxing;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        zbar = (Button) findViewById(R.id.zbar_button);
        zxing = (Button) findViewById(R.id.zxing_button);
        result = (TextView) findViewById(R.id.result_text);

        zbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(MainActivity.this, new String[]{Manifest.permission.CAMERA}, new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 99);
                    }

                    @Override
                    public void onDenied(String permission) {

                    }
                });

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 99) {
            if (resultCode == RESULT_OK) {
                String tempResult = data.getStringExtra(CaptureActivity.RESULT_DATA);
                if (!TextUtils.isEmpty(tempResult)) {
                    result.setText(result.getText() + "\nzbar：" + tempResult);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
