package org.jboss.reddeer.eclipse.jdt.ui.packageexplorer;

import org.apache.log4j.Logger;
import org.jboss.reddeer.swt.api.TreeItem;
import org.jboss.reddeer.swt.condition.JobsAreNotActive;
import org.jboss.reddeer.swt.condition.ShellWithTextIsActive;
import org.jboss.reddeer.swt.impl.button.PushButton;
import org.jboss.reddeer.swt.impl.menu.ContextMenu;
import org.jboss.reddeer.swt.impl.shell.DefaultShell;
import org.jboss.reddeer.swt.util.Jobs;
import org.jboss.reddeer.swt.wait.WaitUntilCondition;
import org.jboss.reddeer.swt.wait.WaitWhileCondition;

/**
 * Represents a project item of {@link Project}.
 * 
 * @author Vlado Pakan
 * 
 */
public class ProjectItem {
	protected final Logger log = Logger.getLogger(ProjectItem.class);
	
	private TreeItem treeItem;
	private Project project;
	private String[] path;
	

	public ProjectItem(TreeItem treeItem, Project project, String... path) {
		this.treeItem = treeItem;
		this.path = path;
		this.project = project;
	}

	public void open() {
		select();
		treeItem.doubleClick();
	}

	public void delete(boolean deleteFromFileSystem) {
		select();
        log.debug("Delete project item " + treeItem.getText() + " via Package Explorer");
	    new ContextMenu("Delete").select();
		DefaultShell shell = new DefaultShell("Confirm Delete");
		new PushButton("OK").click();
		new WaitWhileCondition(new ShellWithTextIsActive(shell.getText()), 10000);
		new WaitUntilCondition(new JobsAreNotActive(Jobs.BUILDING_WORKSPACE_JOB,
          Jobs.COMPACTING_RESOURCE_MODEL,
		  Jobs.LOADING_JOB),
		  30000);
	}

	public void select() {
		TreeItem item = project.getTreeItem();
		int index = 0;
		while (index < path.length){
			item = item.getItem(path[index]);
			index++;
		}
		item.select();
	}
	
	public String[] getPath() {
		return path;
	}
	
	public Project getProject() {
		return project;
	}
	
	public boolean isSelected(){
		return treeItem.isSelected();
	}
	
	public ProjectItem getChild (String text){
		String[] childPath = new String[path.length + 1];
		System.arraycopy(path, 0, childPath, 0, path.length);
		childPath[childPath.length - 1] = text;
		return new ProjectItem(treeItem.getItem(text), project, path);
	}
}