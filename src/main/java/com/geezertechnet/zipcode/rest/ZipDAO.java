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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Loren Kratzke
 */
@Component
public class ZipDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private static final String GET_COUNTRIES = "select distinct(country) from ZIPS";
  private static final String GET_STATES = "select distinct(state) from ZIPS where country=?";
  private static final String GET_CITIES = "select distinct(city) from ZIPS where country=? and state=?";
  private static final String GET_ZIP_SUMMARIES = "select id, zipcode from ZIPS where country=? and state=? and city=?";
  private static final String GET_ZIPS = "select * from ZIPS where country=? and state=? and city=?";
  private static final String GET_ZIP = "select * from ZIPS where id = ?";

  public List<CountryBean> getCountries() {
    final List<CountryBean> list = new ArrayList<>();
    jdbcTemplate.query(GET_COUNTRIES, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rset) throws SQLException {
        CountryBean bean = new CountryBean();
        bean.setCountry(rset.getString("country"));
        list.add(bean);
      }
    });
    return list;
  }

  public List<StateBean> getStates(String country) {
    String[] args = new String[]{country};
    final List<StateBean> list = new ArrayList<>();
    jdbcTemplate.query(GET_STATES, args, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rset) throws SQLException {
        StateBean bean = new StateBean();
        bean.setCountry(country);
        bean.setState(rset.getString("state"));
        list.add(bean);
      }
    });
    return list;
  }

  public List<CityBean> getCities(String country, String state) {
    String[] args = new String[]{country, state};
    final List<CityBean> list = new ArrayList<>();
    jdbcTemplate.query(GET_CITIES, args, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rset) throws SQLException {
        CityBean bean = new CityBean();
        bean.setCountry(country);
        bean.setState(state);
        bean.setCity(rset.getString("city"));
        list.add(bean);
      }
    });
    return list;
  }
  
  public List<ZipSummaryBean> getZipSummaries(String country, String state, String city) {
    String[] args = new String[]{country, state, city};
    final List<ZipSummaryBean> list = new ArrayList<>();
    jdbcTemplate.query(GET_ZIP_SUMMARIES, args, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rset) throws SQLException {
        ZipSummaryBean bean = new ZipSummaryBean();
        bean.setCountry(country);
        bean.setState(state);
        bean.setCity(city);
        bean.setId(rset.getInt("id"));
        bean.setZipcode(rset.getString("zipcode"));
        list.add(bean);
      }
    });
    return list;
  }

  public List<ZipBean> getZips(String country, String state, String city) {
    String[] args = new String[]{country, state, city};
    final List<ZipBean> list = new ArrayList<>();
    jdbcTemplate.query(GET_ZIPS, args, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rset) throws SQLException {
        list.add(unloadZipRecord(rset));
      }
    });
    return list;
  }
  
  public ZipBean getZip(int id) {
    final List<ZipBean> list = new ArrayList<>();
    String[] args = new String[]{Integer.toString(id)};
    jdbcTemplate.query(GET_ZIP, args, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rset) throws SQLException {
        list.add(unloadZipRecord(rset));
      }
    });
    return list.get(0);
  }

  private ZipBean unloadZipRecord(ResultSet rset) throws SQLException {
    ZipBean bean = new ZipBean();
    bean.setCountry(rset.getString("country"));
    bean.setState(rset.getString("state"));
    bean.setCity(rset.getString("city"));
    bean.setDecom(rset.getString("decom"));
    bean.setEstimatedPopulation(rset.getLong("est_pop"));
    bean.setId(rset.getInt("id"));
    bean.setLat(rset.getString("lat"));
    bean.setLocation(rset.getString("location"));
    bean.setLocationText(rset.getString("location_text"));
    bean.setLocationType(rset.getString("location_type"));
    bean.setLon(rset.getString("lon"));
    bean.setNotes(rset.getString("notes"));
    bean.setRecordNumber(rset.getInt("record_num"));
    bean.setTaxReturnsFiled(rset.getLong("taxreturns_filed"));
    bean.setTotalWages(rset.getLong("total_wages"));
    bean.setWorldRegion(rset.getString("world_region"));
    bean.setZipCode(rset.getString("zipcode"));
    bean.setZipCodeType(rset.getString("zipcode_type"));
    bean.setxAxis(rset.getString("xaxis"));
    bean.setyAxis(rset.getString("yaxis"));
    bean.setzAxis(rset.getString("zaxis"));
    
    return bean;
  }

  public String health() {
    if (jdbcTemplate != null) {
      return "got connection";
    } else {
      return "Failed to get connection";
    }
  }
}
