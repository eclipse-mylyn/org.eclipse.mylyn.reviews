/*******************************************************************************
 * Copyright (c) 2016 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.internal.gerrit.core.operations;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.internal.gerrit.core.client.GerritClient;
import org.eclipse.mylyn.internal.gerrit.core.client.GerritException;

import com.google.gerrit.common.data.ReviewerResult;

public class RemoveReviewerRequest extends AbstractRequest<ReviewerResult> {

	private final String reviewer;

	private final String reviewId;

	public RemoveReviewerRequest(String reviewId, String reviewer) {
		Assert.isNotNull(reviewId);
		Assert.isNotNull(reviewer);
		this.reviewId = reviewId;
		this.reviewer = reviewer;
	}

	public String getReviewerToRemove() {
		return this.reviewer;
	}

	public String getReviewId() {
		return this.reviewId;
	}

	@Override
	protected ReviewerResult execute(GerritClient client, IProgressMonitor monitor) throws GerritException {
		return client.removeReviewer(getReviewId(), getReviewerToRemove(), monitor);
	}

	@Override
	public String getOperationName() {
		return Messages.RemoveReviewerRequest_Remove_Reviewer;
	}

}
