package kevin.trochon.resolveur.webservices.mapper;

import kevin.trochon.resolveur.entity.TermesDuCalculBaoLoc;
import kevin.trochon.resolveur.webservices.dto.get.CalculBaoLocGetDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalculBaoLocMapper {

    public CalculBaoLocGetDto toDto(TermesDuCalculBaoLoc entity){
        CalculBaoLocGetDto dto = new CalculBaoLocGetDto();
        dto.setTerme1(entity.getTerme1());
        dto.setTerme2(entity.getTerme2());
        dto.setTerme3(entity.getTerme3());
        dto.setTerme4(entity.getTerme4());
        dto.setTerme5(entity.getTerme5());
        dto.setTerme6(entity.getTerme6());
        dto.setTerme7(entity.getTerme7());
        dto.setTerme8(entity.getTerme8());
        dto.setTerme9(entity.getTerme9());
        dto.setResultat(entity.getResultat());
        dto.setTempsExecution(entity.getTempsExecution());
        return dto;
    }

    public List<CalculBaoLocGetDto> toDtos(List<TermesDuCalculBaoLoc> entities){
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
