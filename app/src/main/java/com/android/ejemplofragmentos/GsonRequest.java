package com.android.ejemplofragmentos;

import com.android.ejemplofragmentos.model.Producto;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * Created by sbogado on 19/10/16.
 */

public class GsonRequest<T> extends Request<T> {

    private static Gson gson = new Gson();
    private Class<T> clazz;
    private Response.Listener<T> listener;

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener reponseListener, Response.ErrorListener listener){
        super(method, url,listener);
        this.clazz = clazz;
        this.listener=reponseListener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try{
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return  Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return  Response.error(new VolleyError(e));
        } catch (JsonSyntaxException ex){
           return Response.error(new VolleyError(ex));
        }

    }


    //Una vez que parseamos la respuesta, el response de arriba lo manda aca.
    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
