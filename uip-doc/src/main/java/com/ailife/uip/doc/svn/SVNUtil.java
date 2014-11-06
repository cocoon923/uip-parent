package com.ailife.uip.doc.svn;

import com.ailife.uip.core.util.LogUtil;
import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.*;

import java.io.File;

public class SVNUtil {

	public static void setupLibrary() {
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		FSRepositoryFactory.setup();
	}

	public static SVNURL initialSVNURL(String url) {
		try {
			return SVNURL.parseURIEncoded(url);
		} catch (Exception e) {
			LogUtil.error(SVNUtil.class, e);
		}
		return null;
	}

	public static SVNClientManager authSvn(String svnRoot, String username, String password) {

		setupLibrary();

		SVNRepository repository;
		try {
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(svnRoot));
		} catch (SVNException e) {
			LogUtil.error(SVNUtil.class, e);
			return null;
		}

		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);

		repository.setAuthenticationManager(authManager);

		DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
		return SVNClientManager.newInstance(options, authManager);
	}

	public static SVNCommitInfo makeDirectory(SVNClientManager clientManager, SVNURL url, String commitMessage) {
		try {
			return clientManager.getCommitClient().doMkDir(new SVNURL[]{url}, commitMessage);
		} catch (SVNException e) {
			LogUtil.error(SVNUtil.class, e);
		}
		return null;
	}

	public static SVNCommitInfo importDirectory(SVNClientManager clientManager, File localPath, SVNURL dstURL, String commitMessage, boolean isRecursive) {
		try {
			return clientManager.getCommitClient().doImport(localPath, dstURL, commitMessage, null, true, true, SVNDepth.fromRecurse(isRecursive));
		} catch (SVNException e) {
			LogUtil.error(SVNUtil.class, e);
		}
		return null;
	}

	public static void addEntry(SVNClientManager clientManager, File wcPath) {
		try {
			clientManager.getWCClient().doAdd(new File[]{wcPath}, true, false, false, SVNDepth.INFINITY, false, false, true);
		} catch (SVNException e) {
			LogUtil.error(SVNUtil.class, e);
		}
	}

	public static SVNStatus showStatus(SVNClientManager clientManager, File wcPath, boolean remote) {
		SVNStatus status = null;
		try {
			status = clientManager.getStatusClient().doStatus(wcPath, remote);
		} catch (SVNException e) {
			LogUtil.error(SVNUtil.class, e);
		}
		return status;
	}

	public static SVNCommitInfo commit(SVNClientManager clientManager, File wcPath, boolean keepLocks, String commitMessage) {
		try {
			return clientManager.getCommitClient().doCommit(new File[]{wcPath}, keepLocks, commitMessage, null, null, false, false, SVNDepth.INFINITY);
		} catch (SVNException e) {
			LogUtil.error(SVNUtil.class, e);
		}
		return null;
	}

	public static long update(SVNClientManager clientManager, File wcPath, SVNRevision updateToRevision, SVNDepth depth) {

		SVNUpdateClient updateClient = clientManager.getUpdateClient();
		/*
		 * sets externals not to be ignored during the update
		 */
		updateClient.setIgnoreExternals(false);

		/*
		 * returns the number of the revision wcPath was updated to
		 */
		try {
			return updateClient.doUpdate(wcPath, updateToRevision, depth, false, false);
		} catch (SVNException e) {
			LogUtil.error(SVNUtil.class, e);
		}
		return 0;
	}

	public static long checkout(SVNClientManager clientManager, SVNURL url, SVNRevision revision, File destPath, SVNDepth depth) {

		SVNUpdateClient updateClient = clientManager.getUpdateClient();
		/*
		 * sets externals not to be ignored during the checkout
		 */
		updateClient.setIgnoreExternals(false);
		/*
		 * returns the number of the revision at which the working copy is
		 */
		try {
			return updateClient.doCheckout(url, destPath, revision, revision, depth, false);
		} catch (SVNException e) {
			LogUtil.error(SVNUtil.class, e);
		}
		return 0;
	}

	public static boolean isWorkingCopy(File path) {
		if (!path.exists()) {
			LogUtil.error(SVNUtil.class, "'" + path + "' not exist!");
			return false;
		}
		try {
			if (null == SVNWCUtil.getWorkingCopyRoot(path, false)) {
				return false;
			}
		} catch (SVNException e) {
			LogUtil.error(SVNUtil.class, e);
		}
		return true;
	}

	public static boolean isURLExist(SVNURL url, String username, String password) {
		try {
			SVNRepository svnRepository = SVNRepositoryFactory.create(url);
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
			svnRepository.setAuthenticationManager(authManager);
			SVNNodeKind nodeKind = svnRepository.checkPath("", -1);
			return nodeKind != SVNNodeKind.NONE;
		} catch (SVNException e) {
			LogUtil.error(SVNUtil.class, e);
		}
		return false;
	}

}