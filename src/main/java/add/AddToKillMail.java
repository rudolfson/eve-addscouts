package add;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;

import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class AddToKillMail {

    private WebClient webClient;
    private Publisher publisher;
    private String killMailUrl;
    private List<String> pilotsToAdd;

    public AddToKillMail(WebClient webClient, Publisher publisher, String killMailUrl, List<String> pilots) {
        this.webClient = webClient;
        this.publisher = publisher;
        this.killMailUrl = killMailUrl;
        this.pilotsToAdd = pilots;
    }

    public void start() throws Exception {
        publisher.publish(String.format("Processing kill <a href=\"%s\">%s</a>", killMailUrl, killMailUrl));
        // extract current pilots names
        Collection<String> currentPilots = readPilots();
        // add each pilot, check if already exists
        for (String pilot : pilotsToAdd) {
            if (currentPilots.contains(pilot)) {
                publisher.publish(String.format("%s already on kill mail - skipping", pilot));
            } else {
                publisher.publish(String.format("Adding %s", pilot));
            }
        }
        // read all pilot names again and confirm that all pilots are listed
        currentPilots = readPilots();
    }

    private Collection<String> readPilots() throws Exception {
        Set<String> currentPilots = new HashSet<String>();
        HtmlPage page = webClient.getPage(killMailUrl + "&nolimit");
        // find all regular pilots
        DomNodeList<DomNode> pilotLinks = page.querySelectorAll("tr.kill-pilot-name > td > a");
        for (DomNode node : pilotLinks) {
            currentPilots.add(node.getTextContent());
        }
        // find all scouts/logis
        pilotLinks = page.querySelectorAll("div#kl-detail-left table.kb-table table.kb-table td.kb-table-cell > a");
        for (DomNode node : pilotLinks) {
            if (((HtmlAnchor) node).getHrefAttribute().contains("pilot_detail")) {
                currentPilots.add(node.getTextContent());
            }
        }

        return currentPilots;
    }
}
