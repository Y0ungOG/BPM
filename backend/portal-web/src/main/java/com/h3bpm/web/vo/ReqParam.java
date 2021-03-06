package com.h3bpm.web.vo;

/**
 * Created by tonghao on 2020/3/1.
 */
public class ReqParam {
	private String fileId;

	// common
	private String mode;
	private String path;

	// list
	private boolean onlyFolders;

	// rename
	private String newPath;

	// edit
	private String content;

	// preview download
	private String preview;

	// create folder
	private String name;

	/**
	 * 文件权限
	 */
	private FilePermissionVo filePermission = null;

	/**
	 * 查询关键字
	 */
	private String keyword = null;

	public ReqParam() {
	}

	// list
	public ReqParam(String mode, boolean onlyFolders, String path) {
		this.mode = mode;
		this.onlyFolders = onlyFolders;
		this.path = path;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public FilePermissionVo getFilePermission() {
		return filePermission;
	}

	public void setFilePermission(FilePermissionVo filePermission) {
		this.filePermission = filePermission;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public boolean isOnlyFolders() {
		return onlyFolders;
	}

	public void setOnlyFolders(boolean onlyFolders) {
		this.onlyFolders = onlyFolders;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ReqParam{" + "mode='" + mode + '\'' + ", onlyFolders=" + onlyFolders + ", path='" + path + '\'' + '}';
	}
}
