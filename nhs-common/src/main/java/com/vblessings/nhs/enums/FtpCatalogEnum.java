package com.vblessings.nhs.enums;

public enum FtpCatalogEnum {
    ROOT("/", "ftp根路径"),
    EXAM("/exam", "体检目录"),
    ;

    /**
     * 目录
     */
    private String catalog;

    /**
     * 描述
     */
    private String decs;

    FtpCatalogEnum(String catalog, String decs) {
        this.catalog = catalog;
        this.decs = decs;
    }

    public String getCatalog() {
        return catalog;
    }

    public String getDecs() {
        return decs;
    }
}
