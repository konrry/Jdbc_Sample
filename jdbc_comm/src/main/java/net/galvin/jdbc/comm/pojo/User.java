package net.galvin.jdbc.comm.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/7.
 */
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    enum SEX{

        MALE("男"),FEMALE("女");

        private String cName;

        SEX(String cName){
            this.cName = cName;
        }

    }

    @Override
    public String toString() {
        return "{ id="+id+",name="+name+",age="+age+",sex="+sex+" }";
    }
}
