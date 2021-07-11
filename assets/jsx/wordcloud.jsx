import React from 'react';
import ReactWordcloud from 'react-wordcloud';
import {ApiClient, DefaultApi, SuggestionGroupedDto} from "suggestor1/src";
import ReactDOM from "react-dom";

export function createWordCloudFrom(words) {
    const option = {
        fontSizes: [46, 96]
    };
    return <ReactWordcloud words={words} options={option}/>;
}

export function refreshWordCloud() {
    let client = new ApiClient();
    client.basePath = window.location.origin;
    let defaultApi = new DefaultApi(client);
    defaultApi.getAllSuggestions((error, data, response) => {

        let wordCloud = createWordCloudFrom(data);
        ReactDOM.render(wordCloud, document.getElementById('root'));
    });
}
