package com.example.api.services.dataResponse;

import com.example.api.services.models.InfoApi;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class InfoResponse {
    @SerializedName("data") public List<InfoApi> data;
}
