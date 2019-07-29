package stu.aewnfin.service;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import stu.aewnfin.dao.ChapterDao;
import stu.aewnfin.dao.ComicDao;
import stu.aewnfin.dao.PageDao;
import stu.aewnfin.dao.PublishDao;
import stu.aewnfin.entity.Author;
import stu.aewnfin.entity.Chapter;
import stu.aewnfin.entity.Comic;
import stu.aewnfin.entity.Publish;
import stu.aewnfin.util.NoteResult;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {
	@Resource
	private ComicDao comicDao;
	@Resource
	private ChapterDao chapterDao;
	@Resource
	private PageDao pageDao;
	@Resource
	private PublishDao publishDao;
	
	// 文件存放路径
	private static final String STOREPATH="D:\\eclipse-jee-workspace\\comic001\\src\\main\\webapp\\comic";

	// 尝试提交新漫画
	public NoteResult<Comic> sameComic(String comicTitle,MultipartFile file,Integer id,String penName) {
		NoteResult<Comic> noteResult = new NoteResult<Comic>();

		if (!file.isEmpty()) {

			System.out.println("尝试新增漫画：title=[" + comicTitle + "]");

			// 查询数据库
			Comic comic = comicDao.findByTitle(comicTitle);
			if (comic == null) {
				noteResult.setStatus(0);
				noteResult.setMsg("验证通过,可以新增漫画");
				// 新增漫画
				comicDao.addComic(comicTitle, file.getOriginalFilename());
				//取新增漫画
				Comic newcomic=comicDao.findByTitle(comicTitle);
				noteResult.setData(newcomic);
				
				//保存文件
				// 文件存放目录
				String storePath = STOREPATH + File.separator + newcomic.getId();

				File filepath = new File(storePath, file.getOriginalFilename());

				if (!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();// 如果目录不存在，创建目录
				}
				
				try {
					// 把文件写入目标文件地址
					file.transferTo(new File(storePath + File.separator + file.getOriginalFilename()));
					noteResult.setMsg("提交已完成");
					//增加出版记录
					publishDao.addPublish(id, penName, newcomic.getId());
				} catch (Exception e) {
					e.printStackTrace();
					//删除已提交的出错漫画
					comicDao.deleteWrongComic(newcomic.getId());
					noteResult.setStatus(2);
					noteResult.setMsg("提交失败");
					return noteResult;
				}
				// 。
			} else {
				noteResult.setStatus(1);
				noteResult.setMsg("漫画名重复");
				// 。
			}
			//....
		} else {
			noteResult.setStatus(2);
			noteResult.setMsg("空文件");
		}
		return noteResult;
		
	}

	// 漫画更新
	public NoteResult<Chapter> uploadChapter(Integer comicid,int part, String title, int cost, MultipartFile[] files) {
		NoteResult<Chapter> noteResult=new NoteResult<Chapter>();
		
		//保存文件
		// 文件存放目录
		String storePath = STOREPATH + File.separator + comicid+ File.separator+part;

		File chapterPath = new File(storePath);

		if (!chapterPath.exists()) {
			chapterPath.mkdirs();// 如果目录不存在，创建目录
		}
		
		//保存页至章节文件夹
		for(int i=0;i<files.length;i++) {
			MultipartFile file=files[i];
			
			
			//文件保存路径
			String pagePath = FilenameUtils.concat(storePath, file.getOriginalFilename());
			
			try {
				file.transferTo(new File(pagePath));
			} catch (Exception e) {
				e.printStackTrace();
				//删除章节文件夹??
				noteResult.setStatus(0);
				noteResult.setMsg("添加失败");
			}
		}
		
		//添加数据库章节记录
		chapterDao.addChapter(comicid, part, title, cost);
		//添加数据库页记录
		for(int i=0;i<files.length;i++) {
			MultipartFile file=files[i];
			pageDao.addPage(comicid, part, i+1, file.getOriginalFilename());
			
		}
		//更新漫画长度
		comicDao.updateComicLength(comicid);
		noteResult.setStatus(1);
		noteResult.setMsg("已成功更新章节");
		return noteResult;
	}
	
	//是否有权更新
	public NoteResult<Publish> publishCan(Author author, Integer comicid) {
		NoteResult<Publish> noteResult=new NoteResult<Publish>();
		
		Boolean eaerCan=publishDao.truehavePublish(author.getId(), comicid);
		if(eaerCan==true) {
			noteResult.setStatus(0);
			noteResult.setMsg("有权更新");
		}else {
			noteResult.setStatus(1);
			noteResult.setMsg("无权更新");
		}
		
		return noteResult;
	}
	
	public NoteResult<Chapter> chapterCan(Integer comicid,Integer part){
		NoteResult<Chapter> noteResult=new NoteResult<Chapter>();
		
		Chapter chapter=chapterDao.getCost(comicid, part);
		if(chapter==null) {
			noteResult.setStatus(0);
			noteResult.setMsg("可以更新章节");
		}else {
			noteResult.setStatus(1);
			noteResult.setMsg("不可以更新章节");
		}
		
		return noteResult;
	}
}
