import { ApiClient, DefaultApi } from "../../../wordcloud/client/src";
import ReactDOM from "react-dom";
import React from "react";

class QuestionDisplay extends React.Component {
  render() {
    return <h1>{this.props.question}</h1>;
  }
}

window.addEventListener("load", downloadQuestion);
var oldQuestion = '';

export function downloadQuestion() {
  let client = new ApiClient();
  client.basePath = window.location.origin;
  let defaultApi = new DefaultApi(client);
  defaultApi.getLatestQuestion((error, data, response) => {
    if (data != null) {
      if (data.question != oldQuestion) {
        let q = document.getElementById("question");
        q.style.webkitAnimation = 'none';
        ReactDOM.render(
          <QuestionDisplay question={data.question} />,
          document.getElementById("question")
        );        

        setTimeout(function () {
          q.style.webkitAnimation = '';
        }, 10);
        oldQuestion = data.question;
      }
    }
  });
}
