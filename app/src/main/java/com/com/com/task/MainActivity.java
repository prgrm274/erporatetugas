package com.com.com.task;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String url = "https://erporate.com/bootcamp/jsonBootcamp.php";

    private static final String nama = "nama_pariwisata";
    private static final String alamat = "alamat_pariwisata";
    private static final String detail = "detail_pariwisata";
    private static final String gambar = "gambar_pariwisata";
    private static final String resultKey = "result";
    private static final String dataArray = "data";

//    private ProgressDialog pDialog;
    private String result;
    private PariwisataAdapter adapter;

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.gambar);

        new FetchPariwisata().execute();
    }

    private class FetchPariwisata extends AsyncTask<String, String, String> {
        JSONObject response;

        @Override
        protected String doInBackground(String... strings) {
            HTTPJsonParser httpJsonParser = new HTTPJsonParser();
            response = httpJsonParser.makeHttpRequest(
                    url,
                    "POST",
                    null
            );
            try {
                result = response.getString(resultKey);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pDialog = new ProgressDialog(MainActivity2.this);
//            pDialog.setMessage("Loading");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(false);
//            pDialog.show();
            Toast.makeText(MainActivity.this, "loading", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
//            pDialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListView listView = findViewById(R.id.listView);
                    if (result.equals("true")) {
                        try {
                            JSONArray array = response.getJSONArray(dataArray);
                            List<Model> models = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                Model model = new Model();
                                JSONObject data = array.getJSONObject(i);
                                model.setNamaP(data.getString(nama));
                                model.setAlamatP(data.getString(alamat));
                                model.setDetailP(data.getString(detail));

//                                URL url = new URL(data.getString(gambar));
//                                String urlGambar = "https://"+ url.getHost() + url.getFile();
//                                String urlGambar = url.getHost() + url.getFile();
//                                Picasso.get().load(data.getString(gambar)).into(imageView);
//                                Picasso.get().load(urlGambar).into(imageView);

                                model.setGambarP(data.getString(gambar));

                                models.add(model);
                            }
                            adapter = new PariwisataAdapter(models, getApplicationContext());
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, " = error while loading", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
