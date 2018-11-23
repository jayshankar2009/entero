package com.app.entero.direct.ui.activity.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.app.entero.direct.R;
import com.app.entero.direct.model.LoginModel;
import com.app.entero.direct.network.ApiCallService;
import com.app.entero.direct.network.ApiConstants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    public Retrofit retrofit;
    public ApiCallService apiCallService;
    public ProgressDialog progressDialog;
    public CompositeDisposable mCompositeDisposable;
    public LinkedHashMap<String, String> hashMap;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    public void initObjects() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        mCompositeDisposable = new CompositeDisposable();
        hashMap = new LinkedHashMap<>();
    }

    public void isShowProgress(boolean b) {
        if (b) {
            //progressDialog.setMessage(getResources().getString(R.string.loading));
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    public ApiCallService getApiCallService() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL).client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiCallService = retrofit.create(ApiCallService.class);
        return apiCallService;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void showAlerDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(
                BaseActivity.this).create();
        alertDialog.setTitle(getResources().getString(R.string.app_name));
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    public void showAlerDialogF(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(
                BaseActivity.this).create();
        alertDialog.setTitle(getResources().getString(R.string.app_name));
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    public void showAlerDialogWithFinish(String message, final Activity activity) {
        AlertDialog alertDialog = new AlertDialog.Builder(
                BaseActivity.this).create();
        alertDialog.setTitle(getResources().getString(R.string.app_name));
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });
        alertDialog.show();
    }
    public static void showAlertToast(String s,Context context){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, s, duration);
        toast.show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isValidForMultipleEmail(String target) {
        String[] test = target.split(",");
        for (int i = 0; i < test.length; i++) {
            if (!validEmail(test[i])) {
                System.out.println("This is not a vaild email");
                return false;
            }
        }
        return true;
    }

    public final static boolean validEmail(String str_newEmail) {

        return str_newEmail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View v = getCurrentFocus();
        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];
            if (event.getAction() == MotionEvent.ACTION_UP
                    && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w
                    .getBottom())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
                        .getWindowToken(), 0);
            }
        }
        boolean ret = super.dispatchTouchEvent(event);
        return ret;
    }
    public void callApi(String url, LinkedHashMap<String, String> linkedHashMap) {
        isShowProgress(true);
        mCompositeDisposable.add(getApiCallService().login("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOjEsInJvbGUiOiJzdG9ja2lzdCIsImlhdCI6MTU0Mjg4MzI3MiwiZXhwIjoxNTQyOTE5MjcyfQ.moPbCi930NaIGLSpLAwvdZZG0EGrBeJCbsrUvxKpS3s",url, linkedHashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleError(Throwable throwable) {
        Log.e(TAG, " error: " + throwable.getMessage());
        isShowProgress(false);
    }

    private void handleResponse(LoginModel loginModel) {
        Log.e(TAG, " res: " + loginModel.getStatus());
        isShowProgress(false);
        showAlerDialogF(loginModel.getMsg());
        if (loginModel.getStatus().equals("true")) {

        } else {

        }
    }
}
