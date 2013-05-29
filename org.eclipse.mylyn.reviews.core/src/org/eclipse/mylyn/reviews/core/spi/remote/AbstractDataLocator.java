/*******************************************************************************
 * Copyright (c) 2013 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.reviews.core.spi.remote;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public abstract class AbstractDataLocator {

	public abstract IPath getSystemPath();

	public IPath getFileScalingFragment(String fileName) {
		return new Path("");
	}

	public IPath getParentDir(String containerSegment, String typeFragment, String fileName) {
		IPath path = getSystemPath();
		path = path.append(containerSegment);
		path = path.append(typeFragment);
		path = path.append(getFileScalingFragment(fileName));
		return path;
	}

	public IPath getFilePath(String containerSegment, String typeFragment, String itemName, String artifactExtension) {
		IPath path = getParentDir(containerSegment, typeFragment, itemName);
		path = path.append(itemName);
		path = path.addFileExtension(artifactExtension);
		return path;
	}

	public IPath getObjectPath(String containerSegment, String typeFragment, String itemName) {
		IPath path = new Path(containerSegment).makeAbsolute();
		path = path.append(typeFragment);
		path = path.append(itemName);
		return path;
	}

	public IPath getFilePathFromObjectPath(IPath path) {
		return getFilePath(path.segment(0), path.segment(1), path.removeFileExtension().lastSegment(),
				path.getFileExtension());
	}

	public IPath getObjectPathFromFilePath(IPath path) {
		if (getSystemPath().isPrefixOf(path)) {
			String fragment = parseScalingFragment(path);
			path = path.makeRelativeTo(getSystemPath());
			path = path.removeFileExtension();
			String cleanPath = StringUtils.remove(path.toPortableString(), fragment);
			return new Path(cleanPath).makeAbsolute();
		}
		return path;
	}

	public IPath normalize(IPath path) {
		if (getSystemPath().isPrefixOf(path)) {
			path = path.makeRelativeTo(getSystemPath());

			return getFilePath(path.segment(0), path.segment(1), path.removeFileExtension().lastSegment(),
					path.getFileExtension());
		} else {
			return path;
		}
	}

	public String parseFileType(IPath path) {
		return path.segment(path.segmentCount() - 2);
	}

	public String parseContainerSegment(IPath path) {
		if (getSystemPath().isPrefixOf(path)) {
			path = path.makeRelativeTo(getSystemPath());
		}
		return path.segment(0);
	}

	public String parseScalingFragment(IPath path) {
		path = path.makeRelativeTo(getSystemPath());
		path = path.removeFirstSegments(2).removeFileExtension().removeLastSegments(1);
		return path.toString();
	}

	public String parseFileName(IPath path) {
		return path.removeFileExtension().lastSegment();
	}
}