/*******************************************************************************
 * Copyright (c) 2015 Vaughan Hilts and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Vaughan Hilts - Initial implementation
 *******************************************************************************/

package org.eclipse.mylyn.reviews.internal.core;

public class BuildResult {

	public enum BuildStatus {
		NOT_BUILT, STARTED, SUCCESS, UNSTABLE, FAILURE, ABORTED
	}

	private final int buildNumber;

	private final String buildUrl;

	private final BuildStatus buildStatus;

	private final int patchSetNumber;

	private final String jobName;

	public BuildResult(int buildNumber, String buildUrl, BuildStatus buildStatus, int patchSetNumber, String jobName) {
		this.buildNumber = buildNumber;
		this.buildUrl = buildUrl;
		this.buildStatus = buildStatus;
		this.patchSetNumber = patchSetNumber;
		this.jobName = jobName;
	}

	public int getBuildNumber() {
		return this.buildNumber;
	}

	public String getBuildUrl() {
		return this.buildUrl;
	}

	public BuildStatus getBuildStatus() {
		return this.buildStatus;
	}

	public int getPatchSetNumber() {
		return patchSetNumber;
	}

	public String getJobName() {
		return jobName;
	}
}
