package model;

import com.google.gson.annotations.SerializedName;

public class PostData {
    @SerializedName("name")
    private String name;

    @SerializedName("job")
    private String job;

    @SerializedName("id")
    private String id;

    @SerializedName("createdAt")
    private String createdAt;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
