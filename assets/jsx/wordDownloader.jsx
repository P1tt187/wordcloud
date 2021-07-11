import {refreshWordCloud} from "./wordcloud";

window.addEventListener("load", refreshWordCloud);

let poll = setInterval(refreshWordCloud, 5000);



