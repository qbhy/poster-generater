# poster-generater
⚡⚡⚡海报生成器. 只需要一个简单的 json 配置即可生成你需要的海报...

## 相关文档
[使用文档](https://github.com/qbhy/poster-generater/wiki)  
[常见问题](faq.md)

## 新版功能更丰富
* 基于 java 开发，部署和二次开发更方便
* 图片将上传到 公共 CDN，不占用主机磁盘，且速度更快
* 支持结果缓存，相同的 海报配置 不会重复渲染，一次渲染持续保存，速度更快
* 添加删除结果 API
* 支持自定义字体，运行目录下新建 fonts 文件夹，里面放 ttf 格式字体就行。
* 支持模板图片，减少网络图片加载，运行目录下新建 templates 文件夹，支持多种图片格式。
* 缓存网络图片，减少网络图片加载
* 支持企业定制化开发部署，详情请联系我
> ps：自定义字体、模板图片、网络图片缓存路径均可配置。具体配置参考 [example.application.properties](https://github.com/qbhy/poster-generater/blob/master/example.application.properties)

## 压测
```bash
$ wrk -c 1000 -t 4 -s wrk.lua http://127.0.0.1:8000/wrk
```
结果如下
```
Running 10s test @ http://127.0.0.1:8000/wrk
  4 threads and 1000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   763.65ms  530.89ms   2.00s    61.96%
    Req/Sec   177.08     78.36   435.00     68.26%
  5756 requests in 10.06s, 2.22MB read
  Socket errors: connect 0, read 890, write 3, timeout 106
Requests/sec:    572.28
Transfer/sec:    225.82KB
```

## 组件参数解释

### config字段

| 字段            | 类型                     | 必填 | 描述                                       |
| --------------- | ------------------------ | ---- | ------------------------------------------ |
| width           | Number(单位:px)          | 是   | 画布宽度                                   |
| height          | Number(单位:px)          | 是   | 画布高度                                   |
| backgroundColor | String                   | 否   | 画布颜色                                   |
| blocks          | Object Array（对象数组） | 否   | 看下文                                     |
| texts           | Object Array（对象数组） | 否   | 看下文                                     |
| images          | Object Array（对象数组） | 否   | 看下文                                     |
| lines           | Object Array（对象数组） | 否   | 看下文                                     |

### blocks字段

| 字段名          | 类型             | 必填 | 描述                                   |
| --------------- | ---------------- | ---- | -------------------------------------- |
| x               | Number(单位:px) | 是   | 块的坐标                               |
| y               | Number(单位:px) | 是   | 块的坐标                               |
| width           | Number(单位:px) | 否   | 如果内部有文字，由文字宽度和内边距决定 |
| height          | Number(单位:px) | 是   |                                        |
| paddingLeft     | Number(单位:px) | 否   | 内左边距                               |
| paddingRight    | Number(单位:px) | 否   | 内右边距                               |
| borderWidth     | Number(单位:px) | 否   | 边框宽度                               |
| borderColor     | String           | 否   | 边框颜色                               |
| backgroundColor | String           | 否   | 背景颜色                               |
| borderRadius    | Number(单位:px) | 否   | 圆角                                   |
| text            | Object           | 否   | 块里面可以填充文字，参考texts字段解释  |
| index          | Int              | 否   | 层级，越大越高                         |

### texts字段

| 字段名         | 类型             | 必填 | 描述                                                         |
| -------------- | ---------------- | ---- | ------------------------------------------------------------ |
| x              | Number(单位:px) | 是   | 坐标                                                         |
| y              | Number(单位:px) | 是   | 坐标                                                         |
| text           | String\|Object   | 是   | 当Object类型时，参数为text字段的参数，marginLeft、marginRight这两个字段可用（示例请看下文） |
| fontSize       | Number(单位:px) | 是   | 文字大小                                                     |
| color          | String           | 否   | 颜色                                                         |
| lineHeight     | Number(单位:px) | 否   | 行高                                                         |
| lineNum        | Int              | 否   | 根据宽度换行，最多的行数                                     |
| width          | Number(单位:px) | 否   | 没有指定为画布宽度，默认为x轴右边所有宽度                                           |
| baseLine       | String           | 否   | top\| middle\|bottom基线对齐方式                             |
| textAlign      | String           | 否   | left\|center\|right对齐方式                                  |
| index         | Int              | 否   | 层级，越大越高                                               |
| font          | String           | 否   | 默认字体为'pingfangtf' ，支持自定义字体      |

### images字段

| 字段         | 类型             | 必填 | 描述                       |
| ------------ | ---------------- | ---- |--------------------------|
| x            | Number(单位:px) | 是   | 右上角的坐标                   |
| y            | Number(单位:px) | 是   | 右上角的坐标                   |
| url          | String           | 是   | 图片url，支持base64、本地图片   |
| width        | Number(单位:px) | 是   | 宽度    |
| height       | Number(单位:px) | 是   | 高度    |
| borderRadius | Number(单位:px) | 否   | 圆角，跟css一样                |
| index       | Int              | 否   | 层级，越大越高                  |
| qrCode       | Bool              | 否   | 是否二维码图片，如果是，url内容就是二维码内容 |

### lines字段

| 字段   | 类型             | 必填 | 描述           |
| ------ | ---------------- | ---- | -------------- |
| startX | Number(单位:px) | 是   | 起始坐标       |
| startY | Number(单位:px) | 是   | 起始坐标       |
| endX   | Number(单位:px) | 是   | 终结坐标       |
| endY   | Number(单位:px) | 是   | 终结坐标       |
| width  | Number(单位:px) | 是   | 线的宽度       |
| color  | String           | 否   | 线的颜色       |
| index | Int              | 否   | 层级，越大越高 |

## 赞赏
如果你认为我的代码对你有帮助... 好吧我看他们都放，我也跟着放了
![1141628692038_.pic.jpg](https://i.loli.net/2021/08/11/QEX2nZIf3Aa8hdJ.jpg)

进群请加微信: qbhy0715  
qbhy0715@qq.com  
[qbhy/poster-generater](https://github.com/qbhy/poster-generater)
