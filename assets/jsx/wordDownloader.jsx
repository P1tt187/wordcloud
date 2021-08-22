import { refreshWordCloud } from "./wordcloud";
import { downloadQuestion } from "./questionDowloader";

window.addEventListener("load", refreshWordCloud);

let poller = () => {
  downloadQuestion();
  refreshWordCloud();
};

let poll = setInterval(poller, 5000);
