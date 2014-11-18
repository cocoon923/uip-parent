package com.ailife.uip.doc.config;


import com.ailife.uip.core.entity.Param;
import com.ailife.uip.core.util.FileUtil;
import com.ailife.uip.doc.redis.IDocRedisService;
import com.ailife.uip.doc.svn.IDocSVNService;
import com.ailife.uip.doc.util.ID;
import com.alibaba.fastjson.JSONReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chenmm on 10/13/2014.
 */
public class DocInitializer {

	@Autowired
	private DocProperties docProperties;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Autowired
	private IDocSVNService docService;

	@Autowired
	private IDocRedisService docRedisService;


	@PostConstruct
	protected void initialize() throws Exception {
		checkoutDoc();
		initialStaticParams();
		initialDocument();
	}

	private void checkoutDoc() {
		//Checkout document from svn.
		boolean doCheckout = false;
		if(doCheckout) {
			docService.checkoutDoc();
		}
	}

	private void initialDocument() {
//		InputStream inputStream = FileUtil.loadProjectFile(docProperties.getDocPath());
//		List<Inter> interList = JsoupUtil.parseHtml(TikaUtil.parse(inputStream));
	}

	private void initialStaticParams() {
		List<Param> staticParams = Collections.emptyList();
		staticParams.addAll(getParams(docProperties.getRootParamPath()));
		staticParams.addAll(getParams(docProperties.getRequestParamPath()));
		staticParams.addAll(getParams(docProperties.getResponseParamPath()));
		for (Param param : staticParams){
			param.setSeq(ID.getNewId());
			docRedisService.saveParam(param);
		}
	}

	private List<Param> getParams(String filePath) {
		List<Param> params = new ArrayList<Param>();
		InputStream inputStream = FileUtil.loadClassPathFile(filePath);
		JSONReader jsonReader = new JSONReader(new InputStreamReader(inputStream));
		jsonReader.startArray();
		while (jsonReader.hasNext()) {
			Param param = jsonReader.readObject(Param.class);
			param.setSeq(ID.getNewId());
			params.add(param);
		}
		jsonReader.endArray();
		jsonReader.close();
		return params;
	}

}
