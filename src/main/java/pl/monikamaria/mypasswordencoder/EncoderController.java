package pl.monikamaria.mypasswordencoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EncoderController {

    private CaesarCypherService caesarCypherService;

    @Autowired
    public EncoderController(CaesarCypherService caesarCypherService) {
        this.caesarCypherService = caesarCypherService;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> index() {
        Map<String, String> map = new HashMap<>();
        try {
            map.put("encode link", "localhost:8080/encode?password=AkademiaSpring");
            map.put("matches link", "localhost:8080/matches?password=AkademiaSpring&hashedPassword=nxnqrzvnfcevat");
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            map.put("error", e.toString());
            return ResponseEntity.status(500).body(map);
        }
    }

    @GetMapping("/encode")
    public ResponseEntity<String> encode(@RequestParam String password) {
        try {
            return ResponseEntity.ok(caesarCypherService.encode(password));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error");
        }
    }

    @GetMapping("/matches")
    public ResponseEntity<Boolean> matches(@RequestParam String password, @RequestParam String hashedPassword) {
        try {
            return ResponseEntity.ok(caesarCypherService.matches(password, hashedPassword));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Boolean.FALSE);
        }
    }
}
