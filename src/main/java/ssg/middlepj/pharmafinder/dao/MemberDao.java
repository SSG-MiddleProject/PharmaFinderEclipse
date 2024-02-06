package ssg.middlepj.pharmafinder.dao;
import org.springframework.stereotype.Repository;
import ssg.middlepj.pharmafinder.dto.MemberDto;

public interface MemberDao {
	
	int idcheck(String id);
	int addmember(MemberDto mem);
}
