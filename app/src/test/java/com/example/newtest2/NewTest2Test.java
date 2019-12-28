package com.example.newtest2;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowToast;

import java.util.Random;

@RunWith(RobolectricTestRunner.class)
public class NewTest2Test {
    private MainActivity activity;
    static StringBuffer sb;
    static int salt;

    @BeforeClass
    public static void report(){
        salt=new Random(System.currentTimeMillis()).nextInt(999);
        sb=new StringBuffer();
        sb.append(String.format("%03d",salt));
    }

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create().get();
    }


     @Test
    public void testButtonToast() throws Exception {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class)
                .create().get();
        Button view = (Button) activity.findViewById(R.id.btn);
        Assert.assertNotNull(view);
        view.performClick();
        org.junit.Assert.assertThat(ShadowToast.getTextOfLatestToast(), CoreMatchers.equalTo("I clicked!"));
        sb.append(",TEST1:OK");
    }

    @Test
    public void testButtonText() throws Exception {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class)
                .create().get();
        Button view = (Button) activity.findViewById(R.id.btn1);
        Assert.assertNotNull(view);
        EditText et= (EditText) activity.findViewById(R.id.et);
        Assert.assertNotNull(et);
        TextView tv= (TextView) activity.findViewById(R.id.tv);
        Assert.assertNotNull(tv);
        String testStr="test"+new Random().nextInt();
        et.setText(testStr);
        view.performClick();
        org.junit.Assert.assertEquals(tv.getText().toString(),testStr);
        sb.append(",TEST2:OK");
    }

    @AfterClass
    public static void printResult(){
        System.err.println("\n\n=============================\nВАШ РЕЗУЛЬТАТ: "+sb.toString().hashCode()+""+salt+"\n=============================\n");

    }

}
