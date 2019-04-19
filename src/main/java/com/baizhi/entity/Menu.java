package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    private Integer id;
    private String title;
    private String iconCls;
    private Integer parentId;
    private String jspUrl;
    private List<Menu> menus;

}
