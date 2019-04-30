# Robot Server
imchat机器人demo
# BotApi
BotApi中包含机器人所有的Api

# BotServer
BotServer通过调用Api实现功能。

签到机器人（TextHandler）：通过接收imChat文类型消息，如“签到”(在setting.json可配置)，与设置关键字配匹，符合规则则签到成功，并发送红包。

新用户入群（NewChatMemberHandler）：接收新用户入群消息，可向该用户发送红包。

