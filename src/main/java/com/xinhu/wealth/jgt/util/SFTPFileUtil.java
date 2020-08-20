package com.xinhu.wealth.jgt.util;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.*;

@Component
@Slf4j
public class SFTPFileUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    private ChannelSftp sftp = null;

    private Session sshSession = null;
    /**
     * FTP基础目录
     **/
    private String basePath = "/home/fis10/upload";

    /**
     * SFTP 登录用户名
     */
    private String username = "root";
    /**
     * SFTP 登录密码
     */
    private String password = "Yy@zgy2020_zxjgt";
    /**
     * SFTP 服务器地址IP地址
     */
    private String host = "47.98.89.145";
    /**
     * SFTP 端口
     */
    private int port = 3022;

    public SFTPFileUtil() {
    }

    public SFTPFileUtil(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * 连接sftp服务器,获取匹配路径下对应文件编号的所有文件
     *
     * @return ChannelSftp sftp连接实例
     */
    public ChannelSftp connect(String remoteFileDic, String fileNum, List<String> fileList) {
        logger.info("SFTP服务器连接：" + host + "：" + port + "：username = " + username);
        JSch jsch = new JSch();
        remoteFileDic=basePath;
        try {
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(properties);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            try {
                sftp.cd(remoteFileDic);
                // 获取列表下所有文件
                Vector<String> files = sftp.ls("*");
                for (int i = 0; i < files.size(); i++)
                {
                    Object obj = files.elementAt(i);
                    if (obj instanceof ChannelSftp.LsEntry)
                    {
                        ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) obj;
                        if (true && !entry.getAttrs().isDir())
                        {
                            // 匹配文件批次号
                            if (entry.getFilename().contains(fileNum)) {
                                fileList.add(entry.getFilename());
                            }
                        }
                        if (true && entry.getAttrs().isDir())
                        {
                            if (!entry.getFilename().equals(".") && !entry.getFilename().equals(".."))
                            {
                                // 匹配文件批次号
                                if (entry.getFilename().contains(fileNum)) {
                                    fileList.add(entry.getFilename());
                                }
                            }
                        }
                    }
                }
                //System.out.println("匹配文件列表：" + fileList.toString());
                log.info("匹配文件列表:{}",fileList.toString());
                //System.out.println("文件数量：" + fileList.size());
                log.info("文件数量:{}",fileList.size());
            } catch (Exception e) {

            }
            info(" SFTP 已连接： " + host + ":" + port);
        } catch (JSchException e) {
            throw new RuntimeException("SFTP连接失败", e);
        }
        return sftp;
    }

    /**
     * 下载文件
     *
     * @param remoteFileDic         远程文件路径
     * @param inputLocalFilePath    本地文件路径
     * @param fileNum               文件批次号
     * @return 下载的文件
     */
    public File[] downloadFile(String remoteFileDic, String fileNum,  String inputLocalFilePath) {
        logger.info("文件下载目标路径" + remoteFileDic);
        // fileList保留了对应SFTP路径下所匹配的文件批次所有文件
        List<String> fileList = new ArrayList<>();
        connect(remoteFileDic, fileNum, fileList);
        //存储base64流
        String remoteFileName = "";
        // 远端目录确定以 / 作为目录格式
        File file = null;
        //接收单个file
        File[] files=new File[ fileList.size()];
        OutputStream output = null;
        try {

            for(int i = 0; i < fileList.size(); i++) {
                // localFilePath 为本地路径 + 本地文件名，这里可以对fileList.get(i)处理切换成你们想要的本地文件名
                String localFilePath = inputLocalFilePath + fileList.get(i);
                file = new File(localFilePath);
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                // 切换到目标SFTP服务器路径
                sftp.cd(remoteFileDic);
                output = new FileOutputStream(file);
                // 下载文件批次号匹配上的文件
                sftp.get(fileList.get(i), output);
                info("文件" + fileList.get(i) + " 已下载！");
                //转base64流
                files[i]=file;
            }
        } catch (SftpException e) {
            error("SFTP下载文件失败", e);
            log.info("SFTP下载文件失败:{}",e);
        } catch (FileNotFoundException e) {
            error("本地目录异常，请检查" + file.getPath(), e);
            log.info("本地目录异常，请检查:{},{}",file.getPath(),e);
        } catch (IOException e) {
            error("创建本地文件失败" + file.getPath(), e);
            log.info("创建本地文件失败:{},{}",file.getPath(),e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            disconnect();
        }
        info("文件下载结束");
        return files;
    }

    /**
     * 从本地上传文件到指定SFTP服务器路径
     *
     * @param remoteFileDic     远程文件路径
     * @param uploadFileDic     要上传的文件路径
     */
    public void uploadFile(String remoteFileDic, String fileNum, String uploadFileDic) {
        info(" 开始从" + uploadFileDic +
                "上传至: " + remoteFileDic);
        FileInputStream in = null;
        // 这里的fileList是从SFTP获取的文件名列表，但是如果是上传应该需要从本地uploadFileDic的文件名列表
        // 因为之前从服务器下载了文件，文件名又是一样的，所以暂时用SFTP那边的文件名
        List<String> fileList = new ArrayList<>();
        // 这里只需要connect，上传可以不需要fileNum和fileList
        connect(remoteFileDic, fileNum, fileList);
        try {
            sftp.cd(remoteFileDic);
        } catch (SftpException e) {
            try {
                sftp.mkdir(remoteFileDic);
                sftp.cd(remoteFileDic);
            } catch (SftpException e1) {
                error("SFTP创建文件路径失败，路径为" + remoteFileDic);
                throw new RuntimeException("SFTP创建文件路径失败" + remoteFileDic);
            }
        }

        try {
            for(int i = 0; i < fileList.size(); i++) {
                // 获取本地文件进行上传，你可以指定上传后植信需要的文件名
                File file = new File(uploadFileDic + fileList.get(i));
                in = new FileInputStream(file);
                sftp.put(in, "从本地上传到SFTP的" +  fileList.get(i));
            }
        } catch (FileNotFoundException e) {
            error("文件不存在");
        } catch (SftpException e) {
            error("SFTP异常：", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    info("Close stream error." + e.getMessage());
                }
            }
            disconnect();
        }
        info("上传文件结束");
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
                this.sftp = null;
                info("SFTP 连接已关闭！");
            }
        }
        if (this.sshSession != null) {
            if (this.sshSession.isConnected()) {
                this.sshSession.disconnect();
                this.sshSession = null;
                info("SSH Session 连接已关闭！");
            }
        }
    }
    private void info(String msg) {
        System.out.println("INFO：" + msg);
    }

    private void error(String msg) {
        error(msg, null);
    }

    private void error(String msg, Throwable e) {
        System.out.println("ERROR： " + msg);
        if(e != null) {
            e.printStackTrace();
        }
    }



    /**
     * 转换为base64流
     *  返回map
     *  key 是字节流
     *  value 是文件的类型
     *
     * @return
     * @throws IOException
     */
    public Map<String,String> changeBase642(File[] files){
        String encode ="";
        Map<String,String> map=new HashMap<>();
        try {
            for (int i = 0; i <files.length ; i++) {
                File file = files[i];
                if (file!=null){
                    String name = file.getName();
                    //文件转换成流 按照植信的规则转换64
                    FileInputStream inputFile = new FileInputStream(file);
                    MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputFile);
                    //转换成64
                    encode = fileToBase64(multipartFile);
                    //获取文件的 后缀类型
                    int lastIndexOf = name.lastIndexOf(".");
                    String value= name.substring(lastIndexOf,name.length());
                    map.put(encode,value);
                }
            }
        } catch (IOException e) {
            logger.error("文件转换异常" + e);
        }
        return map;
    }
    /**
     * base64字节处理
     * @param file
     * @return
     */
    public static String fileToBase64(MultipartFile file) {
        String result = null;
        try {
            result = Base64.encodeBase64String(file.getBytes()).trim();//转换成base64串
            result = result.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 转换为base64流
     * @return
     * @throws IOException
     */
    public Map<String,String> changeBase64(File[] files){
        String encode ="";
        Map<String,String> map=new HashMap<>();

        try {
            for (int i = 0; i <files.length ; i++) {
                FileInputStream inputFile = new FileInputStream(files[i]);
                File file = files[i];
                String name = file.getName();
                String[] split = name.split("\\.");
                String value = split[1];
                byte[] buffer = new byte[(int) files[i].length()];
                inputFile.read(buffer);
                inputFile.close();
                encode = new BASE64Encoder().encode(buffer);
                map.put(encode,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}