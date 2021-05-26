package ma.enset.web;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ma.enset.dao.PatientRepository;
import ma.enset.entities.Patient;

@Controller
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping(path= "/index")
	public String index(Model model, 
			            @RequestParam(name="page", defaultValue="0") int page, 
			            @RequestParam(name="size", defaultValue="5")int size,
			            @RequestParam(name="keyword", defaultValue="")String mc
			            ) {
		Page<Patient> pagePatients = patientRepository.findByNameContains(mc, PageRequest.of(page, size));
		model.addAttribute("index",pagePatients.getContent());
		model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword", mc);
		return "index";
	}
	
	@GetMapping(path= "/deletePatient")
	public String delete(Long id, String keyword, int page, int size) {
		patientRepository.deleteById(id);
		return "redirect:/index?page="+ page + "&size=" + size + "&keyword=" + keyword;
	}
	
	@GetMapping(path= "/deletePatient2")
	public String delete2(Long id, String keyword, int page, int size, Model model) {
		patientRepository.deleteById(id);
		return index(model, size, size, keyword);
	}
	 
	@GetMapping(path= "/formAjoutPatient")
	public String formAjoutPatient(Model model) {
		model.addAttribute("patient", new Patient());
		return "formAjoutPatient";
	}
	
	@PostMapping(path= "/savePatient")
	public String fsavePatient(@Valid Patient patient , BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return "formAjoutPatient";
		patientRepository.save(patient);
		return "formAjoutPatient";
	}
	
	@GetMapping("/editPatient")
	public String editPatient(Model model, Long id) {
		Patient patient = patientRepository.findById(id).get();
		model.addAttribute("patient", patient);
		return "formAjoutPatient";
		
	}
}
