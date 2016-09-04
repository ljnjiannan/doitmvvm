package com.nannan.doit.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author ljnjiannan
 * @since 16/8/29.
 */

@Entity()
public class MiCategoryModel {

  @Id
  private Long id;

  private String cateTitle;

  public String getCateTitle() {
    return this.cateTitle;
  }

  public void setCateTitle(String cateTitle) {
    this.cateTitle = cateTitle;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Generated(hash = 2040892682)
  public MiCategoryModel(Long id, String cateTitle) {
    this.id = id;
    this.cateTitle = cateTitle;
  }

  @Generated(hash = 207515916)
  public MiCategoryModel() {
  }

}
