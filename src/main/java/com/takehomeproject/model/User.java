package com.takehomeproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//Importamos libreria lombok
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//We use @Data from lombok library to generate automatically constructors, getter, setter, equals, hascode and toString methods.
@Data
@Entity
@Table(name = "USER")
public class User {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "First_Name", nullable = false, length = 30)
  private String firstname;

  @Column(name = "Last_Name", nullable = false, length = 30)
  private String lastname;

  @Column(name = "Email", nullable = false, length = 50)
  private String email;

  @Column(name = "Password", nullable = false, length = 150)
  @JsonIgnore
  private String password;

  @JsonIgnore
  public String getPassword() {
    return password;
  }
  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }
}
