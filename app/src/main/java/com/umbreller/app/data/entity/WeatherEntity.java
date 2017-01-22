package com.umbreller.app.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherEntity {

  @SerializedName("cod")
  private String code;
  @SerializedName("list")
  private List<WeatherDetailEntity> details;

  public final String getCode() {
    return code;
  }

  public final void setCode(String code) {
    this.code = code;
  }

  public final List<WeatherDetailEntity> getDetails() {
    return details;
  }

  public final void setDetails(List<WeatherDetailEntity> details) {
    this.details = details;
  }
}
