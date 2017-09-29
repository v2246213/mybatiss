package com.ctmp01.web.controller.excel;

import com.ctmp01.web.util.excel.OutExcel;
import com.ctmp01.web.util.excel.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/6/9.
 */
@RestController
public class ExcelController {
    @Autowired
    private UserService userService;
    @Autowired
    private QiNiuService qiNiuService;
   /**
     * 导出用户信息Excel
     *
     * @return
     */
   @GetMapping("/download_project.do")
    public String download(HttpServletRequest request, HttpServletResponse response,UserPage userPage) throws IOException {
        String fileName = "user";
        //填充projects数据
        List<UserInfo> projects = createData(userPage);
        List<Map<String, Object>> list = createExcelRecord(projects);
        String columnNames[] = {"账号", "性别", "昵称", "注册时间", "生日", "标签"};//列名
        String keys[] = {"mobile", "gender", "nickname", "birtherDay", "age", "lable"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            OutExcel.createWorkBook(list, keys, columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"), "iso8859-1")+".xls");
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }
   private List<UserInfo> createData(UserPage userPage) {
        List<UserInfo> userList=userService.selectUserInfoList(userPage);
        return userList;
    }
    private List<Map<String, Object>> createExcelRecord(List<UserInfo> projects) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        UserInfo project=null;
        for (int j = 0; j < projects.size(); j++) {
            project=projects.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("mobile", project.getMobile());
            mapValue.put("gender", project.getGender());
            mapValue.put("nickname", project.getNickname());
            mapValue.put("birtherDay", project.getBirtherDay());
            mapValue.put("age", project.getAge());
            mapValue.put("lable", project.getLable());
            listmap.add(mapValue);
        }
        return listmap;
    }
   /* @ApiOperation("导入数据")
    @PostMapping("/ExcelReadX")
    public String ExcelReadX(@RequestBody String pat, String file) throws Exception {
       String pat1="http://resouce.dongdongwedding.com/";
        ExcelReadX er = new ExcelReadX();
        DataframeInDC dc = er.converter(file,"Sheet1");
        Map<String, ArrayList<Object>> dataMap = dc.getColValues();
        List<Object> nameList = dataMap.get("标签");
        List<String> strs = (List<String>)(List)nameList;
        //获取图片路径
        List<File> files = Arrays.asList(new File(pat).listFiles());
        Collections.sort(files, new Comparator< File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;

                return getFileName(o1.getName())-getFileName(o2.getName());
            }
        });
        List<String> lablesPath = files.stream()
                .map(File::getPath).collect(Collectors.toList());
        for (int i=0;i<nameList.size();i++)
        {
            UserLableInit userLable=new UserLableInit();
            String Label=pat1+qiNiuService.uplaodfile(lablesPath.get(i));
            //System.out.println(Label);
            userLable.setUserLableHeadurl(Label);
            Long time=System.currentTimeMillis();
            userLable.setCreateTime(time);
            userLable.setOne(0);
            userLable.setUserLableName(strs.get(i));
            userService.insert(userLable);
        }
        return "导入成功";
    }
    private static int getFileName(String fileName){
        String temp[] = fileName.split("\\\\");
        return Integer.parseInt(temp[temp.length-1].split("\\.")[0]);
    }*/
    }








