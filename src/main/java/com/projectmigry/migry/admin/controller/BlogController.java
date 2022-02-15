package com.projectmigry.migry.admin.controller;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.projectmigry.migry.admin.common.FileService;
import com.projectmigry.migry.admin.common.NumPerPage;
import com.projectmigry.migry.admin.common.Utility;
import com.projectmigry.migry.admin.domain.Blog;
import com.projectmigry.migry.admin.domain.Member;
import com.projectmigry.migry.admin.domain.PageInfo;
import com.projectmigry.migry.admin.domain.Search;
import com.projectmigry.migry.admin.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/admin/blog/listBlog.do")
    public String listBlog(HttpServletRequest request, Model model) {
		int beginPerPage = 0;		// 시작 레코드 번호
		int numPerPage = NumPerPage.BLOG_LIST;		// 한 페이지당 보여줄 레코드 개수
		//int recNo = 0;					// 개시판 출력 글번호
		
        try {
        	Member loginMember = (Member)(request.getSession().getAttribute("login"));
            
            if(loginMember == null) {
            	return "redirect:/common/login.do?flag=U";
            } else if (!loginMember.getUserid().equals("administrator")) {
            	return "redirect:/common/login.do?flag=A";
            } else {
            	PageInfo pageinfo = Utility.getPageInfo(request);
            	
            	// 시작 레코드번호 값 부여
            	beginPerPage = pageinfo.getNowPage() * numPerPage;
            	
            	int startRow = beginPerPage + 1;
        		int endRow = beginPerPage + numPerPage;
        		
            	Search s = new Search(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), startRow, endRow, pageinfo.getCode());
            	List<Blog> list = blogService.getBlogList(s);
            	int count = blogService.countBlogList();
            	String url = "listBlog.do";
            	String paging = Utility.paging(pageinfo.getSearchColumn(), pageinfo.getSearchWord(), pageinfo.getNowPage(), pageinfo.getNowBlock(),
            						count, numPerPage, url, pageinfo.getCode());
            	
            	for(int i=0; i<list.size(); i++) {
            		Blog blog = list.get(i);
            		String fileName = blog.getFileName();
            		
            		if(fileName != null && !fileName.equals("")) {
    					String path = FileService.blogSavePath;
    					File f = new File(path + "/" + fileName);
    					
    					if(f == null || !f.exists()) {
    						path = request.getSession().getServletContext().getRealPath("/resources/images");
    						fileName = "noimage.png";
    						blog.setFileName(fileName);
    					}
    					
    					Image img = new ImageIcon(path + "/" + fileName).getImage();
    					int width = img.getWidth(null);			//가로 사이즈
    					int height = img.getHeight(null);		//세로 사이즈
    					
    					blog.setWidth(width);
    					blog.setHeight(height);
    					
    					if(width > 250) {
    						double ratio = (double)width / 250;
    						int resizeHeight = (int)(height / ratio);
    						
    						blog.setWidth(250);
        					blog.setHeight(resizeHeight);
    					}
    					
    					list.set(i, blog);
    				}
            	}
            	
	        	model.addAttribute("list", list);
	        	model.addAttribute("count", list.size());
	        	model.addAttribute("pageinfo", pageinfo);
	        	model.addAttribute("paging", paging);
	        	
	        	return "admin/blog/list";
            }
        } catch (Exception ex) {
        	System.out.println("Blog List 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
        }
    }
	
	@RequestMapping(value = "/admin/blog/insertBlog.do", method = RequestMethod.POST)
	public String insertBlog(@ModelAttribute Blog dto, HttpServletRequest request) {
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	return "redirect:/common/login.do?flag=A";
	        } else {
	        	String path = FileService.blogSavePath;
	        	MultipartFile uploadfile = dto.getUploadFile();
	        	
	        	if (uploadfile != null) {
	        		String fileName = uploadfile.getOriginalFilename();
	        		File file = FileService.getSaveFile(path, fileName);
	        		uploadfile.transferTo(file);
	        		
	        		dto.setFileName(file.getName());
	        		dto.setOriFileName(fileName);
	            }
	        	
	        	blogService.insertBlog(dto);
	           	return "redirect:listBlog.do";
	        }
		} catch(Exception ex) {
			System.out.println("Blog Insert 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
	
	@RequestMapping("/admin/blog/deleteBlog.do")
	public String deleteBlog(HttpServletRequest request) {
		try {
			Member loginMember = (Member)(request.getSession().getAttribute("login"));
			
			if(loginMember == null) {
				return "redirect:/common/login.do?flag=U";
	        } else if (!loginMember.getUserid().equals("administrator")) {
	        	return "redirect:/common/login.do?flag=A";
	        } else {
				int id = Integer.parseInt(request.getParameter("id"));
				Blog blog = blogService.getBlogInfo(id);
				String fileName = blog.getFileName();
				
				if(fileName != null && !fileName.equals("")) {
	        		String path = FileService.blogSavePath;
        			FileService.deleteFile(path, fileName);
        		}
				
				blogService.deleteBlog(id);
				return "redirect:listBlog.do";
	        }
		} catch(Exception ex) {
			System.out.println("Blog Delete 에러 : " + ex.toString());
        	ex.printStackTrace();
        	return "redirect:/common/error.do";
		}
	}
	
	@RequestMapping("/admin/blog/getImage.do")
	public void getImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		Blog blog = blogService.getBlogInfo(id);
		String filePath = FileService.blogSavePath;
		String fileName = blog.getFileName();
		String oriFileName = blog.getOriFileName();
		
		String realFile = filePath + "/" + fileName;
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

		BufferedOutputStream out = null;
		InputStream in = null;

		try {
			response.setContentType("image/" + ext);
			response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(oriFileName, "UTF-8"));
			
			File file = new File(realFile);
			
			if(file.exists()){
				in = new FileInputStream(file);
				out = new BufferedOutputStream(response.getOutputStream());
				int len;
				byte[] buf = new byte[1024];
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null){ out.flush(); }
			if(out != null){ out.close(); }
			if(in != null){ in.close(); }
		}
	}
}
