package nasaro.pray.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Verses;

@Mapper
public interface FamilyMapper {


	List<Family> family();

	Family detailedFamily(String no);

}

