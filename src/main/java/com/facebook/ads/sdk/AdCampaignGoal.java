/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.facebook.ads.sdk.APIException.MalformedResponseException;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class AdCampaignGoal extends APINode {
  @SerializedName("engaged_audiences_audience_label_exclusions")
  private List<String> mEngagedAudiencesAudienceLabelExclusions = null;
  @SerializedName("engaged_audiences_audience_label_inclusions")
  private List<String> mEngagedAudiencesAudienceLabelInclusions = null;
  @SerializedName("engaged_audiences_exclusions")
  private List<String> mEngagedAudiencesExclusions = null;
  @SerializedName("engaged_audiences_inclusions")
  private List<String> mEngagedAudiencesInclusions = null;
  @SerializedName("existing_customers_audience_label_exclusions")
  private List<String> mExistingCustomersAudienceLabelExclusions = null;
  @SerializedName("existing_customers_audience_label_inclusions")
  private List<String> mExistingCustomersAudienceLabelInclusions = null;
  @SerializedName("existing_customers_exclusions")
  private List<String> mExistingCustomersExclusions = null;
  @SerializedName("existing_customers_inclusions")
  private List<String> mExistingCustomersInclusions = null;
  @SerializedName("is_ca_expansion_enabled")
  private Boolean mIsCaExpansionEnabled = null;
  @SerializedName("is_lookalike_inclusion_enabled")
  private Boolean mIsLookalikeInclusionEnabled = null;
  @SerializedName("lookalike_inclusions")
  private List<String> mLookalikeInclusions = null;
  @SerializedName("type")
  private Long mType = null;
  protected static Gson gson = null;

  public AdCampaignGoal() {
  }

  public String getId() {
    return null;
  }
  public static AdCampaignGoal loadJSON(String json, APIContext context, String header) {
    AdCampaignGoal adCampaignGoal = getGson().fromJson(json, AdCampaignGoal.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(adCampaignGoal.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      }
    }
    adCampaignGoal.context = context;
    adCampaignGoal.rawValue = json;
    adCampaignGoal.header = header;
    return adCampaignGoal;
  }

  public static APINodeList<AdCampaignGoal> parseResponse(String json, APIContext context, APIRequest request, String header) throws MalformedResponseException {
    APINodeList<AdCampaignGoal> adCampaignGoals = new APINodeList<AdCampaignGoal>(request, json, header);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    Exception exception = null;
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          adCampaignGoals.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
        };
        return adCampaignGoals;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject();
            if (paging.has("cursors")) {
                JsonObject cursors = paging.get("cursors").getAsJsonObject();
                String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                adCampaignGoals.setCursors(before, after);
            }
            String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
            String next = paging.has("next") ? paging.get("next").getAsString() : null;
            adCampaignGoals.setPaging(previous, next);
            if (context.hasAppSecret()) {
              adCampaignGoals.setAppSecret(context.getAppSecretProof());
            }
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              adCampaignGoals.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            boolean isRedownload = false;
            for (String s : new String[]{"campaigns", "adsets", "ads"}) {
              if (obj.has(s)) {
                isRedownload = true;
                obj = obj.getAsJsonObject(s);
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                  adCampaignGoals.add(loadJSON(entry.getValue().toString(), context, header));
                }
                break;
              }
            }
            if (!isRedownload) {
              adCampaignGoals.add(loadJSON(obj.toString(), context, header));
            }
          }
          return adCampaignGoals;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              adCampaignGoals.add(loadJSON(entry.getValue().toString(), context, header));
          }
          return adCampaignGoals;
        } else {
          // Fifth, check if it's an array of objects indexed by id
          boolean isIdIndexedArray = true;
          for (Map.Entry entry : obj.entrySet()) {
            String key = (String) entry.getKey();
            if (key.equals("__fb_trace_id__")) {
              continue;
            }
            JsonElement value = (JsonElement) entry.getValue();
            if (
              value != null &&
              value.isJsonObject() &&
              value.getAsJsonObject().has("id") &&
              value.getAsJsonObject().get("id") != null &&
              value.getAsJsonObject().get("id").getAsString().equals(key)
            ) {
              adCampaignGoals.add(loadJSON(value.toString(), context, header));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return adCampaignGoals;
          }

          // Sixth, check if it's pure JsonObject
          adCampaignGoals.clear();
          adCampaignGoals.add(loadJSON(json, context, header));
          return adCampaignGoals;
        }
      }
    } catch (Exception e) {
      exception = e;
    }
    throw new MalformedResponseException(
      "Invalid response string: " + json,
      exception
    );
  }

  @Override
  public APIContext getContext() {
    return context;
  }

  @Override
  public void setContext(APIContext context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }


  public List<String> getFieldEngagedAudiencesAudienceLabelExclusions() {
    return mEngagedAudiencesAudienceLabelExclusions;
  }

  public AdCampaignGoal setFieldEngagedAudiencesAudienceLabelExclusions(List<String> value) {
    this.mEngagedAudiencesAudienceLabelExclusions = value;
    return this;
  }

  public List<String> getFieldEngagedAudiencesAudienceLabelInclusions() {
    return mEngagedAudiencesAudienceLabelInclusions;
  }

  public AdCampaignGoal setFieldEngagedAudiencesAudienceLabelInclusions(List<String> value) {
    this.mEngagedAudiencesAudienceLabelInclusions = value;
    return this;
  }

  public List<String> getFieldEngagedAudiencesExclusions() {
    return mEngagedAudiencesExclusions;
  }

  public AdCampaignGoal setFieldEngagedAudiencesExclusions(List<String> value) {
    this.mEngagedAudiencesExclusions = value;
    return this;
  }

  public List<String> getFieldEngagedAudiencesInclusions() {
    return mEngagedAudiencesInclusions;
  }

  public AdCampaignGoal setFieldEngagedAudiencesInclusions(List<String> value) {
    this.mEngagedAudiencesInclusions = value;
    return this;
  }

  public List<String> getFieldExistingCustomersAudienceLabelExclusions() {
    return mExistingCustomersAudienceLabelExclusions;
  }

  public AdCampaignGoal setFieldExistingCustomersAudienceLabelExclusions(List<String> value) {
    this.mExistingCustomersAudienceLabelExclusions = value;
    return this;
  }

  public List<String> getFieldExistingCustomersAudienceLabelInclusions() {
    return mExistingCustomersAudienceLabelInclusions;
  }

  public AdCampaignGoal setFieldExistingCustomersAudienceLabelInclusions(List<String> value) {
    this.mExistingCustomersAudienceLabelInclusions = value;
    return this;
  }

  public List<String> getFieldExistingCustomersExclusions() {
    return mExistingCustomersExclusions;
  }

  public AdCampaignGoal setFieldExistingCustomersExclusions(List<String> value) {
    this.mExistingCustomersExclusions = value;
    return this;
  }

  public List<String> getFieldExistingCustomersInclusions() {
    return mExistingCustomersInclusions;
  }

  public AdCampaignGoal setFieldExistingCustomersInclusions(List<String> value) {
    this.mExistingCustomersInclusions = value;
    return this;
  }

  public Boolean getFieldIsCaExpansionEnabled() {
    return mIsCaExpansionEnabled;
  }

  public AdCampaignGoal setFieldIsCaExpansionEnabled(Boolean value) {
    this.mIsCaExpansionEnabled = value;
    return this;
  }

  public Boolean getFieldIsLookalikeInclusionEnabled() {
    return mIsLookalikeInclusionEnabled;
  }

  public AdCampaignGoal setFieldIsLookalikeInclusionEnabled(Boolean value) {
    this.mIsLookalikeInclusionEnabled = value;
    return this;
  }

  public List<String> getFieldLookalikeInclusions() {
    return mLookalikeInclusions;
  }

  public AdCampaignGoal setFieldLookalikeInclusions(List<String> value) {
    this.mLookalikeInclusions = value;
    return this;
  }

  public Long getFieldType() {
    return mType;
  }

  public AdCampaignGoal setFieldType(Long value) {
    this.mType = value;
    return this;
  }




  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public AdCampaignGoal copyFrom(AdCampaignGoal instance) {
    this.mEngagedAudiencesAudienceLabelExclusions = instance.mEngagedAudiencesAudienceLabelExclusions;
    this.mEngagedAudiencesAudienceLabelInclusions = instance.mEngagedAudiencesAudienceLabelInclusions;
    this.mEngagedAudiencesExclusions = instance.mEngagedAudiencesExclusions;
    this.mEngagedAudiencesInclusions = instance.mEngagedAudiencesInclusions;
    this.mExistingCustomersAudienceLabelExclusions = instance.mExistingCustomersAudienceLabelExclusions;
    this.mExistingCustomersAudienceLabelInclusions = instance.mExistingCustomersAudienceLabelInclusions;
    this.mExistingCustomersExclusions = instance.mExistingCustomersExclusions;
    this.mExistingCustomersInclusions = instance.mExistingCustomersInclusions;
    this.mIsCaExpansionEnabled = instance.mIsCaExpansionEnabled;
    this.mIsLookalikeInclusionEnabled = instance.mIsLookalikeInclusionEnabled;
    this.mLookalikeInclusions = instance.mLookalikeInclusions;
    this.mType = instance.mType;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<AdCampaignGoal> getParser() {
    return new APIRequest.ResponseParser<AdCampaignGoal>() {
      public APINodeList<AdCampaignGoal> parseResponse(String response, APIContext context, APIRequest<AdCampaignGoal> request, String header) throws MalformedResponseException {
        return AdCampaignGoal.parseResponse(response, context, request, header);
      }
    };
  }
}
