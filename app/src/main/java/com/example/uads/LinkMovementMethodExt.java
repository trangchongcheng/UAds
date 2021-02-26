package com.example.uads;

import android.text.Layout;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class LinkMovementMethodExt extends LinkMovementMethod
{
    private static LinkMovementMethod sInstance;

    public static MovementMethod getInstance()
    {
        if (sInstance == null)
        {
            sInstance = new LinkMovementMethodExt();
        }
        return sInstance;
    }

    int off = 0;
    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event)
    {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN)
        {
            try {
                int x = (int) event.getX();
                int y = (int) event.getY();

                x -= widget.getTotalPaddingLeft();
                y -= widget.getTotalPaddingTop();

                x += widget.getScrollX();
                y += widget.getScrollY();

                Layout layout = widget.getLayout();
                int currentLine = layout.getLineForVertical(y);
                int totalLine = layout.getLineCount();
                off = layout.getOffsetForHorizontal(currentLine, x);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                URLSpan[] urls = buffer.getSpans(off, off+1, URLSpan.class);
                for(URLSpan span : urls)
                {
                    String urlStr = span.getURL();

                    Log.v("URL SPAN", urlStr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
