import {ApiClient, DefaultApi, SuggestionSubmit} from 'suggestor1/src'
import {refreshWordCloud} from "./wordcloud";

document.querySelector('#suggestform').addEventListener('submit', (e) => {
    e.preventDefault();
    let inputs = e.target.querySelectorAll('input');

    let filledInputs = Array.from(inputs).map((v) => {
        if (v.value !== null && v.value !== '') {
            return new SuggestionSubmit(v.value);
        }
        return null;
    }).filter((element) => {
        return element !== null;
    });
    if (filledInputs.length > 0) {
        let client = new ApiClient()
        client.basePath = window.location.origin;
        let api = new DefaultApi(client);
        api.addSuggestion({
            suggestions: filledInputs
        }, () => {
            e.target.querySelectorAll('input').forEach((input) => {
                input.value = null;
            });
            refreshWordCloud();
        });
    }
});
