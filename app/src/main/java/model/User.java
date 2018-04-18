package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("page")
    private int page;

    @SerializedName("per_page")
    private int per_page;

    @SerializedName("total")
    private int total;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("data")
    private List<data> data;

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<data> getData() {
        return data;
    }

}
