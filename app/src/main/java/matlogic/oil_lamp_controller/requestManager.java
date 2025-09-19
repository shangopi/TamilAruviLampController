package matlogic.oil_lamp_controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ConcurrentModificationException;

/**
 * Created by Haran on 29/03/2018.
 */

public class requestManager implements Response.Listener<String> ,Response.ErrorListener{

    private RequestQueue queue;
    private String URL;
    private String response;
    private String error;

    public requestManager(Context context , String targetUrl) {
        queue = Volley.newRequestQueue(context);
        URL = targetUrl;
    }

    private  String getUrl(){
        return URL;
    }

    public String getsResponse(){
        return response;
    }

    public String getsError(){
        return error;
    }


    public void initializeGet(String urlAppend, Response.Listener<String> listener,
                              Response.ErrorListener errorListener){
        String url = getUrl() + urlAppend;
        Log.d("url",url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                listener,errorListener);
        queue.add(stringRequest);
    }


    @Override
    public void onResponse(String response) {
        this.response = response;
        Log.d("response",response);
        getsResponse();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        this.error = "Something went wrong" + error.toString();
        Log.d("Error",this.error);
        getsError();
    }

}
