package model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/photos")
    Call<List<GetImages>> getDataFromApi();
}
