package add;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
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

@RunWith( BlockJUnit4ClassRunner.class )
public class BattleReportTest {

    @Test
    public void readbr()
        throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        WebClient wc = new WebClient( BrowserVersion.FIREFOX_17 );
        HtmlPage page = wc.getPage( "http://killfeed.eveuniversity.org/?a=kill_related&kll_id=138232" );

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

        // now call each kill link, use &nolimit option to get all pilots
    }
}
