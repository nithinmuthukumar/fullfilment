/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import com.google.api.services.actions_fulfillment.v2.model.User;

import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.google.api.services.dialogflow_fulfillment.v2.model.IntentFollowupIntentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements all intent handlers for this Action. Note that your App must extend from DialogflowApp
 * if using Dialogflow or ActionsSdkApp for ActionsSDK based Actions.
 */
public class MyActionsApp extends DialogflowApp {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyActionsApp.class);
  public String[] words={"ablution","hello","please"};
  public Random random=new Random();
  public String word;
  public String answer="";


  @ForIntent("Word")
  public ActionResponse giveWord(ActionRequest request){
    ResponseBuilder responseBuilder = getResponseBuilder(request);
    word=words[random.nextInt(3)];
    responseBuilder.add("Your word is "+word);
    return responseBuilder.build();
  }
  @ForIntent("End Practice")
  public ActionResponse end(ActionRequest request){
    ResponseBuilder responseBuilder = getResponseBuilder(request);

    responseBuilder.add("Goonda super da "+answer).endConversation();

    return responseBuilder.build();
  }
  @ForIntent("Spell")
  public ActionResponse letter(ActionRequest request){
    ResponseBuilder responseBuilder = getResponseBuilder(request);

    if(answer.equals(word)) responseBuilder.add("ok");
    else responseBuilder.add("incorrect");


    return responseBuilder.build();
  }

}
