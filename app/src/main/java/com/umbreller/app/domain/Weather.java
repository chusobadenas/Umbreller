package com.umbreller.app.domain;

public class Weather {

  private double rainVolume;
  private String rainTime;

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
        "rainVolume=" + rainVolume +
        ", rainTime=" + rainTime +
        '}';
  }
}
