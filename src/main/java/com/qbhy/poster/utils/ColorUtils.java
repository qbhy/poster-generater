package com.qbhy.poster.utils;
// 颜色处理工具类
public class ColorUtils {
    /**
     * 将简写的十六进制颜色代码扩展为完整格式
     * 示例：
     * #fff → #ffffff
     * #1234 → #11223344
     * 无效格式直接返回原值
     */
    public static String expandHexColor(String color) {
        if (color == null || color.isEmpty()) {
            return color;
        }

        // 验证颜色格式并扩展
        if (color.startsWith("#")) {
            String hexPart = color.substring(1);
            int len = hexPart.length();

            // 处理简写格式（3位RGB或4位RGBA）
            if (len == 3 || len == 4) {
                StringBuilder expanded = new StringBuilder();
                for (char c : hexPart.toCharArray()) {
                    expanded.append(c).append(c);
                }
                return "#" + expanded;
            }
        }

        // 其他情况直接返回原值（包括6位、8位、无效格式）
        return color;
    }
}
