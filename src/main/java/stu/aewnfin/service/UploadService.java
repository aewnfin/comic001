
package stu.aewnfin.service;


import org.springframework.web.multipart.MultipartFile;

import stu.aewnfin.entity.Author;
import stu.aewnfin.entity.Chapter;
import stu.aewnfin.entity.Comic;
import stu.aewnfin.entity.Publish;
import stu.aewnfin.util.NoteResult;

public interface UploadService {
	// 尝试提交新漫画
	public NoteResult<Comic> sameComic(String comicTitle,MultipartFile file,Integer id,String penName);

	// 漫画更新
	public NoteResult<Chapter> uploadChapter(Integer comicid,int part, String title, int cost, MultipartFile[] files);

	//是否有权更新
	public NoteResult<Publish> publishCan(Author author, Integer comicid);
	
	//章节是否已经存在
	public NoteResult<Chapter> chapterCan(Integer comicid,Integer part);
//	
//	//新增章节
//	public void uploadChapter(Comic comic,int part,String title,int cost);
//	
//	//新增页
//	public void uploadPage(Chapter chapter,int page_num,String content);
	
}
