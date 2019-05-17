package enn.testone.entity;

import javax.persistence.*;
import java.io.Serializable;

public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer age;

    private String name;

    private String sex;

    private String bumen;

    public Employee() {

    }

    public Employee(Integer age, String name, String sex, String bumen) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.bumen = bumen;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return bumen
     */
    public String getBumen() {
        return bumen;
    }

    /**
     * @param bumen
     */
    public void setBumen(String bumen) {
        this.bumen = bumen;
    }

}