package add;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class ScoutAdder {
    private Publisher publisher;
    private List<String> pilots;
    private List<String> reportsAndKills;
    private Set<String> consolidatedKills;
    private WebClient webClient;
    private boolean stopIt = true;

    /**
     * Create a new instance for adding the given pilots to the given list of
     * reports and kill.
     */
    public ScoutAdder(Publisher publisher, List<String> pilots, List<String> reportsAndKills) {
        this.publisher = publisher;
        this.pilots = pilots;
        this.reportsAndKills = reportsAndKills;
        this.consolidatedKills = new HashSet<String>();
        this.webClient = new WebClient( BrowserVersion.FIREFOX_17 );
    }

    /**
     * Start the work.
     */
    public void start() throws Exception {
        publisher.publish(
                String.format(
                    "You entered %d pilots and %d urls. I will start by consolidating the kill mails.", 
                    this.pilots.size(), this.reportsAndKills.size()));
        for (String url : reportsAndKills) {
            publisher.publish(String.format("Checking <a href=\"%s\">%s</a>", url, url));
            if (isBattleReportUrl(url)) {
                List<String> kills = extractKillsFromBattleReport(url); 
                this.consolidatedKills.addAll(kills);
                publisher.publish(String.format("Added %d kill mails from battle report", kills.size()));
            } else if (isKillUrl(url)) {
                this.consolidatedKills.add(url);
                publisher.publish("Added kill mail");
            }
        }
        publisher.publish(String.format("Finished consolidating mails, I got %d distinct of them now.",
                consolidatedKills.size()));
    }

    /**
     * Tell this adder that we want to stop the process soon (tm).
     */
    public void stop() {
        this.stopIt = true;
    }

    /**
     * Check if the given url seems to be a battle report.
     */
    private boolean isBattleReportUrl(String url) {
        return url != null && url.contains("kill_related");
    }

    /**
     * Check if the given url seems to be a kill detail page.
     */
    private boolean isKillUrl(String url) {
        return url != null && url.contains("kill_detail");
    }

    /**
     * Load the given battle report url and parse it for hostile kills.
     */
    public List<String> extractKillsFromBattleReport(String battleReportUrl)
        throws Exception {
        HtmlPage page = webClient.getPage( battleReportUrl );

        // examine table headers to find the hostiles table
        DomNodeList<DomNode> headers = page.querySelectorAll( "div#pilots_and_ships div.kb-date-header" );
        Node hostileHeader = null;
        for ( DomNode header : headers ) {
            if ( header.getTextContent().contains( "Hostile" ) ) {
                hostileHeader = header;
                break;
            }
        }

        // get all kill links from hostile table
        HtmlTable hostileTable =
            (HtmlTable) ( (HtmlElement) hostileHeader.getParentNode() ).querySelector( "table.kb-table" );
        DomNodeList<DomNode> killLinks = hostileTable.querySelectorAll( "tr.br-destroyed > td:first-child > a" );
        List<String> kills = new ArrayList<String>( killLinks.size() );
        for ( DomNode domNode : killLinks ) {
            kills.add( ( (HtmlAnchor) domNode ).getHrefAttribute() );
        }
        return kills;
    }
}
