package matlogic.oil_lamp_controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private requestManager regManager;
    private String prefix = "http://";
    private String subUrl = "http://192.168.8.105";
    private String targetUrl = subUrl +  "/Lamp?no=";

    private int count = 0;

    private Button testButton_1;
    private Button testButton_2;
    private Button testButton_3;
    private Button testButton_4;
    private Button testButton_5;
    private Button testButton_6;
    private Button testButton_7;
    private Button testButton_8;
    private Button testButton_9;
    private Button testButton_10;

    //private SimpleDraweeView illumination_1;

    private boolean btn1_state = true;
    private boolean btn2_state = true;
    private boolean btn3_state = true;
    private boolean btn4_state = true;
    private boolean btn5_state = true;
    private boolean btn6_state = true;
    private boolean btn7_state = true;
    private boolean btn8_state = true;
    private boolean btn9_state = true;

    //private SimpleDraweeView simpleDraweeView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        hideSystemUI();
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null){
            String ip_address = intent.getStringExtra("ip_address");
            targetUrl = prefix + ip_address + "/Lamp?no=";
            count = 0;
        }
        //loadScannerImage();
        preIlluminate();
        addListenerOnButton();
    }



    public void addListenerOnButton() {

        regManager = new requestManager(getApplicationContext(),targetUrl);


        testButton_1 = (Button) findViewById(R.id.button1);
        testButton_2 = (Button) findViewById(R.id.button2);
        testButton_3 = (Button) findViewById(R.id.button3);
        testButton_4 = (Button) findViewById(R.id.button4);
        testButton_5 = (Button) findViewById(R.id.button5);
        testButton_6 = (Button) findViewById(R.id.button6);
        testButton_7 = (Button) findViewById(R.id.button7);
        testButton_8 = (Button) findViewById(R.id.button8);
        testButton_9 = (Button) findViewById(R.id.button9);
       // testButton_10 = (Button) findViewById(R.id.button10);




        addSpecListerner(testButton_1,"1");
        addSpecListerner(testButton_2,"2");
        addSpecListerner(testButton_3,"3");
        addSpecListerner(testButton_4,"4");
        addSpecListerner(testButton_5,"5");
        addSpecListerner(testButton_6,"6");
        addSpecListerner(testButton_7,"7");
        addSpecListerner(testButton_8,"8");
        addSpecListerner(testButton_9,"9");
      //  addSpecListerner(testButton_10,"0");

        //addDoublClickListener(testButton_1);
        //addDoublClickListener(testButton_2);
        //addDoublClickListener(testButton_3);
        //addDoublClickListener(testButton_4);
        //addDoublClickListener(testButton_5);



        //addSpecListerner(simpleDraweeView,"10");
    }

    public void addSpecListerner(View btn, final String str){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!str.equals("0")){
                    if (getBtnState(str)){
                        setBtnState(str,false);
                        regManager.initializeGet(String.valueOf(count),regManager,regManager);

                        //Toast.makeText(getApplicationContext(),"send", Toast.LENGTH_LONG).show();
                    } else {
                        setBtnState(str,true);
                        //Toast.makeText(getApplicationContext(),"reset", Toast.LENGTH_LONG).show();
                    }

                } else {
                    regManager.initializeGet(str,regManager,regManager);
                }

            }
        });
    }
    public void addDoublClickListener(View btn){
        btn.setOnClickListener(new DoubleClickListener(200) {

            @Override
            public void onDoubleClick() {
                //Toast.makeText(getApplicationContext(),"This is a double click listener", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean getBtnState(String id){
        if (id.equals("1")){
            return btn1_state;
        } else if (id.equals("2")) {
            return btn2_state;
        } else if (id.equals("3")) {
            return btn3_state;
        } else if (id.equals("4")) {
            return btn4_state;
        } else if (id.equals("5")) {
            return btn5_state;
        } else if (id.equals("6")) {
            return btn6_state;
        } else if (id.equals("7")) {
            return btn7_state;
        } else if (id.equals("8")) {
            return btn8_state;
        } else if (id.equals("9")) {
            return btn9_state;

        }
        return true;
    }

    private void setBtnState(String id, boolean val){
        count = count >= 8 ? 0 : count +1;
        setIlluminatorVisibility(getIlluminatorId(id), val);
        if (id.equals("1")){
            btn1_state = val;
        } else if (id.equals("2")) {
            btn2_state = val;
        } else if (id.equals("3")) {
            btn3_state = val;
        } else if (id.equals("4")) {
            btn4_state = val;
        } else if (id.equals("5")) {
            btn5_state = val;
        } else if (id.equals("6")) {
            btn6_state = val;
        } else if (id.equals("7")) {
            btn7_state = val;
        } else if (id.equals("8")) {
            btn8_state = val;
        } else if (id.equals("9")) {
            btn9_state = val;
        }
    }

    private void setIlluminatorVisibility(int id, boolean val){
        if (val){
            findViewById(id).setVisibility(View.INVISIBLE);
        } else {
            findViewById(id).setVisibility(View.VISIBLE);
        }

    }

    private void preIlluminate(){
        ArrayList<Integer> ill_list = new ArrayList<Integer>();
        ill_list.add(R.id.illumination_1);
        ill_list.add(R.id.illumination_2);
        ill_list.add(R.id.illumination_3);
        ill_list.add(R.id.illumination_4);
        ill_list.add(R.id.illumination_5);
        ill_list.add(R.id.illumination_6);
        ill_list.add(R.id.illumination_7);
        ill_list.add(R.id.illumination_8);
        ill_list.add(R.id.illumination_9);

        for (int id: ill_list){
            loadIllumination(id);
        }
    }

    private void loadIllumination(int ill_id){
        //Toast.makeText(getApplicationContext(),"I am working", Toast.LENGTH_LONG).show();
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.flame1);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();


        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(ill_id);
        simpleDraweeView.setImageURI(uri);
        simpleDraweeView.setController(controller);
    }

//    private View.OnTouchListener lightsOn = new View.OnTouchListener() {
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//
//
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_UP:
//
//                    break;
//            }
//
//            return true;
//        }
//    };

    private int getIlluminatorId(String id){
        if (id.equals("1")) {
            return R.id.illumination_1;
        } else if (id.equals("2")) {
            return R.id.illumination_2;
        } else if (id.equals("3")) {
            return R.id.illumination_3;
        } else if (id.equals("4")) {
            return R.id.illumination_4;
        } else if (id.equals("5")) {
            return R.id.illumination_5;
        } else if (id.equals("6")) {
            return R.id.illumination_6;
        } else if (id.equals("7")) {
            return R.id.illumination_7;
        } else if (id.equals("8")) {
            return R.id.illumination_8;
        } else if (id.equals("9")) {
            return R.id.illumination_9;

        }
        return 10;
    }

    private void hideSystemUI() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle(s);
  //      actionBar.hide();



    }
}
