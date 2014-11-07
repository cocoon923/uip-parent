package com.ailife.uip.doc.svn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by chenmm6 on 2014/11/4.
 */
@Service
public class DocSVNServiceImpl implements IDocSVNService {

	@Autowired
	private SVNClientManager svnClientManager;

	@Resource
	private SVNURL svnurl;

	@Resource(name = "destPath")
	private File destPath;

	@Resource(name = "filePath")
	private File filePath;

	@Override
	public void checkoutDoc() {
		SVNUtil.checkout(svnClientManager, svnurl, SVNRevision.HEAD, destPath, SVNDepth.EMPTY);
		SVNUtil.update(svnClientManager, filePath, SVNRevision.HEAD, SVNDepth.INFINITY);
	}

	@Override
	public void updateDoc() {
		SVNUtil.update(svnClientManager, filePath, SVNRevision.HEAD, SVNDepth.INFINITY);
	}

	@Override
	public void commitDoc() {

	}
}
