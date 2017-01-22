package com.umbreller.app.data.entity;

import com.google.gson.annotations.SerializedName;

public class WeatherDetailEntity {

  @SerializedName("dt_txt")
  private String dataForecast;
  @SerializedName("rain")
  private RainEntity rain;

  public final String getDataForecast() {
    return dataForecast;
  }

  public final void setDataForecast(String dataForecast) {
    this.dataForecast = dataForecast;
  }

  public final RainEntity getRain() {
    return rain;
  }

  public final void setRain(RainEntity rain) {
    this.rain = rain;
  }
}
