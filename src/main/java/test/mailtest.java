package test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailtest {
	 // 窟周繁議 喨�� 才 畜鷹��紋算葎徭失議喨�篋傭楝襭�
    // PS: 蝶乂喨�箏�暦匂葎阻奐紗喨�箟症軛楝覽聴家�來��公 SMTP 人薩極譜崔阻鏡羨畜鷹��嗤議喨�箜椴�＾娩幡鷹￣��,
    //     斤噐蝕尼阻鏡羨畜鷹議喨��, 宸戦議喨�簔楝覬慚菠荒稚盡�鏡羨畜鷹��娩幡鷹��。
    public static String myEmailAccount = "oozora_jimaku@163.com";
    public static String myEmailPassword = "HKDOGEAZWGTILNJL";
 
    // 窟周繁喨�箋� SMTP 捲暦匂仇峽, 駅倬彈鳩, 音揖喨周捲暦匂仇峽音揖, 匯違(峪頁匯違, 蒸掲蒸斤)鯉塀葎: smtp.xxx.com
    // 利叟126喨�箋� SMTP 捲暦匂仇峽葎: smtp.126.com
    public static String myEmailSMTPHost = "smtp.163.com";
 
    // 辺周繁喨�筍�紋算葎徭失岑祇議嗤丼喨�筍�
    public static String receiveMailAccount = "308611998@qq.com";
 
    public static void main(String[] args) throws Exception {
        // 1. 幹秀歌方塘崔, 喘噐銭俊喨周捲暦匂議歌方塘崔
        Properties props = new Properties();                    // 歌方塘崔
        props.setProperty("mail.transport.protocol", "smtp");   // 聞喘議亅咏��JavaMail号袈勣箔��
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 窟周繁議喨�箋� SMTP 捲暦匂仇峽
        props.setProperty("mail.smtp.auth", "true");            // 俶勣萩箔範屬
 
        // PS: 蝶乂喨�箏�暦匂勣箔 SMTP 銭俊俶勣聞喘 SSL 芦畠範屬 (葎阻戻互芦畠來, 喨�籌С�SSL銭俊, 匆辛參徭失蝕尼),
        //     泌惚涙隈銭俊喨周捲暦匂, 徙聾臥心陣崙岬嬉咫議 log, 泌惚嗤嗤窃貌 ＾銭俊払移, 勣箔 SSL 芦畠銭俊￣ 吉危列,
        //     函��和中 /* ... */ 岻寂議廣瞥旗鷹, 蝕尼 SSL 芦畠銭俊。
        /*
        // SMTP 捲暦匂議極笥 (掲 SSL 銭俊議極笥匯違潮範葎 25, 辛參音耶紗, 泌惚蝕尼阻 SSL 銭俊,
        //                  俶勣個葎斤哘喨�箋� SMTP 捲暦匂議極笥, 醤悶辛臥心斤哘喨�箏�暦議逸廁,
        //                  QQ喨�箋�SMTP(SLL)極笥葎465賜587, 凪麿喨�籃墅佝ゲ蘓�)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */
 
        // 2. 功象塘崔幹秀氏三斤��, 喘噐才喨周捲暦匂住札
        Session session = Session.getInstance(props);
        // 譜崔葎debug庁塀, 辛參臥心�袁元跳∨� log
        session.setDebug(true);
 
        // 3. 幹秀匯撃喨周
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);
 
        // 4. 功象 Session 資函喨周勧補斤��
        Transport transport = session.getTransport();
 
        // 5. 聞喘 喨�簽忘� 才 畜鷹 銭俊喨周捲暦匂, 宸戦範屬議喨�箟慚誡� message 嶄議窟周繁喨�簟志�, 倦夸烏危
        //
        //    PS_01: 泌惚銭俊捲暦匂払移, 脅氏壓陣崙岬補竃�獏κО榑�咀議log。
        //    徙聾臥心払移圻咀, 嗤乂喨�箏�暦匂氏卦指危列鷹賜臥心危列窃侏議全俊,
        //    功象公竃議危列窃侏欺斤哘喨周捲暦匂議逸廁利嫋貧臥心醤悶払移圻咀。
        //
        //    PS_02: 銭俊払移議圻咀宥械葎參和叱泣, 徙聾殊臥旗鷹:
        //           (1) 喨�簔屍仗�尼 SMTP 捲暦;
        //           (2) 喨�簔楝覺輓�, 箭泌蝶乂喨�篆�尼阻鏡羨畜鷹;
        //           (3) 喨�箏�暦匂勣箔駅倬勣聞喘 SSL 芦畠銭俊;
        //           (4) 萩箔狛噐撞訓賜凪麿圻咀, 瓜喨周捲暦匂詳蒸捲暦;
        //           (5) 泌惚參貧叱泣脅鳩協涙列, 欺喨周捲暦匂利嫋臥孀逸廁。
        //
        transport.connect(myEmailAccount, myEmailPassword);
 
        // 6. 窟僕喨周, 窟欺侭嗤議辺周仇峽, message.getAllRecipients() 資函欺議頁壓幹秀喨周斤�麒洩躰啜痛�嗤辺周繁, 貝僕繁, 畜僕繁
        transport.sendMessage(message, message.getAllRecipients());
 
        // 7. 購液銭俊
        transport.close();
    }
 
    /**
     * 幹秀匯撃峪淫根猟云議酒汽喨周
     *
     * @param session     才捲暦匂住札議氏三
     * @param sendMail    窟周繁喨��
     * @param receiveMail 辺周繁喨��
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 幹秀匯撃喨周
        MimeMessage message = new MimeMessage(session);
 
        // 2. From: 窟周繁
        message.setFrom(new InternetAddress(sendMail, "蠻各", "UTF-8"));
 
        // 3. To: 辺周繁�┸敏墺�紗謹倖辺周繁、貝僕、畜僕��
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX喘薩", "UTF-8"));
 
        // 4. Subject: 喨周麼籾
        message.setSubject("郭繁", "UTF-8");
 
        // 5. Content: 喨周屎猟�┸敏塋荒�html炎禰��
        message.setContent("‐まったり／おはようスバル‐�j��／厮丕\"", "text/html;charset=UTF-8");
            // 6. 譜崔窟周扮寂
        message.setSentDate(new Date());
 
        // 7. 隠贋譜崔
        message.saveChanges();
 
        return message;


}
}
