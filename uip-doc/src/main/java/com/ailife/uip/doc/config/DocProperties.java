package com.ailife.uip.doc.config;

import com.ailife.uip.core.util.LogUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * Created by chenmm on 9/29/2014.
 */
@ConfigurationProperties(prefix = DocAutoConfiguration.CONFIGURATION_PREFIX)
public class DocProperties {

	private String rootParamPath;
	private String requestParamPath;
	private String responseParamPath;
	private String docName;
	private String docVersion;
	private SVN svn;

	@PostConstruct
	public void log() {
		LogUtil.debug(this.getClass(), "Initial Doc Properties." + this.toString());
	}

	public String getRootParamPath() {
		return rootParamPath;
	}

	public void setRootParamPath(String rootParamPath) {
		this.rootParamPath = rootParamPath;
	}

	public String getRequestParamPath() {
		return requestParamPath;
	}

	public void setRequestParamPath(String requestParamPath) {
		this.requestParamPath = requestParamPath;
	}

	public String getResponseParamPath() {
		return responseParamPath;
	}

	public void setResponseParamPath(String responseParamPath) {
		this.responseParamPath = responseParamPath;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocVersion() {
		return docVersion;
	}

	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}

	public SVN getSvn() {
		return svn;
	}

	public void setSvn(SVN svn) {
		this.svn = svn;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("DocProperties{");
		sb.append("rootParamPath='").append(rootParamPath).append('\'');
		sb.append(", requestParamPath='").append(requestParamPath).append('\'');
		sb.append(", responseParamPath='").append(responseParamPath).append('\'');
		sb.append(", docName='").append(docName).append('\'');
		sb.append(", docVersion='").append(docVersion).append('\'');
		sb.append(", svn=").append(svn);
		sb.append('}');
		return sb.toString();
	}

	public static class SVN {

		private String name = "chenmm6";
		private String password = "123456";
		private String url;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@Override
		public String
		toString() {
			final StringBuilder sb = new StringBuilder("SVN{");
			sb.append("name='").append(name).append('\'');
			sb.append(", password='").append(password).append('\'');
			sb.append(", url='").append(url).append('\'');
			sb.append('}');
			return sb.toString();
		}
	}

}
