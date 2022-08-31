package co.grtk.entity.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Identifier of preferred authorization method type proposed by local BE 
 */
public enum AuthorizationType {
  
  TAC("TAC"),
  
  SMS("SMS"),
  
  TAN("TAN"),
  
  CASE_MOBILE("CASE_MOBILE"),
  
  CARDTAN("CARDTAN"),
  
  GRID_CARD("GRID_CARD"),
  
  DISPLAY_CARD("DISPLAY_CARD"),
  
  E_DEVICE("E_DEVICE"),
  
  MOBILE_DEVICE("MOBILE_DEVICE"),
  
  MOBILE_TOKEN("MOBILE_TOKEN"),
  
  M_TOKEN("M_TOKEN"),
  
  VOICE("VOICE"),
  
  NONE("NONE"),
  
  TRUSTED("TRUSTED"),
  
  PKI("PKI"),
  
  EOK("EOK"),
  
  ALL("ALL"),
  
  INTEGRATED_TOKEN("INTEGRATED_TOKEN"),
  
  TOKEN("TOKEN");

  private String value;

  AuthorizationType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AuthorizationType fromValue(String value) {
    for (AuthorizationType b : AuthorizationType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

