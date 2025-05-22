package com.qbhy.poster.kernal;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 颜色转换工具类
 * @author qbhy
 */
public class ColorTools {
    /**
     * Color 转字符串
     *
     * @param color
     *
     * @return
     */
    public static String Color2String(Color color) {
        String R = Integer.toHexString(color.getRed());
        R = R.length() < 2 ? ('0' + R) : R;
        String B = Integer.toHexString(color.getBlue());
        B = B.length() < 2 ? ('0' + B) : B;
        String G = Integer.toHexString(color.getGreen());
        G = G.length() < 2 ? ('0' + G) : G;
        return '#' + R + B + G;
    }

    public static Color String2Color(String str, double opacity) {
        int i = Integer.parseInt(str.substring(1), 16);
        return new Color(i);
    }

    /**
     * 字符串转Color
     *
     * @param str
     *
     * @return
     */
    public static Color String2Color(String str) {
        if (str.startsWith("rgba(") || str.startsWith("rgb(")) {
            return parseGbaColor(str);
        }

        return ColorTools.String2Color(str, 1.0);
    }


    private static final Pattern GBAPattern = Pattern.compile(
            "^(rgba|gba)\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*([\\d.]+)\\s*\\)$",
            Pattern.CASE_INSENSITIVE
    );

    /**
     * 解析 gba/rgba 颜色字符串为 Color 对象
     * @param colorStr 颜色字符串，如 "gba(255, 255, 255, 1)"
     * @return 对应的 Color 对象
     * @throws IllegalArgumentException 格式错误或数值越界时抛出
     */
    public static Color parseGbaColor(String colorStr) {
        // 去除空格并统一小写处理
        String normalized = colorStr.replaceAll("\\s", "").toLowerCase();

        Matcher matcher = GBAPattern.matcher(normalized);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid color format: " + colorStr);
        }

        try {
            // 提取颜色分量
            int r = parseComponent(matcher.group(2), 0, 255);
            int g = parseComponent(matcher.group(3), 0, 255);
            int b = parseComponent(matcher.group(4), 0, 255);

            // 解析 alpha 值（支持整数和小数）
            float alpha = parseAlpha(matcher.group(5));
            int a = (int) (alpha * 255);

            return new Color(r, g, b, a);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format", e);
        }
    }

    /**
     * 校验颜色分量范围
     */
    private static int parseComponent(String value, int min, int max) {
        int component = Integer.parseInt(value);
        if (component < min || component > max) {
            throw new IllegalArgumentException(
                    String.format("Component value must be between %d and %d", min, max)
            );
        }
        return component;
    }

    /**
     * 校验 alpha 范围（0-1）
     */
    private static float parseAlpha(String value) {
        float alpha = Float.parseFloat(value);
        if (alpha < 0.0f || alpha > 1.0f) {
            throw new IllegalArgumentException("Alpha must be between 0.0 and 1.0");
        }
        return alpha;
    }


    public static void main(String[] args) {
        // 无空格测试
        testParse("rgba(255,255,255,1)");    // 合法
        testParse("gba(0,127,255,0.5)");     // 合法
        testParse("RGBA(255,0,0,0)");        // 合法（大小写混合）

        // 带空格测试
        testParse(" rgba ( 255 , 255 , 255 , 1 ) "); // 合法（前后空格）
    }

    private static void testParse(String colorStr) {
        try {
            Color color = parseGbaColor(colorStr);
            System.out.printf("解析成功：%-20s → ARGB(%3d, %3d, %3d, %3d)\n",
                    colorStr,
                    color.getAlpha(),
                    color.getRed(),
                    color.getGreen(),
                    color.getBlue()
            );
        } catch (Exception e) {
            System.err.println("解析失败：" + colorStr + " → " + e.getMessage());
        }
    }

}
