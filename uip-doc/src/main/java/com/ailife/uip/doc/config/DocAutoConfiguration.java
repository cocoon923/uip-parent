package com.ailife.uip.doc.config;

import com.ailife.uip.core.util.Symbol;
import com.ailife.uip.doc.svn.SVNUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc.SVNClientManager;

import java.io.File;

/**
 * Created by chenmm on 10/13/2014.
 */
@Configuration
@EnableConfigurationProperties(DocProperties.class)
public class DocAutoConfiguration {

	public static final String CONFIGURATION_PREFIX = "uip.doc";

	@Configuration
	@ConditionalOnMissingBean(DocInitializer.class)
	protected static class UIPDataSourceInitializerConfiguration {

		@Bean
		public DocInitializer docInitializer() {
			return new DocInitializer();
		}

	}

	@Configuration
	@ConditionalOnMissingBean(IdGenerator.class)
	protected static class IdGeneratorConfiguration {

		@Bean
		public IdGenerator idGeneratorInitializer() {
			return new AlternativeJdkIdGenerator();
		}

	}

	@Configuration
	@ConditionalOnMissingBean(SVNClientManager.class)
	protected static class SVNClientManagerConfiguration {

		@Autowired
		private DocProperties docProperties;

		@Bean
		public SVNClientManager svnClientManager() {
			DocProperties.SVN svn = docProperties.getSvn();
			return SVNUtil.authSvn(svn.getUrl(), svn.getName(), svn.getPassword());
		}

		@Bean
		public SVNURL svnurl() {
			return SVNUtil.initialSVNURL(docProperties.getSvn().getUrl());
		}

		@Bean(name = "destPath")
		public File destPath() {
			return new File("uip-doc/docs");
		}

		@Bean(name = "filePath")
		public File filePath() {
			StringBuilder sb = new StringBuilder("uip-doc/docs/");
			sb.append(docProperties.getDocName()).append(Symbol.UNDERLINE).append(VERSION)
					.append(docProperties.getDocVersion()).append(DOCX);
			return new File(sb.toString());
		}

		private final static String DOCX = ".docx";
		private final static String VERSION = "v";
	}

}
