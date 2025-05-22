package com.qbhy.poster.drawable.demo;

import lombok.Data;

import java.util.List;

@Data
public class DemoPoster {
    public String version;
    public List<DemoImage> objects;
    public DemoImage clipPath;
}
