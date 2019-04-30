package botapi.types;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Map;

/**
 * **************************************************************
 * <p>
 * Desc:
 * User: jianguangluo
 * Date: 2018-12-29
 * Time: 17:47
 * <p>
 * **************************************************************
 */
public class Message {
    //消息类型
    private int type;
    //消息ID
    @JSONField(name = "message_id")
    private String messageId;
    //发送人
    private User from;
    //发送时间
    private long date;
    //群信息
    private Chat chat;
    //@
    private List<MessageEntity> entities;
    //文本内容
    private String text;
    //图片内容
    private Photo photo;
    //新增入群
    @JSONField(name = "new_chat_members")
    private List<User> newChatMembers;
    //文件内容
    private File file;
    //红包
    @JSONField(name = "redpack")
    private RedPack redPack;
    //图文
    private Document document;
    //声音
    private Sound sound;
    //用户被踢出群
    @JSONField(name = "remove_chat_members")
    private List<User> removeChatMembers;
    //设置新群主
    @JSONField(name = "new_chat_owner")
    private User newChatOwner;
    //新增管理员
    @JSONField(name = "new_chat_assistant")
    private List<User> newChatAssistant;
    //删除管理员
    @JSONField(name = "remove_chat_assistant")
    private List<User> removeChatAssistant;
    //新增嘉宾
    @JSONField(name = "new_chat_vip")
    private List<User> newChatVip;
    //删除嘉宾
    @JSONField(name = "remove_chat_vip")
    private List<User> removeChatVip;
    //群主将某一个试用期成员转正
    @JSONField(name = "charge_chat_free")
    private User chargeChatFree;
    //群主将某一个试用期成员转正到期时间
    @JSONField(name = "charge_chat_free_expireTime")
    private Long chargeChatFreeExpireTime;
    //群主将某一个代付费成员延期
    @JSONField(name = "charge_chat_member")
    private User chargeGroupMember;
    //群主将某一个代付费成员延期到期时间
    @JSONField(name = "charge_chat_member_expireTime")
    private Long chargeChatMemberExpireTime;
    //群主将用户加入试用列表
    @JSONField(name = "new_chat_trail")
    private List<User> addToGroupTrail;
    //已经在群待付费列表
    @JSONField(name = "alredy_chat_waiting_pay")
    private List<User> alredyChatWaitingPay;
    //主动加入群试用
    @JSONField(name = "join_chat_trail")
    private User joinChatTrail;
    //主动加入群待支付
    @JSONField(name = "join_chat_waiting_pay")
    private User joinChatWaitingPay;
    //授权某人扮演自己
    @JSONField(name = "role_authorize")
    private User roleAuthorize;
    //取消授权某人扮演自己
    @JSONField(name = "cancel_role_authorize")
    private User cancelRoleAuthorize;
    //退出扮演其他人
    @JSONField(name = "quit_role_authorize")
    private User quitRoleAuthorize;

    //多语言
    private Map<String, Map<String, Object>> langs;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String message_id) {
        this.messageId = message_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<User> getNewChatMembers() {
        return newChatMembers;
    }

    public void setNewChatMembers(List<User> newChatMembers) {
        this.newChatMembers = newChatMembers;
    }

    public List<MessageEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<MessageEntity> entities) {
        this.entities = entities;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public RedPack getRedPack() {
        return redPack;
    }

    public void setRedPack(RedPack redPack) {
        this.redPack = redPack;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public List<User> getRemoveChatMembers() {
        return removeChatMembers;
    }

    public void setRemoveChatMembers(List<User> removeChatMembers) {
        this.removeChatMembers = removeChatMembers;
    }

    public User getNewChatOwner() {
        return newChatOwner;
    }

    public void setNewChatOwner(User newChatOwner) {
        this.newChatOwner = newChatOwner;
    }

    public List<User> getNewChatAssistant() {
        return newChatAssistant;
    }

    public void setNewChatAssistant(List<User> newChatAssistant) {
        this.newChatAssistant = newChatAssistant;
    }

    public List<User> getRemoveChatAssistant() {
        return removeChatAssistant;
    }

    public void setRemoveChatAssistant(List<User> removeChatAssistant) {
        this.removeChatAssistant = removeChatAssistant;
    }

    public List<User> getNewChatVip() {
        return newChatVip;
    }

    public void setNewChatVip(List<User> newChatVip) {
        this.newChatVip = newChatVip;
    }

    public List<User> getRemoveChatVip() {
        return removeChatVip;
    }

    public void setRemoveChatVip(List<User> removeChatVip) {
        this.removeChatVip = removeChatVip;
    }

    public User getChargeChatFree() {
        return chargeChatFree;
    }

    public void setChargeChatFree(User chargeChatFree) {
        this.chargeChatFree = chargeChatFree;
    }

    public Long getChargeChatFreeExpireTime() {
        return chargeChatFreeExpireTime;
    }

    public void setChargeChatFreeExpireTime(Long chargeChatFreeExpireTime) {
        this.chargeChatFreeExpireTime = chargeChatFreeExpireTime;
    }

    public User getChargeGroupMember() {
        return chargeGroupMember;
    }

    public void setChargeGroupMember(User chargeGroupMember) {
        this.chargeGroupMember = chargeGroupMember;
    }

    public Long getChargeChatMemberExpireTime() {
        return chargeChatMemberExpireTime;
    }

    public void setChargeChatMemberExpireTime(Long chargeChatMemberExpireTime) {
        this.chargeChatMemberExpireTime = chargeChatMemberExpireTime;
    }

    public List<User> getAddToGroupTrail() {
        return addToGroupTrail;
    }

    public void setAddToGroupTrail(List<User> addToGroupTrail) {
        this.addToGroupTrail = addToGroupTrail;
    }

    public Map<String, Map<String, Object>> getLangs() {
        return langs;
    }

    public void setLangs(Map<String, Map<String, Object>> langs) {
        this.langs = langs;
    }

    public List<User> getAlredyChatWaitingPay() {
        return alredyChatWaitingPay;
    }

    public void setAlredyChatWaitingPay(List<User> alredyChatWaitingPay) {
        this.alredyChatWaitingPay = alredyChatWaitingPay;
    }

    public User getJoinChatTrail() {
        return joinChatTrail;
    }

    public void setJoinChatTrail(User joinChatTrail) {
        this.joinChatTrail = joinChatTrail;
    }

    public User getJoinChatWaitingPay() {
        return joinChatWaitingPay;
    }

    public void setJoinChatWaitingPay(User joinChatWaitingPay) {
        this.joinChatWaitingPay = joinChatWaitingPay;
    }

    public User getRoleAuthorize() {
        return roleAuthorize;
    }

    public void setJoleAuthorize(User joleAuthorize) {
        this.roleAuthorize = joleAuthorize;
    }

    public User getCancelRoleAuthorize() {
        return cancelRoleAuthorize;
    }

    public void setCancelRoleAuthorize(User cancelRoleAuthorize) {
        this.cancelRoleAuthorize = cancelRoleAuthorize;
    }

    public User getQuitRoleAuthorize() {
        return quitRoleAuthorize;
    }

    public void setQuitRoleAuthorize(User quitRoleAuthorize) {
        this.quitRoleAuthorize = quitRoleAuthorize;
    }
}
