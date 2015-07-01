package com.daijt.cth.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestOperations;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;

import com.daijt.cth.adaptor.BingTranslatorAdaptor;
import com.daijt.cth.bo.AccessToken;
import com.daijt.cth.bo.TattooSample;
import com.daijt.cth.dao.CFontDAO;
import com.daijt.cth.dao.DictionaryDAO;
import com.daijt.cth.dao.DownloadHistoryDAO;
import com.daijt.cth.persistence.CFont;
import com.daijt.cth.persistence.DownloadRecord;
import com.daijt.cth.persistence.WordEntry;
import com.daijt.cth.util.IOUtil;
import com.daijt.cth.util.Misc;

@Controller
@RequestMapping("/cthlight")
public class CTHLightController {

	private static Logger log = Logger.getLogger(CTHLightController.class);
	
	private String imgUrl;
	private String imgFolder;
	private String imgFormat;
	private String fontFolder;
//	private String appId;
	private String engCode;
	private String chsCode;
	private String transUrl;
	private int ifnImgWidth;
	private int ifnImgHeight;
	private int textSize;
	
	@Autowired
	private CFontDAO cfontDao;
	
	@Autowired
	private DictionaryDAO dictionaryDao;
	
	@Autowired
	private DownloadHistoryDAO downloadHistoryDao;
	
	@Autowired
	private RestOperations restTemplate;
	
	@Autowired
	private Jaxp13XPathTemplate xpathTemplate;
	
	/**
	 * 
	 * @param word
	 * @param font
	 */
	@Transactional
	@RequestMapping(method=RequestMethod.GET, value = "/ird/{word}/{font}")
	public void recordDownload(@PathVariable String word, @PathVariable String font) {
		if (log.isDebugEnabled()) {
			log.debug("Iphone client download image for " + word + " using font id " + font);
		}
		
		DownloadRecord dr = new DownloadRecord();
		dr.setWord(word);
		long fontId = NumberUtils.toLong(font);
		dr.setFontId(fontId);
		dr.setDeviceId(1L);
		dr.setSourceId(1L);
		
		downloadHistoryDao.addDownloadRecord(dr);
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.GET, value = "/ard/{word}/{font}")
	public void recordAndroidDl(@PathVariable String word, @PathVariable String font) {
		if (log.isDebugEnabled()) {
			log.debug("Android client download image for " + word + " using font id " + font);
		}
		
		DownloadRecord dr = new DownloadRecord();
		dr.setWord(word);
		long fontId = NumberUtils.toLong(font);
		dr.setFontId(fontId);
		dr.setDeviceId(2L);
		dr.setSourceId(1L);
		
		downloadHistoryDao.addDownloadRecord(dr);
	}
	
	/**
	 * Query image from iphone
	 * @param word
	 * @param font
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional
	@RequestMapping(method=RequestMethod.GET, value = "/iri/{word}/{font}")
	public @ResponseBody Map<String, ? extends Object> retrieveSample(@PathVariable String word, @PathVariable String font,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) {
			log.debug("calling service /iri/" + word + "/" + font);
		}
		
		// Manipulate input String 
		String trimmedWord = StringUtils.lowerCase(Misc.removeNonAlphabetic(StringUtils.trim(word)));
		
		String untrWord = StringUtils.replace(trimmedWord, " ", "_");
		
		// Return error for empty string
		if (untrWord.isEmpty() || untrWord.equals("_")) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return Collections.singletonMap("error", word + " is not a valid English word.");
		}
		
		List<WordEntry> words = dictionaryDao.wordLookup(Misc.formLikeQueryStr(untrWord));
		
		WordEntry we = null;
		
		if (words != null) {
			for (WordEntry wd : words) {
				
				if (log.isDebugEnabled()) {
					log.debug("Match " + untrWord + " within " +  StringUtils.lowerCase(wd.getTags()));
				}
				
				boolean matched = Misc.matchWordThroughTags(untrWord, StringUtils.lowerCase(wd.getTags()));
				if (matched) {
					we = wd;
					break;
				}
			}
		}
		
		boolean tranReq = false;
		
		// Chinese word look up using MICROSOFT Bing transalator
		if (we == null) {
			tranReq = true;
			String queryText = Misc.formatString4Query(trimmedWord);
			
			// Form query URL
			StringBuilder sb = new StringBuilder(transUrl);
			sb.append(queryText);
			sb.append("&from=");
			sb.append(engCode);
			sb.append("&to=");
			sb.append(chsCode);
			
			String queryStr = sb.toString();
			
			if (log.isDebugEnabled()) {
				log.debug("Query Bing: " + queryStr);
			}
			
			String chText = null;
			boolean transSuc = false;

			
			HttpSession session = Misc.retrieveSession();
			
			String accessToken = (String)session.getAttribute("accesstoken");
			Date expiration = (Date)session.getAttribute("token_expriation");
			
			if (accessToken == null || expiration == null || expiration.before(new Date())) {
				
				AccessToken at = BingTranslatorAdaptor.getAccessToken();
				accessToken = at.getAccess_token();
				int duration = at.getExpires_in();
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				cal.add(Calendar.SECOND, duration - 30);
				expiration = cal.getTime();
				
				session.setAttribute("accesstoken", accessToken);
				session.setAttribute("token_expriation", expiration);

			}
			
			try {
				/*
				Source xmlResp = restTemplate.getForObject(queryStr, Source.class);
				
				chText = xpathTemplate.evaluateAsString("/*", xmlResp);
				*/
				
				chText = BingTranslatorAdaptor.translate(queryStr, accessToken, restTemplate, xpathTemplate);

				if (log.isInfoEnabled()) {
					log.info("bing translate " + trimmedWord + " into " + chText);
				}
				
				
			}
			catch(Exception e) {
				log.error("Query Bing failed:", e);
			}
			
			if (chText != null) {
				// 检查是否是英文
				if (StringUtils.equals(trimmedWord, chText)) {
					log.warn("Bing is not able to translate " + trimmedWord);
				}
				else {
					we = new WordEntry();
					we.setId(StringUtils.remove(trimmedWord, " "));
					we.setWord(trimmedWord);
					we.setAudit(0);
					we.setSafeStatus(0);
					we.setTags(untrWord);
					we.setTradCh(chText);
					we.setCategory("general");
						
					transSuc = true;
				}
			}
			
			if (!transSuc) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return Collections.singletonMap("error", "Translate failed.");
			}
		}
		
		
		long fontId = NumberUtils.toLong(font);
		
		StringBuilder imgStrBuilder = new StringBuilder(untrWord);
		imgStrBuilder.append(font);
		imgStrBuilder.append(".");
		imgStrBuilder.append(imgFormat);
		
		String imageFile = imgStrBuilder.toString();
		
		StringBuilder imageFileLoc = new StringBuilder(imgFolder);
		imageFileLoc.append(imageFile);
		
		String fileLoc = imageFileLoc.toString();
		
		if (log.isDebugEnabled()) {
			log.debug("sample file physical location: " + fileLoc);
		}
		
		long prevId = fontId;
		long nextId = fontId;
		
		IOUtil ioUtil = new IOUtil();
		
		boolean fileGen = ioUtil.isFileExist(fileLoc);
		
		List<CFont> fonts = cfontDao.listAllFonts();
		
		CFont pickedFont = null;
		
		int fontIndex = 0;
		
		// Find previous and next font 
		for (CFont ft : fonts) {
			if (ft.getId() == fontId) {
				pickedFont = ft;
				break;
			}
			
			fontIndex ++;
		}
		
		if (pickedFont == null) {
			pickedFont = fonts.get(0);
			fontIndex = 0;
		}
		
		if (fontIndex != 0) {
			prevId = fonts.get(fontIndex - 1).getId();
		}
		
		if (fontIndex != fonts.size() - 1) {
			nextId = fonts.get(fontIndex + 1).getId();
		}
		
		// Generate sample files
		if (!fileGen) {
			
			StringBuilder sb = new StringBuilder(fontFolder);
			sb.append(pickedFont.getFile());
			
			String fontPath = sb.toString();
		
			String message = we.getTradCh();
			
			if (log.isDebugEnabled()) {
				log.debug("Generate image for : " + message);
			}
			
			boolean fileCreate = ioUtil.generateImageFile(fontPath, (float)textSize, message, fileLoc, imgFormat, ifnImgWidth, ifnImgHeight);
			
			if (!fileCreate) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return Collections.singletonMap("error", "Resource doesn't found");
			}
		}
		
		StringBuilder fileUrlPath = new StringBuilder(imgUrl);
		fileUrlPath.append(imageFile);
		String fileUrl = fileUrlPath.toString();
		
		// Prepare returning data
		TattooSample ts = new TattooSample();
		ts.setFontId(fontId);
		ts.setImageUrl(fileUrl);
		ts.setStatus(we.getSafeStatus());
		ts.setWord(word);
		ts.setPrevId(prevId);
		ts.setNextId(nextId);
		ts.setMessage(we.getWarning());
		
		// Database insert
		if (tranReq) {
			
			if (log.isDebugEnabled()) {
				log.debug("Database entry add: " + we.getWord());
			}
			
			try {
				dictionaryDao.addWordEntry(we);
			}
			catch (Exception e) {
				log.warn("Failed insert new word entry ", e);
			}
			
		}
		
		return Collections.singletonMap("sample", ts);
	}
	
	@Value("#{cthProperties['resource.sample.url'] }")
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Value("#{cthProperties['resource.sample.folder'] }")
	public void setImgFolder(String imgFolder) {
		this.imgFolder = imgFolder;
	}

	@Value("#{cthProperties['resource.sample.format'] }")
	public void setImgFormat(String imgFormat) {
		this.imgFormat = imgFormat;
	}

	@Value("#{cthProperties['resource.font.folder'] }")
	public void setFontFolder(String fontFolder) {
		this.fontFolder = fontFolder;
	}

//	@Value("#{cthProperties['microsoft.app.id'] }")
//	public void setAppId(String appId) {
//		this.appId = appId;
//	}

	@Value("#{cthProperties['translator.lng.en'] }")
	public void setEngCode(String engCode) {
		this.engCode = engCode;
	}

	@Value("#{cthProperties['translator.lng.ch.t'] }")
	public void setChsCode(String chsCode) {
		this.chsCode = chsCode;
	}

	@Value("#{cthProperties['translator.url'] }")
	public void setTransUrl(String transUrl) {
		this.transUrl = transUrl;
	}

	@Value("#{cthProperties['iphone.img.width'] }")
	public void setIfnImgWidth(int ifnImgWidth) {
		this.ifnImgWidth = ifnImgWidth;
	}

	@Value("#{cthProperties['iphone.img.height'] }")
	public void setIfnImgHeight(int ifnImgHeight) {
		this.ifnImgHeight = ifnImgHeight;
	}

	@Value("#{cthProperties['iphone.text.size'] }")
	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}
	
	
}
