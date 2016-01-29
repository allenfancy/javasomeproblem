package com.allenfancy.HtmlToImage;

import gui.ava.html.image.generator.HtmlImageGenerator;

public class DEMO2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		String tpl ="<html><head><title>DEMO</title><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /></head><body><span id='cta'><a href='http://www.baidu.com' target='_blank' class='piwik_jzl_link piwik_ignore'><div class='btn' style='color: rgb(171, 205, 239); font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 24px; line-height: normal; font-family: serif; padding: 20px 80px; width: auto; height: auto; background-color: rgb(0, 64, 64);'>吴涛测试按钮</div></a></span></body>";
				
				System.out.println(tpl);
		imageGenerator.loadHtml(tpl);
		imageGenerator.getBufferedImage();
		imageGenerator.saveAsImage("/Users/allen/temp/button1.png");
		//imageGenerator.saveAsHtmlWithMap("/Users/allen/temp/hello-world.html", "/Users/allen/temp/button2.png");
	}
	
	private static String changeToString(){
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head></head>");
		sb.append("<body>");
		sb.append("<div class=\"wrapper\">");
		sb.append("<div class=\"holder\">");
		sb.append("<style id=\"preview_style\">");
		sb.append("a#cta_button_1915932_b6444436-c870-40fb-a898-43b969e1e00e {");
		sb.append(" -webkit-font-smoothing:antialiased !important; ");
		sb.append("cursor:pointer !important; ");
		sb.append("-moz-user-select:none !important; ");
		sb.append("-webkit-user-select:none !important;");
		sb.append("-o-user-select:none !important; ");
		sb.append("user-select:none !important; ");
		sb.append("display:inline-block !important; ");
		sb.append("font-weight:bold !important; ");
		sb.append("text-align:center !important;");
		sb.append("text-decoration:none !important;");
		sb.append("font-family:sans-serif !important; ");
		sb.append("border-radius:6px !important; ");
		sb.append("background-color:rgb(53,116,227) !important;");
		sb.append("background-image:-webkit-linear-gradient(top, rgb(53,116,227), rgb(39,87,170)) !important; ");
		sb.append("background-image :-moz-linear-gradient(top, rgb(53,116,227), rgb(39,87,170)) !important; ");
		sb.append("box-shadow:inset 0px 1px rgb(66,145,255) !important; ");
		sb.append("-webkit-box-shadow:inset 0px 1px rgb(66,145,255) !important; ");
		sb.append("-moz-box-shadow:inset 0px 1px rgb(66,145,255) !important; ");
		sb.append("color:rgb(255, 255, 255) !important; ");
		sb.append("border:2px solid rgb(26,58,113) !important; ");
		sb.append("text-shadow:0px -1px rgb(15,34,68) !important; ");
		sb.append("line-height:1.5em !important; ");
		sb.append("padding:15px 30px !important;} ");
		sb.append("a#cta_button_1915932_b6444436-c870-40fb-a898-43b969e1e00e:hover {");
		sb.append("background-color:rgb(58,127,249) !important; ");
		sb.append("background-image:-webkit-linear-gradient(top, rgb(58,127,249), rgb(39,87,170)) !important; ");
		sb.append("background-image :-moz-linear-gradient(top, rgb(58,127,249), rgb(39,87,170)) !important;  ");
		sb.append("box-shadow:inset 0px 1px rgb(71,156,255), 0px 1px 8px rgba(0, 0, 0, 0.3) !important; ");
		sb.append("-webkit-box-shadow:inset 0px 1px rgb(71,156,255), 0px 1px 8px rgba(0, 0, 0,0.3) !important; ");
		sb.append("-moz-box-shadow:inset 0px 1px rgb(71,156,255), 0px 1px 8px rgba(0, 0, 0, 0.3) !important; ");
		sb.append("color:rgb(255,255,255) !important; ");
		sb.append("border:2px solid rgb(31,69,136) !important; }");
		sb.append("a#cta_button_1915932_b6444436-c870-40fb-a898-43b969e1e00e:active, #cta_button_1915932_b6444436-c870-40fb-a898-43b969e1e00e:active:hover {");
		sb.append("background-color:rgb(39,87,170) !important;");
		sb.append("background-image:-webkit-linear-gradient(top, rgb(39,87,170), rgb(53,116,227)) !important; ");
		sb.append("background-image :-moz-linear-gradient(top, rgb(39,87,170), rgb(53,116,227)) !important; ");
		sb.append("box-shadow:inset 0px 1px 10px rgba(0, 0, 0, 0.5) !important; ");
		sb.append("-webkit-box-shadow:inset 0px 1px 10px rgba(0, 0, 0, 0.5) !important; ");
		sb.append("-moz-box-shadow:inset 0px 1px 10px rgba(0, 0, 0, 0.5) !important; ");
		sb.append("color:rgb(244,244,244) !important; ");
		sb.append("}");
		sb.append("</style>");
		sb.append("<a id=\"cta_button_1915932_b6444436-c870-40fb-a898-43b969e1e00e\" class=\"cta_button \">test-allen</a>");
		sb.append("</div></div>");
		//sb.append("<script src=\"//static.hsappstatic.net/CtaUI/static-1.1186/preview/preview.js\" type=\"text/javascript\"></script>");
	//	sb.append("<link href=\"//static.hsappstatic.net/CtaUI/static-1.1186/preview/preview.css\" rel=\"stylesheet\" type=\"text/css\">");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();	
	}

}
