package nasaro.community.model.service;

import java.util.List;

import nasaro.community.model.dto.Free;
import nasaro.community.model.dto.Notice;
import nasaro.community.model.dto.Sharing;
import nasaro.pray.model.dto.Family;
import nasaro.pray.model.dto.Nations;
import nasaro.verses.model.dto.Column;
import nasaro.verses.model.dto.Reference;
import nasaro.verses.model.dto.Verses;

public interface SharingService {

	List<Sharing> sharingList();

	Sharing detailedSharing(String no);


	
}
