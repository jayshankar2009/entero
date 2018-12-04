package com.app.entero.direct.ui.activity.salesman;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.app.entero.direct.R;
import com.app.entero.direct.model.Selfie;
import com.app.entero.direct.ui.activity.main.BaseActivity;
import com.app.entero.direct.ui.adapter.salesman.HorizontalAdapter_Salesman;
import com.app.entero.direct.utils.LocationTrack;
import com.app.entero.direct.utils.getLocation;

public class CustomTaskDeliverDetailStatusActivity_Salesman extends BaseActivity implements View.OnClickListener {

    TextView text_cust_task_delivery_detail_status_name, text_cust_task_delivery_detail_status_invoice_no, text_cust_task_delivery_detail_status_items_count,
            text_cust_task_delivery_detail_status_packets_count, text_cust_task_delivery_detail_status_boxes_count;
    Button btn_cust_task_delivery_detail_status_view_invoice, btn_cust_task_delivery_detail_status_delivery;
    LinearLayout ly_cust_task_delivery_detail_status_take_selfie, ly_cust_task_delivery_detail_status_take_signature;
    RadioGroup radio_group_delivered_to;
    RecyclerView horizontal_recycler_view_take_selfie, horizontal_recycler_view_take_signature;
    HorizontalAdapter_Salesman horizontalAdapter;
    private List<Selfie> data;
    Bitmap bitmap;
    Toolbar mToolbar;
    TextView txtToolbarHeader;
    Button clear, save;
    LinearLayout canvasLL;
    signature mSignature;
    private View view;
    LocationTrack locationTrack;
    LinearLayoutManager horizontalLayoutManager;
    List<Selfie> bitmapSignaturedata = new ArrayList<Selfie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesman_activity_custom_task_deliver_detail_status);
        initLayout();
        new getLocation(this).checkLocation(this);
        setToolbar();
        onSetText();
        onClickEvent();
        horizontal_recycler_view_take_selfie.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view_take_signature = (RecyclerView) findViewById(R.id.horizontal_recycler_view_take_signature);
        LinearLayoutManager horizontalLayoutManagerSignature = new LinearLayoutManager(CustomTaskDeliverDetailStatusActivity_Salesman.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view_take_signature.setLayoutManager(horizontalLayoutManagerSignature);
    }

    private void onSetText() {
        txtToolbarHeader.setText("Delivery Details");
    }

    private void setToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_nav_left_arrow);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void onClickEvent() {
        ly_cust_task_delivery_detail_status_take_selfie.setOnClickListener(this);
        ly_cust_task_delivery_detail_status_take_signature.setOnClickListener(this);
        btn_cust_task_delivery_detail_status_delivery.setOnClickListener(this);

    }

    private void initLayout() {
        locationTrack= new LocationTrack(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtToolbarHeader = (TextView) findViewById(R.id.txtHeader);
        text_cust_task_delivery_detail_status_name = (TextView) findViewById(R.id.text_cust_task_delivery_detail_status_name);
        text_cust_task_delivery_detail_status_invoice_no = (TextView) findViewById(R.id.text_cust_task_delivery_detail_status_invoice_no);
        text_cust_task_delivery_detail_status_items_count = (TextView) findViewById(R.id.text_cust_task_delivery_detail_status_items_count);
        text_cust_task_delivery_detail_status_packets_count = (TextView) findViewById(R.id.text_cust_task_delivery_detail_status_packets_count);
        text_cust_task_delivery_detail_status_boxes_count = (TextView) findViewById(R.id.text_cust_task_delivery_detail_status_boxes_count);
        radio_group_delivered_to = (RadioGroup) findViewById(R.id.radio_group_delivered_to);
        btn_cust_task_delivery_detail_status_view_invoice = (Button) findViewById(R.id.btn_cust_task_delivery_detail_status_view_invoice);
        btn_cust_task_delivery_detail_status_delivery = (Button) findViewById(R.id.btn_cust_task_delivery_detail_status_delivery);
        ly_cust_task_delivery_detail_status_take_selfie = (LinearLayout) findViewById(R.id.ly_cust_task_delivery_detail_status_take_selfie);
        ly_cust_task_delivery_detail_status_take_signature = (LinearLayout) findViewById(R.id.ly_cust_task_delivery_detail_status_take_signature);
        horizontal_recycler_view_take_selfie = (RecyclerView) findViewById(R.id.horizontal_recycler_view_take_selfie);
        horizontalLayoutManager = new LinearLayoutManager(CustomTaskDeliverDetailStatusActivity_Salesman.this, LinearLayoutManager.HORIZONTAL, false);

    }

    private List<Selfie> addSelfieData() {
        List<Selfie> data = new ArrayList<Selfie>();
        /*data.add(new Selfie( R.drawable.img1, "Image 1"));
        data.add(new Selfie( R.drawable.img2, "Image 2"));
        data.add(new Selfie( R.drawable.img3, "Image 3"));*/
        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cust_task_delivery_detail_status_view_invoice:

                break;
            case R.id.ly_cust_task_delivery_detail_status_take_selfie:
                takeSelfie();
                data = addSelfieData();
                horizontalAdapter = new HorizontalAdapter_Salesman(getApplication(), data);
                horizontal_recycler_view_take_selfie.setAdapter(horizontalAdapter);
                break;
            case R.id.ly_cust_task_delivery_detail_status_take_signature:
                takeSignatureDialog();

                break;
            case R.id.btn_cust_task_delivery_detail_status_delivery:
                if(locationTrack.get_location()){

                }else {

                }
        }
    }

    private void takeSignatureDialog() {

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.salesman_dialog_take_signature);
        dialog.setTitle("Title...");

        // set the custom dialog components - text and image

        canvasLL = (LinearLayout) dialog.findViewById(R.id.canvasLL);
        mSignature = new signature(this, null);
        mSignature.setBackgroundColor(Color.WHITE);
        // Dynamically generating Layout through java code
        canvasLL.addView(mSignature, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        view = canvasLL;

        clear = (Button) dialog.findViewById(R.id.clear);
        save = (Button) dialog.findViewById(R.id.save);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignature.clear();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setDrawingCacheEnabled(true);
                mSignature.save(view);
                dialog.dismiss();
            }
        });
        /*// if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
    }

    private void takeSelfie() {
    }

    public class signature extends View {

        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        private Path path = new Path();
        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();
        private Bitmap bitmap;

        public signature(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }

        public void save(View v) {
            /*Log.v("log_tag", "Width: " + v.getWidth());
            Log.v("log_tag", "Height: " + v.getHeight());*/
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(canvasLL.getWidth(), canvasLL.getHeight(), Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(bitmap);
            v.draw(canvas);
            horizontal_recycler_view_take_signature.setVisibility(View.VISIBLE);
            bitmapSignaturedata.add(new Selfie(bitmap, "Image 1"));
            horizontalAdapter = new HorizontalAdapter_Salesman(getApplication(), bitmapSignaturedata);
            horizontal_recycler_view_take_signature.setAdapter(horizontalAdapter);

        }

        public void clear() {
            path.reset();
            invalidate();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:

                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;

                default:
                    debug("Ignored touch event: " + event.toString());
                    return false;
            }

            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

            lastTouchX = eventX;
            lastTouchY = eventY;

            return true;
        }

        private void debug(String string) {

            Log.v("log_tag", string);

        }

        private void expandDirtyRect(float historicalX, float historicalY) {
            if (historicalX < dirtyRect.left) {
                dirtyRect.left = historicalX;
            } else if (historicalX > dirtyRect.right) {
                dirtyRect.right = historicalX;
            }

            if (historicalY < dirtyRect.top) {
                dirtyRect.top = historicalY;
            } else if (historicalY > dirtyRect.bottom) {
                dirtyRect.bottom = historicalY;
            }
        }

        private void resetDirtyRect(float eventX, float eventY) {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }

    }
}
