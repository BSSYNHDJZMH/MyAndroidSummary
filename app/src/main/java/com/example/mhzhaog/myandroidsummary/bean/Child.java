package com.example.mhzhaog.myandroidsummary.bean;

import java.util.List;

public class Child {
    private String name ;
    private String id;
    private int isCamera; //1区域 0摄像机
    private List<Child> childs;
    private String parentId;

    public static final int ITEM_TYPE_PARENT = 0;
    public static final int ITEM_TYPE_CHILD = 1;

    private String uuid;

    private int type;// 显示类型

    private int treeDepth = 0;// 路径的深度
    private boolean expand;// 是否展开

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTreeDepth() {
        return treeDepth;
    }

    public void setTreeDepth(int treeDepth) {
        this.treeDepth = treeDepth;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsCamera(int isCamera) {
        this.isCamera = isCamera;
    }
    public int getIsCamera() {
        return isCamera;
    }

    public void setChild(List<Child> childs) {
        this.childs = childs;
    }
    public List<Child> getChild() {
        return childs;
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", isCamera=" + isCamera +
                ", childs=" + childs +
                '}';
    }
}
