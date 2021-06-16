package batch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import dao.BoardDAO;
import dto.BoardDTO;



public class TopHit implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		BoardDTO top1 = BoardDAO.getInstance().topHit();

			System.out.println("====최대 조회수 게시글====");
			System.out.println("제목: " + top1.getTitle());
			System.out.println("내용: " + top1.getContent());
			System.out.println("조회수: " + top1.getHit());
			

	}
}