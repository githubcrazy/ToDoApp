package model;

import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class ApplicationManager {
     List<GetImages> getImagesList = new ArrayList<>();
     public List<GetImages> getDataFromApi() {
        ApiInterface apiInterface = ApiClient.sendRequest().create(ApiInterface.class);
         Call<List<GetImages>> call = apiInterface.getDataFromApi();
         call.enqueue(new Callback<List<GetImages>>() {
             @Override
             public void onResponse(Call<List<GetImages>> call, Response<List<GetImages>> response) {
                 getImagesList = response.body();
             }

             @Override
             public void onFailure(Call<List<GetImages>> call, Throwable t) {
                 t.printStackTrace();
             }
         });

         return null;
     }

    public void showProgressBar() {

    }
}
