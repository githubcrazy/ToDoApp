package model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/photos")
    Call<List<GetImages>> getDataFromApi();

    @FormUrlEncoded
    @POST("/api/users")
    Call<PostData> postData(@Field("name") String name,
                            @Field("job") String job);
}
