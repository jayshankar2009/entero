package com.app.entero.direct.utils.custom;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.app.entero.direct.R;

@SuppressLint("AppCompatCustomView")

public class CustomTextView_Salesman extends TextView {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    public CustomTextView_Salesman(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);

    }



    public CustomTextView_Salesman(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

        init(attrs);

    }



    public CustomTextView_Salesman(Context context, AttributeSet attrs) {

        super(context, attrs);

        init(attrs);



    }

    public CustomTextView_Salesman(Context context) {

        super(context);

        init(null);

    }


    private void init(AttributeSet attrs) {

        if (attrs != null) {

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextView_Salesman);

            String fontName = a.getString(R.styleable.CustomTextView_Salesman_fonts);



            try {

                if (fontName != null) {
                    Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "font/" + fontName);

                    setTypeface(myTypeface);

                }

            } catch (Exception e) {

                e.printStackTrace();

            }


            a.recycle();

        }

    }


}