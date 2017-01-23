package com.umbreller.app.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherEntity {

  @SerializedName("city")
  private CityEntity city;
  @SerializedName("list")
  private List<WeatherDetailEntity> details;

  public final CityEntity getCity() {
    return city;
  }

  public final void setCity(CityEntity city) {
    this.city = city;
  }

  public final List<WeatherDetailEntity> getDetails() {
    return details;
  }

  public final void setDetails(List<WeatherDetailEntity> details) {
    this.details = details;
  }
}
