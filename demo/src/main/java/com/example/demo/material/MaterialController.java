package com.example.demo.material;

import com.example.demo.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping("/get")
    public List<Material> getAllMaterial() {
        return materialService.getAllMaterials();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPerson(@RequestBody Material material) {
        try{
            materialService.insertMaterial(material);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating material."+e.getMessage());
        }
    }

}
