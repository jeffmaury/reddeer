/******************************************************************************* 
 * Copyright (c) 2016 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.reddeer.workbench.impl.editor;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitDocumentProvider.ProblemAnnotation;
import org.eclipse.ui.texteditor.SimpleMarkerAnnotation;

/**
 * Represents validation marker in editor.
 * @author rawagner
 */
@SuppressWarnings("restriction")
public class Marker {
    
	private String text;
	private String type;
	private int lineNumber;
	
	/**
	 * Default constructor needs SimpleAnnotationMarker to extract info.
	 * @param annotation SimpleAnnotationMarker to extract info from
	 */
	public Marker(SimpleMarkerAnnotation annotation){
		this.text = annotation.getText();
		this.type = annotation.getType();
		try {
			this.lineNumber = Integer.parseInt((annotation.getMarker().getAttribute(IMarker.LINE_NUMBER).toString()));
		} catch (Exception e){
			this.lineNumber = -1;
		}
	}
	
	/**
	 * Constructor used for AYT markers.
	 * @param annotation AYT marker annotation
	 * @param lineNumber line number where AYT marker is. Can't be extracted from annotation.
	 */
	public Marker(ProblemAnnotation annotation, int lineNumber){
		this.text = annotation.getText();
		this.type = annotation.getType();
		this.lineNumber = lineNumber;
	}

	/**
	 * Returns validation marker text.
	 * @return validation marker text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Returns validation marker type.
	 * @return validation marker type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns line number of validation marker.
	 * @return line number of validation marker
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lineNumber;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marker other = (Marker) obj;
		if (lineNumber != other.lineNumber)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Marker [text=" + text + ", type=" + type + ", lineNumber="
				+ lineNumber + "]";
	}	
	
}
