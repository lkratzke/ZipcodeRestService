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


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Loren Kratzke
 */
@RestController
public class ZipRestController {
  @Value("${test.property}")
  private String testProp;

  @Autowired
  private ZipDAO zipDao;
  
  @GetMapping(value = "/rest", produces = "application/json; charset=UTF-8")
  Resources<Resource<CountryBean>> countries() {
    System.out.println(testProp);
    List<Resource<CountryBean>> resourceList = new ArrayList<>();
    List<CountryBean> countryList = zipDao.getCountries();
    for (CountryBean countryBean : countryList) {
      resourceList.add(new Resource<>(countryBean, 
            linkTo(methodOn(ZipRestController.class).states(countryBean.getCountry())).withSelfRel()));
    }
    return new Resources<>(
            resourceList, 
            linkTo(methodOn(ZipRestController.class).countries()).withSelfRel());
  }
  
  @GetMapping(value = "/rest/{country}", produces = "application/json; charset=UTF-8")
  Resources<Resource<StateBean>> states(@PathVariable String country) {
    List<Resource<StateBean>> resourceList = new ArrayList<>();
    List<StateBean> stateList = zipDao.getStates(country);
    
    for (StateBean stateBean : stateList) {
      resourceList.add(new Resource<>(stateBean,
            linkTo(methodOn(ZipRestController.class).cities(country, stateBean.getState())).withSelfRel()));
    }
    return new Resources<>(
            resourceList, 
            linkTo(methodOn(ZipRestController.class).states(country)).withSelfRel());
  }
  
  @GetMapping(value = "/rest/{country}/{state}", produces = "application/json; charset=UTF-8")
  Resources<Resource<CityBean>> cities(
          @PathVariable String country, 
          @PathVariable String state) {
    List<Resource<CityBean>> resourceList = new ArrayList<>();
    List<CityBean> cityList = zipDao.getCities(country, state);
    
    for (CityBean cityBean : cityList) {
      resourceList.add(new Resource<>(cityBean,
            linkTo(methodOn(ZipRestController.class).zipsInCity(country, state, cityBean.getCity())).withSelfRel()));
    }
    return new Resources<>(
            resourceList, 
            linkTo(methodOn(ZipRestController.class).cities(country, state)).withSelfRel());
  }
  
  @GetMapping(value = "/rest/{country}/{state}/{city}", produces = "application/json; charset=UTF-8")
  Resources<Resource<ZipSummaryBean>> zipsInCity(
          @PathVariable String country, 
          @PathVariable String state, 
          @PathVariable String city) {
    List<Resource<ZipSummaryBean>> resourceList = new ArrayList<>();
    List<ZipSummaryBean> zipList = zipDao.getZipSummaries(country, state, city);
    
    for (ZipSummaryBean zipBean : zipList) {
      resourceList.add(new Resource<>(zipBean,
            linkTo(methodOn(ZipRestController.class).zip(country, state, city, zipBean.getId())).withSelfRel(),
            linkTo(methodOn(ZipRestController.class).zipsInCity(country, state, city)).withRel("zipInCity"))); // can remove thiss
    }
    return new Resources<>(
            resourceList, 
            linkTo(methodOn(ZipRestController.class).zipsInCity(country, state, city)).withSelfRel());
  }
  
  @GetMapping(value = "/rest/{country}/{state}/{city}/{id}", produces = "application/json; charset=UTF-8")
  Resource<ZipBean> zip(
          @PathVariable String country, 
          @PathVariable String state, 
          @PathVariable String city, 
          @PathVariable int id) {
    ZipBean zipBean = zipDao.getZip(id);
    return new Resource<>(
            zipBean,
            linkTo(methodOn(ZipRestController.class).zip(country, state, city, id)).withSelfRel());
  }  
}
