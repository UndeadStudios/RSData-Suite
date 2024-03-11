package com.suite.cachelib.cache;


public class FilesContainer extends CacheContainer {
    private int[] filesIndexes;
    private CacheContainer[] files;

    public FilesContainer() {
    }

    public void setFiles(CacheContainer[] containers) {
        this.files = containers;
    }

    public CacheContainer[] getFiles() {
        return this.files;
    }

    public void setFilesIndexes(int[] containersIndexes) {
        this.filesIndexes = containersIndexes;
    }

    public int[] getFilesIndexes() {
        return this.filesIndexes;
    }
}
