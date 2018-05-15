package tech.bilian.dto;

import java.util.List;

public class Execution<T> {
    private int state;

    //状态标识
    private  String stateInfo;

    //数量
    private int count;

    //模型
    private T model;

    //模型列表
    private List<T> models;



    //操作错误时返回的信息
    public Execution(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    //操作成功，只有一个返回结果
    public Execution(int state, String stateInfo, T model) {
        this.state = state;
        this.stateInfo = stateInfo;
        this.model = model;
        this.count = 1;
    }

    //操作成功，具有多个返回结果
    public Execution(int state, String stateInfo, List<T> models) {
        this.state = state;
        this.stateInfo = stateInfo;
        this.models = models;
        this.count = models.size();
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public List<T> getModels() {
        return models;
    }

    public void setModels(List<T> models) {
        this.models = models;
    }
}
