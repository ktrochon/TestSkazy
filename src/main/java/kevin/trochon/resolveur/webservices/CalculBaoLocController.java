package kevin.trochon.resolveur.webservices;

import io.swagger.annotations.ApiOperation;
import kevin.trochon.resolveur.entity.TermesDuCalculBaoLoc;
import kevin.trochon.resolveur.webservices.dto.get.CalculBaoLocGetDto;
import kevin.trochon.resolveur.webservices.mapper.CalculBaoLocMapper;
import kevin.trochon.resolveur.webservices.services.CalculBaoLocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@RestController
@Transactional
@EnableSwagger2
public class CalculBaoLocController {

    @Autowired
    private CalculBaoLocService service;

    @Autowired
    private CalculBaoLocMapper mapper;

    @ApiOperation(value = "Historique des différents résultats.")
    @GetMapping(value = "/calculBaoLoc")
    public List<CalculBaoLocGetDto> findAll(){
        List<TermesDuCalculBaoLoc> termesDuCalculBaoLocs = service.getRepository().findAll();
        return mapper.toDtos(termesDuCalculBaoLocs);
    }

    @ApiOperation(value = "Récupération d'un résultats.")
    @GetMapping(value = "/calculBaoLoc/{resultat}")
    public CalculBaoLocGetDto findByResultat(@PathVariable int resultat){
        TermesDuCalculBaoLoc termesDuCalculBaoLoc = service.getRepository().findByResultat(resultat).orElseThrow(EntityExistsException::new);
        return mapper.toDto(termesDuCalculBaoLoc);
    }

    @ApiOperation(value = "Sauvegarde d'un résultats.")
    @PatchMapping(value = "/calculBaoLoc/{resultat}/{newResultat}")
    public CalculBaoLocGetDto saveCalculBaoLoc(@PathVariable String resultat,@PathVariable String newResultat){
       service.getRepository().delete(service.getRepository().findByResultat(Integer.parseInt(resultat)).orElseThrow());
       LocalTime debutDuCalcul = LocalTime.now();
       while (!service.solve(newResultat,debutDuCalcul)) {}
       return mapper.toDto(service.getRepository().findByResultat(Integer.parseInt(newResultat)).orElseThrow(EntityExistsException::new));
    }

    @ApiOperation(value = "Modification d'un résultats.")
    @PostMapping(value = "/calculBaoLoc/{resultat}")
    public CalculBaoLocGetDto saveCalculBaoLoc(@PathVariable String resultat){
        LocalTime debutDuCalcul = LocalTime.now();
        while (!service.solve(resultat,debutDuCalcul)){
        }
        return mapper.toDto(service.getRepository().findByResultat(Integer.parseInt(resultat)).orElseThrow(EntityExistsException::new));
    }

    @DeleteMapping(value = "/calculBaoLoc/delete/{resultat}")
    @ApiOperation(value = "Suppression d'un résultat.")
    public void deleteCalculBaoLoc(@PathVariable int resultat){
        TermesDuCalculBaoLoc termesDuCalculBaoLoc = service.getRepository().findByResultat(resultat).orElseThrow(EntityExistsException::new);
        service.getRepository().delete(termesDuCalculBaoLoc);
    }
}
