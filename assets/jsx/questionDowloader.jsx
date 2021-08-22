import {ApiClient, DefaultApi} from "../../../wordcloud/client/src";
import ReactDOM from "react-dom";
import React from "react";

class QuestionDisplay extends React.Component {
    render() {
        return (
            <h1>{this.props.question}</h1>
        );
    }
}

window.addEventListener("load", downloadQuestion);

export function downloadQuestion() {
    let client = new ApiClient();
    client.basePath = window.location.origin;
    let defaultApi = new DefaultApi(client);
    defaultApi.getLatestQuestion((error, data, response) => {
        if (data != null) {
            ReactDOM.render(<QuestionDisplay question={data.question}/>, document.getElementById("question"))
        }
    });
}