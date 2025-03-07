package com.nebulademo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSONUtils {
  public static String toJSON(Object obj) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());

    String jsonString;
    try {
      jsonString = mapper.writeValueAsString(obj);
      return jsonString;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return "";
  }

}
