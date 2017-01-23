package com.umbreller.app.domain;

public class Weather {

  private String city;
  private double rainVolume;
  private String rainTime;

  public final String getCity() {
    return city;
  }

  public final void setCity(String city) {
    this.city = city;
  }

  public final double getRainVolume() {
    return rainVolume;
  }

  public final void setRainVolume(double rainVolume) {
    this.rainVolume = rainVolume;
  }

  public final String getRainTime() {
    return rainTime;
  }

  public final void setRainTime(String rainTime) {
    this.rainTime = rainTime;
  }

  @Override
  public String toString() {
    return "Weather{" +
        "city=" + city +
        ", rainVolume=" + rainVolume +
        ", rainTime=" + rainTime +
        '}';
  }
}
