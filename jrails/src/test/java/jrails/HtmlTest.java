package jrails;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class HtmlTest {

    private Html html;

    @Before
    public void setUp() throws Exception {
        html = new Html();
    }

    @Test
    public void empty() {
        assertThat(View.empty().toString(), isEmptyString());
    }
    @Test
    public void html_test1(){
        Html link = View.link_to("Edit", "/edit?id=8");
        assertEquals(link.toString(), "<a href=\"/edit?id=8\">Edit</a>");
        Html form = View.form("/update", link);
        assertEquals(form.toString(), "<form action=\"/update\" accept-charset=\"UTF-8\" method=\"post\"><a href=\"/edit?id=8\">Edit</a></form>");
    }
    @Test
    public void htmlTest() {
        Html okok = new Html("okok");
        Html pTag = View.p(okok);
        assertEquals("<p>okok</p>", pTag.toString());
        Html htmlDoc = View.h1(View.t("hello"));
        assertEquals(htmlDoc.toString(), "<h1>hello</h1>");
    }
    @Test
    public void sampleTags() {
        Html text = View.t("lorum ipsum");
        Html pText = View.p(text);
        assert(pText.toString().equals("<p>lorum ipsum</p>"));
        assert(View.div(pText).toString().equals("<div><p>lorum ipsum</p></div>"));
        assert(View.strong(text).toString().equals("<strong>lorum ipsum</strong>"));
        assert(View.h1(text).toString().equals("<h1>lorum ipsum</h1>"));
    }
}
