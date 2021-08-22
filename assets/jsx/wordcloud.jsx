import React from "react";
import ReactWordcloud from "react-wordcloud";
import { ApiClient, DefaultApi, SuggestionGroupedDto } from "suggestor1/src";
import ReactDOM from "react-dom";
import { downloadQuestion } from "./questionDowloader";

export function createWordCloudFrom(words) {
  const option = {
    fontSizes: [16, 72],
  };
  return <ReactWordcloud words={words} options={option} />;
}

var oldData = [];

let changed = (data) => {
  oldData = data;
};

let detectChanges = (data, old) => {
  if (data.length != old.length) {
    changed(data);
    return true;
  }
  for (let i = 0; i < data.length; i++) {
    if (data[i].text != old[i].text || data[i].value != old[i].value) {
      changed(data);
      return true;
    }
  }
  return false;
};

export function refreshWordCloud() {
  let client = new ApiClient();
  client.basePath = window.location.origin;
  let defaultApi = new DefaultApi(client);
  defaultApi.getAllSuggestions((error, data, response) => {
    if (detectChanges(data, oldData)) {
      let wordCloud = createWordCloudFrom(data);
      ReactDOM.render(wordCloud, document.getElementById("root"));
      downloadQuestion();
    }
  });
}
