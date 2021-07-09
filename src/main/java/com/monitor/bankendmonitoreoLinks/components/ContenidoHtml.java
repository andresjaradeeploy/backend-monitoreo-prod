package com.monitor.bankendmonitoreoLinks.components;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.htmlparser.jericho.CharacterReference;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftConditionalCommentTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import net.htmlparser.jericho.TextExtractor;
//JEricho Html
public class ContenidoHtml {
	
	private  String meta;
	private  String title;
	
	public String obtenerTitleandMeta(String sourceUrlString)  {
		 
		/*if (args.length==0)
		  System.err.println("Using default argument of \""+sourceUrlString+'"');
		else
			sourceUrlString=args[0];*/
		
		try {
			if (sourceUrlString.indexOf(':')==-1) sourceUrlString="file:"+sourceUrlString;
			MicrosoftConditionalCommentTagTypes.register();
			PHPTagTypes.register();
			PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this example otherwise they override processing instructions
			MasonTagTypes.register();
			Source source;
			source = new Source(new URL(sourceUrlString));
			
			source.fullSequentialParse();

			//System.out.println("Document title:");
			String title=getTitle(source);
			this.title=title;
			setTitle(title);
			//System.out.println(title==null ? "(none)" : title);
			
			

			//System.out.println("\nDocument description:");
			String description=getMetaValue(source,"description");
			this.meta=description;
			setMeta(description);
			//System.out.println(description==null ? "(none)" : description);

			//System.out.println("\nDocument og:description:");
			String keywords=getMetaValue(source,"og:description");
			//System.out.println(keywords==null ? "(none)" : keywords);
			
			
			
			
		
			//System.out.println("\nLinks to other documents:");
			List<Element> linkElements=source.getAllElements(HTMLElementName.A);
			for (Element linkElement : linkElements) {
				String href=linkElement.getAttributeValue("href");
				if (href==null) continue;
				// A element can contain other tags so need to extract the text from it:
				String label=linkElement.getContent().getTextExtractor().toString();
				//System.out.println(label+" <"+href+'>');
			}//

	
			TextExtractor textExtractor=new TextExtractor(source) {
				public boolean excludeElement(StartTag startTag) {
					return startTag.getName()==HTMLElementName.P || "control".equalsIgnoreCase(startTag.getAttributeValue("class"));
				}
			};
			return meta;
		} 
		
		
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
		System.out.println("error URl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error IO");
		}
		return meta;
		// Call fullSequentialParse manually as most of the source will be parsed.
		
		//System.out.println(textExtractor.setIncludeAttributes(true).toString());
  }

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private static String getTitle(Source source) {
		Element titleElement=source.getFirstElement(HTMLElementName.TITLE);
		if (titleElement==null) return null;
		// TITLE element never contains other tags so just decode it collapsing whitespace:
		return CharacterReference.decodeCollapseWhiteSpace(titleElement.getContent());
	}

	private static String getMetaValue(Source source, String key) {
		for (int pos=0; pos<source.length();) {
			StartTag startTag=source.getNextStartTag(pos,"name",key,false);
			if (startTag==null) return null;
			if (startTag.getName()==HTMLElementName.META)
				return startTag.getAttributeValue("content"); // Attribute values are automatically decoded
			pos=startTag.getEnd();
		}
		return null;
	}
	
public static void main(String[] args) {
/*	ContenidoHtml html = new ContenidoHtml();
	html.obtenerTitleandMeta("https://articulo.mercadolibre.com.co/MCO-615249238-rinonera-casual-de-cuero-vintage-velez-_JM?variation=80977532900#reco_item_pos=1&reco_backend=machinalis-homes&reco_backend_type=function&reco_client=home_navigation-recommendations&reco_id=47e4cd00-cd97-4d01-994f-e16f3f76d1ba&c_id=/home/navigation-recommendations/element&c_element_order=2&c_uid=88a75d65-e67f-48b2-a385-3669356dc39a");
	System.out.println("meta"+html.getMeta()+"title"+html.getTitle());
	
	html.obtenerTitleandMeta("https://www.alkosto.com/audifonos-samsung-galaxy-buds-negro/p/8806090203435");
	System.out.println("meta"+html.getMeta()+"title"+html.getTitle());*/
}
}
