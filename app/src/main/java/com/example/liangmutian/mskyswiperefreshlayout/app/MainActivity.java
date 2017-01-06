
package com.example.liangmutian.mskyswiperefreshlayout.app;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.liangmutian.mskyswiperefreshlayout.R;
import com.example.liangmutian.mskyswiperefreshlayout.util.Utils;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Tinker.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "i am on onCreate classloader:" + MainActivity.class.getClassLoader().toString());
        //test resource change
//        Log.e(TAG, "i am on onCreate string:" + getResources().getString(R.string.test_resource));
//        Log.e(TAG, "i am on patch onCreate");

//       Button loadPatchButton = (Button) findViewById(R.id.loadPatch);
//
//        loadPatchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), "/sdcard/zhibo8/patch_signed_7zip.apk");
//            }
//        });
//
//        Button loadLibraryButton = (Button) findViewById(R.id.loadLibrary);

//        loadLibraryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //for lib/armeabi, just use TinkerInstaller.loadLibrary
//                TinkerInstaller.loadArmLibrary(getApplicationContext(), "stlport_shared");
////                TinkerInstaller.loadLibraryFromTinker(getApplicationContext(), "assets/x86", "stlport_shared");
//            }
//        });
//
//        Button cleanPatchButton = (Button) findViewById(R.id.cleanPatch);
//
//        cleanPatchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Tinker.with(getApplicationContext()).cleanPatch();
//            }
//        });

//        Button killSelfButton = (Button) findViewById(R.id.killSelf);
//
//        killSelfButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
//                android.os.Process.killProcess(android.os.Process.myPid());
//            }
//        });

//        Button buildInfoButton = (Button) findViewById(R.id.showInfo);
//
//        buildInfoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showInfo(MainActivity.this);
//            }
//        });
    }

    public boolean showInfo(Context context) {
        // add more Build Info
        final StringBuilder sb = new StringBuilder();
        Tinker tinker = Tinker.with(getApplicationContext());
        if (tinker.isTinkerLoaded()) {
            sb.append(String.format("[22229999990000patch is loaded] \n"));
            sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
            sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BaseBuildInfo.BASE_TINKER_ID));

            sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
            sb.append(String.format("[TINKER_ID] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName(ShareConstants.TINKER_ID)));
            sb.append(String.format("[packageConfig patchMessage] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName("patchMessage")));
            sb.append(String.format("[TINKER_ID Rom Space] %d k \n", tinker.getTinkerRomSpace()));

        } else {
            sb.append(String.format("[222299999990000patch is not loaded] \n"));
            sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
            sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BaseBuildInfo.BASE_TINKER_ID));

            sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
            sb.append(String.format("[TINKER_ID] %s \n", ShareTinkerInternals.getManifestTinkerID(getApplicationContext())));
        }
        sb.append(String.format("[BaseBuildInfo Message] %s \n", BaseBuildInfo.TEST_MESSAGE));

        final TextView v = new TextView(context);
        v.setText(sb);
        v.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        v.setTextColor(0xFF000000);
        v.setTypeface(Typeface.MONOSPACE);
        final int padding = 16;
        v.setPadding(padding, padding, padding, padding);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setView(v);
        final AlertDialog alert = builder.create();
        alert.show();
        return true;
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "i am on onResume");
//        Log.e(TAG, "i am on patch onResume");

        super.onResume();
        Utils.setBackground(false);

    }

    @Override
    protected void onPause() {

        Log.e(TAG, "i am on onPause");
        super.onPause();
        Utils.setBackground(true);
    }
}
