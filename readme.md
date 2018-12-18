# poster-generater
海报生成器

## 安装
```bash
git clone https://github.com/qbhy/poster-generater.git
cd poster-generater
go build
```

## 启动
```
./poster-generater {port:7877}
```

## 使用
```http request
POST /poster HTTP/1.1
Host: 127.0.0.1:7877
Content-Type: application/json
cache-control: no-cache
Postman-Token: eb879967-e34b-4144-b4fb-1dd90961e155
{
    "width": 640,
    "height": 1034,
    "backgroundColor": "#d04c44",
    "blocks": [
        {
            "x": 25,
            "y": 25,
            "width": 590,
            "height": 820,
            "borderColor": "#ffe6c0",
            "borderWidth": 2
        },
        {
            "x": 0,
            "y": 870,
            "width": 640,
            "height": 164,
            "backgroundColor": "#fff"
        },
        {
            "x": 67,
            "y": 303,
            "width": 506,
            "height": 500,
            "backgroundColor": "#fff"
        }
    ],
    "texts": [
        {
            "text": "桥边红药",
            "x": 320,
            "y": 187,
            "fontSize": 18,
            "lineHeight": 18,
            "color": "#ffe6c0",
            "width": 320,
            "lineNum": 1,
            "baseLine": "middle",
            "textAlign": "center"
        },
        {
            "text": "这个人很懒~",
            "x": 320,
            "y": 225,
            "fontSize": 20,
            "lineHeight": 24,
            "color": "#ffe6c0",
            "width": 480,
            "lineNum": 2,
            "baseLine": "middle",
            "textAlign": "center"
        },
        {
            "text": "微信内长按图片识别小程序来「 抽奖助手 」看看吧",
            "x": 170,
            "y": 923,
            "fontSize": 18,
            "color": "#999",
            "width": 300,
            "lineNum": 2,
            "baseLine": "middle",
            "zIndex": 8,
            "lineHeight": 40
        }
    ],
    "images": [
        {
            "url": "https://i.loli.net/2018/12/18/5c186d195b3b9.png",
            "x": 270,
            "y": 67,
            "width": 100,
            "height": 100,
            "borderRadius": 100
        },
        {
            "url": "https://i.loli.net/2018/12/18/5c185a49b48ee.png",
            "x": 87,
            "y": 323,
            "width": 466,
            "height": 460
        },
        {
            "url": "https://services.janguly.com/wx-app/code?aid=photo&page=/pages/me/me",
            "x": 45,
            "y": 905,
            "width": 100,
            "height": 100,
            "zIndex": 9
        }
    ],
    "lines": []
}------WebKitFormBoundary7MA4YWxkTrZu0gW--
```

96qbhy@gmail.com