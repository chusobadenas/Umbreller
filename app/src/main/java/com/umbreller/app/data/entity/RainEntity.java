package com.umbreller.app.data.entity;

import com.google.gson.annotations.SerializedName;

public class RainEntity {

  @SerializedName("3h")
  private double volume;

  public final double getVolume() {
    return volume;
  }

  public final void setVolume(double volume) {
    this.volume = volume;
  }
}
