package com.qbhy.poster.drawable.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemoImage {
    // 公共基础属性
    private String type;
    private String version;
    private String originX = "left";
    private String originY = "top";
    private Double left;
    private Double top;
    private Double width;
    private Double height;
    private String fill;
    private String stroke;
    private Double strokeWidth = 0.0;
    private List<Double> strokeDashArray;
    private String strokeLineCap = "butt";
    private Double strokeDashOffset = 0.0;
    private String strokeLineJoin = "miter";
    private Boolean strokeUniform = false;
    private Double strokeMiterLimit = 4.0;

    // 变换属性
    private Double scaleX = 1.0;
    private Double scaleY = 1.0;
    private Double angle = 0.0;
    private Boolean flipX = false;
    private Boolean flipY = false;

    // 渲染属性
    private Double opacity = 1.0;
    private Object shadow;
    private Boolean visible = true;
    private String backgroundColor;
    private String fillRule = "nonzero";
    private String paintFirst = "fill";
    private String globalCompositeOperation = "source-over";

    // 变形属性
    private Double skewX = 0.0;
    private Double skewY = 0.0;

    // 图像特有属性
    private Double cropX = 0.0;
    private Double cropY = 0.0;
    private String id;
    private Boolean selectable = true;
    private Boolean hasControls = true;
    private String src;                      // 图片地址
    private String crossOrigin;              // 跨域属性
    private List<Object> filters;            // 滤镜数组

    // 默认值保护：当JSON中值为null时保留Java默认值
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean isDefaultCompositeOperation() {
        return "source-over".equals(globalCompositeOperation);
    }
}