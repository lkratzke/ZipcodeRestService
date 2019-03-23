/*
 * This is free and unencumbered software released into the public domain.
 * 
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org>
 */
package com.geezertechnet.zipcode.rest;

/**
 *
 * @author Loren Kratzke
 */
public class ZipBean {
  
  private long id;
  private long recordNumber;
  private String zipCode;
  private String zipCodeType;
  private String city;
  private String state;
  private String locationType;
  private String lat;
  private String lon;
  private String xAxis;
  private String yAxis;
  private String zAxis;
  private String worldRegion;
  private String country;
  private String locationText;
  private String location;
  private String decom;
  private long taxReturnsFiled;
  private long estimatedPopulation;
  private long totalWages;
  private String notes;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getRecordNumber() {
    return recordNumber;
  }

  public void setRecordNumber(long recordNumber) {
    this.recordNumber = recordNumber;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getZipCodeType() {
    return zipCodeType;
  }

  public void setZipCodeType(String zipCodeType) {
    this.zipCodeType = zipCodeType;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getLocationType() {
    return locationType;
  }

  public void setLocationType(String locationType) {
    this.locationType = locationType;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLon() {
    return lon;
  }

  public void setLon(String lon) {
    this.lon = lon;
  }

  public String getxAxis() {
    return xAxis;
  }

  public void setxAxis(String xAxis) {
    this.xAxis = xAxis;
  }

  public String getyAxis() {
    return yAxis;
  }

  public void setyAxis(String yAxis) {
    this.yAxis = yAxis;
  }

  public String getzAxis() {
    return zAxis;
  }

  public void setzAxis(String zAxis) {
    this.zAxis = zAxis;
  }

  public String getWorldRegion() {
    return worldRegion;
  }

  public void setWorldRegion(String worldRegion) {
    this.worldRegion = worldRegion;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLocationText() {
    return locationText;
  }

  public void setLocationText(String locationText) {
    this.locationText = locationText;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDecom() {
    return decom;
  }

  public void setDecom(String decom) {
    this.decom = decom;
  }

  public long getTaxReturnsFiled() {
    return taxReturnsFiled;
  }

  public void setTaxReturnsFiled(long taxReturnsFiled) {
    this.taxReturnsFiled = taxReturnsFiled;
  }

  public long getEstimatedPopulation() {
    return estimatedPopulation;
  }

  public void setEstimatedPopulation(long estimatedPopulation) {
    this.estimatedPopulation = estimatedPopulation;
  }

  public long getTotalWages() {
    return totalWages;
  }

  public void setTotalWages(long totalWages) {
    this.totalWages = totalWages;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
  
}
