/*
 * RHQ Management Platform
 * Copyright (C) 2005-2008 Red Hat, Inc.
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License, version 2, as
 * published by the Free Software Foundation, and/or the GNU Lesser
 * General Public License, version 2.1, also as published by the Free
 * Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License and the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License
 * and the GNU Lesser General Public License along with this program;
 * if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.rhq.core.domain.content;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This is the composite primary key for the {@link RepoContentSource} entity. That entity is an explicit
 * many-to-many mapping table, so this composite key is simply the foreign keys to both ends of that relationship.
 *
 * @author John Mazzitelli
 */
public class RepoContentSourcePK implements Serializable {
    private static final long serialVersionUID = 1L;

    /*
     * http://opensource.atlassian.com/projects/hibernate/browse/EJB-286 Hibernate seems to want these mappings here,
     * even though this class is an @IdClass and it should not need the mappings here.  The mappings belong in the
     * entity itself.
     */

    @JoinColumn(name = "REPO_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private Repo repo;

    @JoinColumn(name = "CONTENT_SRC_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    private ContentSource contentSource;

    public RepoContentSourcePK() {
    }

    public RepoContentSourcePK(Repo repo, ContentSource contentSource) {
        this.repo = repo;
        this.contentSource = contentSource;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public ContentSource getContentSource() {
        return contentSource;
    }

    public void setContentSource(ContentSource contentSource) {
        this.contentSource = contentSource;
    }

    @Override
    public String toString() {
        return "RepoContentSourcePK: repo=[" + repo + "]; contentSource=[" + contentSource + "]";
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (31 * result) + ((repo == null) ? 0 : repo.hashCode());
        result = (31 * result) + ((contentSource == null) ? 0 : contentSource.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (!(obj instanceof RepoContentSourcePK))) {
            return false;
        }

        final RepoContentSourcePK other = (RepoContentSourcePK) obj;

        if (repo == null) {
            if (other.repo != null) {
                return false;
            }
        } else if (!repo.equals(other.repo)) {
            return false;
        }

        if (contentSource == null) {
            if (other.contentSource != null) {
                return false;
            }
        } else if (!contentSource.equals(other.contentSource)) {
            return false;
        }

        return true;
    }
}