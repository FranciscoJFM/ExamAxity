package com.example.axityexamen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.axityexamen.Constantes.Constants;
import com.example.axityexamen.Model.ModelProductos;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnPress;
    TextView tvjsonresultnombre;
    TextView tvjsonresultdepartamento;
    TextView tvjsonresultcodigo;
    TextView tvjsonresultprecio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPress = findViewById(R.id.btnPress);
        tvjsonresultnombre = findViewById(R.id.tvjsonresultname);
        tvjsonresultdepartamento = findViewById(R.id.tvjsonresuldepartament);
        tvjsonresultcodigo = findViewById(R.id.tvjsonresulcode);
        tvjsonresultprecio = findViewById(R.id.tvjsonresultPrice);
        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        Constants.URL_PRODUCTO, null,
                        new Response.Listener() {
                            @Override
                            public void onResponse(Object response) {
                                JSONObject json = (JSONObject) response;
                                ModelProductos modelProductos = new ModelProductos();

                                try {
                                    modelProductos.skuDisplayNameText = json.getString(getResources().getString(R.string.skuDisplayNameText));
                                     modelProductos.departament = json.getString(getResources().getString(R.string.department));
                                    modelProductos.skuId = json.getInt(getResources().getString(R.string.skuId));
                                    modelProductos.basePrecio = json.getDouble(getResources().getString(R.string.basePrice));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                tvjsonresultnombre.setText(String.valueOf(modelProductos.skuDisplayNameText));
                                tvjsonresultdepartamento.setText(String.valueOf(modelProductos.departament));
                                tvjsonresultcodigo.setText(String.valueOf(modelProductos.skuId));
                                tvjsonresultprecio.setText(String.valueOf(modelProductos.basePrecio));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {

                    @Override
                    public Map getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<>();
                        headers.put(Constants.ACEPT,Constants.ACEPT_PARAM);
                        headers.put(Constants.CONNECTION, Constants.CONNECTION_PARAM);
                        headers.put(Constants.CONTENTTYP, Constants.CONTENTTYP_PARAM);
                        return headers;
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put(Constants.JSESSIONID_GR, "ewnljk%2BfgPUYt2Uks5PY-vG9.restapp-298183240-6359372925");
                        map.put(Constants.TS01f4281b, "0130aff232b466ffcc4072d1bbce44ecbbd89f5d479ff3da4fb92ddaa9416088 8e2ca908d1fa72efd98d848e3e8531b6c0d47a99fe");
                        map.put(Constants.akavpau_vp_super, "1544643414~id%3D0b950c261050bdafe78b05c390a1fd75");
                        map.put(Constants.dtCookie, "%7CbWV4aWNvX19ncm9jZXJpZXN8MA");
                        map.put(Constants.TS01c7b722, "0130aff232b466ffcc4072d1bbce44ecbbd89f5d479ff3da4fb92ddaa941608 88e2ca908d1fa72efd98d848e3e8531b6c0d47a99fe");
                        return map;
                    }

                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjReq);
            }
        });


    }
}
