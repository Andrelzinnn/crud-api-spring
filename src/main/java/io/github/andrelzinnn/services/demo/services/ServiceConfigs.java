package io.github.andrelzinnn.services.demo.services;

import io.github.andrelzinnn.services.demo.model.Service;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ServiceConfigs {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static Service applyPatchToService(JsonPatch patch, Service targetService)
      throws JsonPatchException, JsonProcessingException {
    JsonNode patched = patch.apply(objectMapper.convertValue(targetService, JsonNode.class));
    return objectMapper.treeToValue(patched, Service.class);
  }
}
