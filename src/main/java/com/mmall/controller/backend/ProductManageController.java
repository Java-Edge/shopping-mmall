package com.mmall.controller.backend;

import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.service.IFileService;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Shusheng Shi
 */

@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private IFileService iFileService;

    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(Product product) {
        return iProductService.saveOrUpdateProduct( product );
    }

    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(Integer productId, Integer status) {
        return iProductService.setSaleStatus( productId, status );
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(Integer productId) {
        return iProductService.manageProductDetail( productId );
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(@RequestParam(value="pageNum", defaultValue="1") int pageNum, @RequestParam(value="pageSize", defaultValue="10") int pageSize) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisSharedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
//
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            //填充业务
//            return iProductService.getProductList(pageNum,pageSize);
//        }else{
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        return iProductService.getProductList( pageNum, pageSize );
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse productSearch(String productName, Integer productId, @RequestParam(value="pageNum", defaultValue="1") int pageNum, @RequestParam(value="pageSize", defaultValue="10") int pageSize) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisSharedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//
//        if(user == null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
//
//        }
//        if(iUserService.checkAdminRole(user).isSuccess()){
//            //填充业务
//            return iProductService.searchProduct(productName,productId,pageNum,pageSize);
//        }else{
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        return iProductService.searchProduct( productName, productId, pageNum, pageSize );
    }

    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(@RequestParam(value="upload_file", required=false) MultipartFile file, HttpServletRequest request) {

        String path=request.getSession().getServletContext().getRealPath( "upload" );
        String targetFileName=iFileService.upload( file, path );
        String url=PropertiesUtil.getProperty( "ftp.server.http.prefix" ) + targetFileName;
        Map fileMap=Maps.newHashMap();
        fileMap.put( "uri", targetFileName );
        fileMap.put( "url", url );
        return ServerResponse.createBySuccess( fileMap );

    }

    /**
     * 富文本图片上传
     *
     * @param file
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richTextImgUpload(@RequestParam(value="upload_file", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map resultMap=Maps.newHashMap();

        String path=request.getSession().getServletContext().getRealPath( "upload" );
        String targetFileName=iFileService.upload( file, path );
        if (StringUtils.isBlank( targetFileName )) {
            resultMap.put( "success", false );
            resultMap.put( "msg", "上传失败" );
            return resultMap;
        }

        String url=PropertiesUtil.getProperty( "ftp.server.http.prefix" ) + targetFileName;
        resultMap.put( "success", true );
        resultMap.put( "msg", "上传成功" );
        resultMap.put( "file_path", url );
        response.addHeader( "Access-Control-Allow-Headers", "X-File-Name" );
        return resultMap;
    }


}
