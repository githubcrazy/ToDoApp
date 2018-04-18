package model;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicationManager {
     User user = new User();
    public void getDataFromApi(User user) {
        ApiInterface apiInterface = ApiClient.sendRequest(user).create(ApiInterface.class);
        Call<User> call = apiInterface.createAccount(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
