package net.liuchenfei.ssitest.vos;

import net.liuchenfei.ssitest.entitys.Directory;
import net.liuchenfei.ssitest.entitys.File;
import net.liuchenfei.ssitest.entitys.UserFile;

import java.util.List;

import static net.liuchenfei.ssitest.utils.CommonUtil.formateDate;

/**
 * Created by liuchenfei on 2017/10/27.
 */
public class FilesAndDirectoriesVO {

    private List<FileOrDirVO> files;
    private Directory directory;

    public FilesAndDirectoriesVO() {
    }

    public FilesAndDirectoriesVO(List<FileOrDirVO> files, Directory directory) {
        this.files = files;
        this.directory = directory;
    }

    public List<FileOrDirVO> getFiles() {
        return files;
    }

    public void setFiles(List<FileOrDirVO> files) {
        this.files = files;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public class FileOrDirVO {
        private String name;
        private String size;
        private Integer status;
        private Integer fileType;
        private boolean isSelect = false;
        private String updateTime;
        private String id;

        public FileOrDirVO(String name, String size, Integer status, Integer fileType, boolean isSelect, String updateTime, String id) {
            this.name = name;
            this.size = size;
            this.status = status;
            this.fileType = fileType;
            this.isSelect = isSelect;
            this.updateTime = updateTime;
            this.id = id;
        }

        public FileOrDirVO(Directory directory) {
            id = directory.getDirectoryId();
            fileType = 0;
            size = "-";
            name = directory.getDirectoryName();
            updateTime = formateDate(directory.getCreatetime());
            status = 0;
        }

        public FileOrDirVO(UserFile uf, File file) {
            id = uf.getId();
            fileType = file.getFileType();
            size = file.getFileSize() / 1024 + " kb";
            name = uf.getFileName();
            updateTime = formateDate(uf.getUploadTime());
            status = file.getFileStatus();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getFileType() {
            return fileType;
        }

        public void setFileType(Integer fileType) {
            this.fileType = fileType;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
