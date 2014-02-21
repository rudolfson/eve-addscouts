package add;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.net.URL;

import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class AddToKillMail {

    private WebClient webClient;
    private Publisher publisher;
    private String killMailUrl;
    private List<String> pilotsToAdd;
    private String password;

    public AddToKillMail(
            WebClient webClient, Publisher publisher, String killMailUrl, List<String> pilots, String password) {
        this.webClient = webClient;
        this.publisher = publisher;
        this.killMailUrl = killMailUrl;
        this.pilotsToAdd = pilots;
        this.password = password;
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
                try {
                    publisher.publish(String.format("Adding %s", pilot));
                    WebRequest request = new WebRequest(new URL(killMailUrl), HttpMethod.POST);
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new NameValuePair("scoutsubmit", "add pilot"));
                    params.add(new NameValuePair("scoutname", pilot));
                    params.add(new NameValuePair("password", password));
                    webClient.getPage(request);
                } catch (Exception ex) {
                    publisher.publish(
                            String.format("%s adding %s: %s", ex.getClass().getName(), pilot, ex.getMessage()));
                }
            }
        }

        publisher.publish(String.format("Adding completed, checking if everyone's added."));

        // read all pilot names again and confirm that all pilots are listed
        currentPilots = readPilots();
        List<String> missing = new ArrayList<String>();
        for (String pilot : pilotsToAdd) {
            if (!currentPilots.contains(pilot)) {
                missing.add(pilot);
            }
        }
        if (missing.isEmpty()) {
            publisher.publish("Verified that all pilots have been added.");
        } else {
            publisher.publish(String.format("Hmmm, missing pilots: %s", missing));
        }

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
