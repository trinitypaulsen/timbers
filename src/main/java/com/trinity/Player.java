package com.trinity;

public class Player {

  private int id;
  String lastName;
  String firstName;

  public void setPlayerNumber(int i) {
      this.id = i;
  }

  public void setLastName(String n) {
      this.lastName = n;
  }
  public void setFirstName(String n) {
      this.firstName = n;
  }

  public int getPlayerNumber() {
      return this.id;
  }

  public String getLastName() {
      return this.lastName;
  }

  public String getFirstName() {
      return this.firstName;
  }

}
