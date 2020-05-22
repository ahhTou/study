package U2_2_fileUpload.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller("UserController2")
@RequestMapping("/day2_2/user")
public class UserController {
    /**
     * 文件上传
     */

    @RequestMapping("/fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传...");

        // 使用fileupload组件完成文件上传
        // 上传的位置
        String path = request
                .getSession()
                .getServletContext()
                .getRealPath("/uploads/");
        // 判断你该路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            System.out.println("文件创建是否成功 ->" + mkdirs);
        }

        //解析request对象，获取上传文件想
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(file);
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 解析request
        List<FileItem> fileItems = upload.parseRequest(request);
        // 遍历
        fileItems.forEach(fileItem -> {
            // 进行判断，当前item对象是否为上传文件项
            if (fileItem.isFormField()) {
                //说明普通表单项
                System.out.println("非文件项");
            } else {
                // 说明为上传文件项
                // 获取上传文件的名称
                String filename = fileItem.getName();
                // 把文件名称设置为唯一值，uuid
                String uuid = UUID.randomUUID().toString();
                filename = uuid + '_' + filename;

                // 完成文件上传
                try {
                    System.out.println("找到文件项，开始写入...");
                    fileItem.write(new File(
                            path,
                            filename
                    ));
                    System.out.println("上传路径为 -> " + path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 删除临时文件
                fileItem.delete();

            }
        });

        return "success";
    }

    /**
     * springMVC文件上传
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileUpload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile upload2) throws Exception {
        System.out.println("SpringMVC文件上传...");

        // 使用fileupload组件完成文件上传
        // 上传的位置
        String path = request
                .getSession()
                .getServletContext()
                .getRealPath("/uploads/");
        // 判断你该路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            System.out.println("文件创建是否成功 ->" + mkdirs);
        }

        // 获取上传文件的名称
        String filename = upload2.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        filename = uuid + '_' + filename;

        // 完成文件上传
        upload2.transferTo(new File(path,filename));

        return "success";
    }

    /**
     * 跨服务器文件上传
     *
     * @para
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileUpload3")
    public String fileUpload3(MultipartFile upload3) throws Exception {
        System.out.println("跨服务器文件上传...");

        // 定义上传文件服务器路径
        String path = "http://localhost:9090/uploads/";


        // 获取上传文件的名称
        String filename = upload3.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        filename = uuid + '_' + filename;


        // 创建客服端对象
        Client client = Client.create();

        // 和图片服务器进行连接
        WebResource webResource = client.resource(path + filename);

        // 上传文件
        webResource.put(upload3.getBytes());


        return "success";
    }
}
