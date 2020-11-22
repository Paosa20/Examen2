package com.cenfotec.examen2.controller;


import com.cenfotec.examen2.domain.Categorias;
import com.cenfotec.examen2.domain.Tareas;
import com.cenfotec.examen2.domain.Workshop;
import com.cenfotec.examen2.service.categoria.CategoriaService;
import com.cenfotec.examen2.service.tarea.TareaService;
import com.cenfotec.examen2.service.workshop.WorkshopService;
import org.apache.poi.xwpf.usermodel.*;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Controller
public class WorkshopController {

    @Autowired
    WorkshopService workshopService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    TareaService tareaService;

    @RequestMapping("/")
    public String home(Model model) {
        return "Home";
    }

    @RequestMapping(value = "/Workshop", method = RequestMethod.GET)
    public String mostrarWorkshop(Model model){

        model.addAttribute("workshop", new Workshop());
        model.addAttribute("categoria", categoriaService.getAll());
        return "Workshop";
    }

    @RequestMapping(value = "/Workshop",  method = RequestMethod.POST)
    public String insertarAction(Workshop workshop, BindingResult result, Model model, HttpServletRequest request) {
        workshopService.save(workshop);
        return "Home";
    }

    @RequestMapping(value = "/tareasWorkshop/{id}", method = RequestMethod.GET)
    public String mostrarTareas(@PathVariable("id") long id, Model model){
        Optional<Workshop> workshop = workshopService.get(id);
         Tareas newTarea = new Tareas();
        if (workshop.isPresent()) {
            newTarea.setWorkshop(workshop.get());
            model.addAttribute("workshop",workshop.get());
            model.addAttribute("tarea",newTarea);
            return "tareasWorkshop";
        }
        return "notfound";
    }

    @RequestMapping(value = "/tareasWorkshop/{id}",  method = RequestMethod.POST)
    public String insertarTareas(@PathVariable("id") long id,Tareas tareas, BindingResult result, Model model) {
            Optional<Workshop> workshop = workshopService.get(id);
        if (workshop.isPresent()) {
            tareas.setWorkshop(workshop.get());
            tareaService.save(tareas);
            Workshop anotherWorkshop = workshop.get();

            anotherWorkshop.setDuracionTotal(tareas.getDuracion());

            workshopService.save(anotherWorkshop);



            model.addAttribute("workshop",workshopService.getAll());
            model.addAttribute("Categoria", categoriaService.getAll());
            model.addAttribute("OtroWorkshop", new Workshop());
            return "listaWorkshop";
        }
        return "Home";
    }

    @RequestMapping("/listaTareas/{id}")
    public String listarTareas(Model model, @PathVariable long id) {
        Optional<Workshop> tareasWorkshop = workshopService.get(id);
        if (tareasWorkshop.isPresent()) {
            model.addAttribute("workshop",tareasWorkshop.get());
            return "listaTareas";
        }
        return "Home";
    }


    @RequestMapping("/listaWorkshop")
    public String listar(Model model) {
        model.addAttribute("workshop",workshopService.getAll());
        model.addAttribute("Categoria", categoriaService.getAll());
        model.addAttribute("OtroWorkshop", new Workshop());
        return "listaWorkshop";
    }

    @RequestMapping(value = "/Categorias", method = RequestMethod.GET)
    public String mostrarCategorias(Model model){

        model.addAttribute("categoria", new Categorias());
        return "Categorias";
    }

    @RequestMapping(value = "/Categorias",  method = RequestMethod.POST)
    public String insertarCategoria(Categorias categorias, BindingResult result, Model model) {
        categoriaService.save(categorias);
        return "Home";
    }

    @RequestMapping("/listaCategorias")
    public String listarCategoria(Model model) {
        model.addAttribute("categoria",categoriaService.getAll());
        return "listaCategorias";
    }

    @RequestMapping(value = "/eliminadaCategorias", method = RequestMethod.DELETE)
    public String eliminarCategoria(Categorias categoria,Model model) {
        categoriaService.delete(categoria);
        model.addAttribute("categoria",categoriaService.getAll());
        return "listaCategorias";
    }

    @GetMapping("/cambioWorkshop/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Optional<Workshop> workshop = workshopService.get(id);

        if (workshop.isPresent()){
            model.addAttribute("workshop", workshop);
            model.addAttribute("categoria", categoriaService.getAll());
            return "cambioWorkshop";
        }

        return "Home";
    }

    @PostMapping("/cambioWorkshop/{id}")
    public String insertarUpdate(@PathVariable("id") long id, Workshop workshop, BindingResult result, Model model) {

        if (result.hasErrors()){
            workshop.setId(id);
            return "/cambioWorkshop/{id}";
        }
        workshopService.save(workshop);
        model.addAttribute("workshop",workshopService.getAll());
        return "listaWorkshop";
    }


    @RequestMapping("/listaCategoriaWorkshop")
    public String listarCategoriaWorkshop(@RequestParam String categoria, Model model) {
        model.addAttribute("categoriaWorkshop",workshopService.findCategoria(categoria));
        return "listaCategoriaWorkshop";
    }


    @RequestMapping("/listaAutorWorkshop")
    public String listarAutorWorkshop(@RequestParam String autor, Model model) {
        model.addAttribute("autorWorkshop",workshopService.findAutor(autor));
        return "listaAutorWorkshop";
    }

    @RequestMapping("/listaKeywordWorkshop")
    public String listarKeywordWorkshop(@RequestParam String keyword, Model model) {
        model.addAttribute("keywordWorkshop",workshopService.findKeywords(keyword));
        return "listaKeywordWorkshop";
    }


    @GetMapping("/cambioCategoria/{id}")
    public String showUpdateFormCategoria(@PathVariable("id") long id, Model model) {
        Optional<Categorias> categorias = categoriaService.get(id);

        if (categorias.isPresent()){
            model.addAttribute("categoria", categorias);
            return "cambioCategoria";
        }

        return "Home";
    }

    @PostMapping("/cambioCategoria/{id}")
    public String insertarUpdateCategoria(@PathVariable("id") long id, Categorias categorias, BindingResult result, Model model) {

        if (result.hasErrors()){
            categorias.setId(id);
            return "/cambioWorkshop/{id}";
        }
        categoriaService.save(categorias);
        model.addAttribute("categoria",categoriaService.getAll());
        return "listaCategorias";
    }


    @RequestMapping(value = "/Word/{id}")
    public String getGeneratedDocument(Model model, @PathVariable long id) throws IOException {
        Optional<Workshop> infoWorkshop = workshopService.get(id);
        if (infoWorkshop.isPresent()) {
            int duracion=0;
            String actividades="";
            for (Tareas act: infoWorkshop.get().getTareas()) {
                duracion+=act.getDuracion();
            }
            XWPFDocument document = new XWPFDocument();
            String output = infoWorkshop.get().getNombre() + ".docx";
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText(infoWorkshop.get().getNombre());
            titleRun.setColor("000000");
            titleRun.setBold(true);
            titleRun.setFontFamily("Arial");
            titleRun.setFontSize(20);

            XWPFParagraph title2=document.createParagraph();
            title2.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun2 = title2.createRun();
            titleRun2.setText("Duracion del taller= "+duracion+" minutos");
            titleRun2.setColor("000000");
            titleRun2.setBold(true);
            titleRun2.setFontFamily("Arial");
            titleRun2.setFontSize(20);

            XWPFParagraph subTitle = document.createParagraph();
            subTitle.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun subTitleRun = subTitle.createRun();
            subTitleRun.setText("Autor del taller= "+infoWorkshop.get().getAutor());
            titleRun2.setColor("000000");
            titleRun2.setBold(true);
            titleRun2.setFontFamily("Arial");
            titleRun2.setFontSize(20);

            XWPFParagraph sectionTitle = document.createParagraph();
            XWPFRun sectionTRun = sectionTitle.createRun();
            sectionTRun.setText("Categoria "+infoWorkshop.get().getCategoria());
            sectionTRun.setColor("000000");
            sectionTRun.setBold(true);
            sectionTRun.setFontFamily("Arial");

            for (Tareas act: infoWorkshop.get().getTareas()) {
                XWPFParagraph sub = document.createParagraph();
                sub.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun subTitleRu = sub.createRun();
                subTitleRu.setColor("000000");
                subTitleRu.setFontFamily("Arial");
                subTitleRu.setFontSize(16);
                subTitleRu.setTextPosition(20);
                subTitleRu.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
                actividades= act.getNombre()+"\n"+
                        "Descripción= "+act.getDescripcion()+"\n"+
                        "Notas de la actividad= "+act.getTextoLeido() +"\n";
                subTitleRun.setText(actividades);

            }

            FileOutputStream out = new FileOutputStream(output);
            document.write(out);
            out.close();
            document.close();
            return "Home";
        } else {
            return "notfound";
        }
    }

}
