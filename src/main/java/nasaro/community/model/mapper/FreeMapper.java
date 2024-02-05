package nasaro.community.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nasaro.community.model.dto.Free;
import nasaro.community.model.dto.Notice;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Verses;

@Mapper
public interface FreeMapper {


	List<Free> freeList();

	Free detailedFree();

}

